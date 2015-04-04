/**
 *   Copyright 2009 Jett Marks
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created April 2009
 */
package com.jettmarks.bikeTrains.server;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.jettmarks.bikeTrains.client.service.RSSProxy;
import com.jettmarks.bikeTrains.server.common.RemoteServiceServletSeparatePaths;
import com.jettmarks.routes.common.LocalDrive;
import com.jettmarks.routes.common.ResourceManager;
import com.jettmarks.routes.server.rtsrc.RouteSource;
import com.jettmarks.routes.server.rtsrc.RouteSourceBase;

/**
 * Returns a String representation of a file; appropriate URL is constructed
 * according to route source and the particular entry point method.
 * 
 * Interpretation of the string is up to the caller.
 * 
 * The server side will be able to tell which OS it is running under and return
 * an appropriate resource when it is file based. LocalDrive helps with this.
 * 
 * This was originally intended to pick up an RSS resource from an external
 * website from within JavaScript, but it has shown useful to retrieve any
 * resource sitting pretty much anywhere.
 * 
 * @author jett
 * 
 */
public class RSSProxyImpl extends RemoteServiceServletSeparatePaths implements
		RSSProxy {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RSSProxyImpl.class);

	private static final long serialVersionUID = 8677152004704407097L;

	/**
	 * Instead of relying on the JavaScript client to know where the server
	 * would get its GPX Template, we're letting the server decide where to get
	 * it from.
	 * 
	 * @see com.jettmarks.routes.client.service.RSSProxy#getGPXTemplate()
	 */
	public String getGPXTemplate() {
		return getResource(LocalDrive.getGPXTemplateFileName());
	}

	public String getFeed(String routeSourceName) {
		return getResource(RouteSourceBase.getInstance(routeSourceName)
				.getFeedURL());
	}

	/**
	 * Determines the right URL to use based on the URL of the route's source.
	 * 
	 * @see com.jettmarks.routes.client.service.RSSProxy#getRoute(java.lang.String)
	 */
	public String getRoute(String routeSourcePath) {
		String routeUrl = null;
		if (routeSourcePath.contains("ridewithgps")) {
			RouteSource routeSource = RouteSourceBase
					.getInstance("Ride with GPS");
			try {
				routeUrl = routeSource.getRouteURL(new URL(routeSourcePath));
			} catch (MalformedURLException e) {
				logger.error("Unable to recognize URL: " + routeSourcePath, e);
			}
		}
		return getResource(routeUrl);
	}

	/**
	 * @see com.jettmarks.routes.client.service.RSSProxy#getRoute(java.lang.String,
	 *      java.lang.String)
	 */
	public String getRoute(String routeName, String routeSourceName) {
		String tags[] = new String[1];
		logger.debug("Getting " + routeName + " from " + routeSourceName);
		RouteSource routeSource = RouteSourceBase.getInstance(routeSourceName);
		if (routeSource == null) {
			logger.error("Unable to find route source matching "
					+ routeSourceName);
			return null;
		}
		tags[0] = System.getProperty("show.tag", "master");
		String routeUrl = routeSource.getRouteURL(routeName, tags);
		return getResource(routeUrl);
	}

	/**
	 * @see com.jettmarks.routes.client.service.RSSProxy#getRoute(java.lang.String,
	 *      java.lang.String, java.lang.String[])
	 */
	public String getRoute(String routeName, String routeSourceName,
			String[] tagList) {
		String routeUrl = RouteSourceBase.getInstance(routeSourceName)
				.getRouteURL(routeName, tagList);
		return getResource(routeUrl);
	}

	/**
	 * @see com.jettmarks.routes.client.service.RSSProxy#getOverlay(java.lang.String,
	 *      java.lang.String)
	 */
	public String getOverlay(String overlayName, String routeSourceName) {
		String overlayUrl = "file:////" + LocalDrive.getRoutePathLocal()
				+ overlayName + ".kmz";
		return getResource(overlayUrl);
	}

	protected String getResource(String sUrl) {
		return ResourceManager.getResource(sUrl);
	}

}
