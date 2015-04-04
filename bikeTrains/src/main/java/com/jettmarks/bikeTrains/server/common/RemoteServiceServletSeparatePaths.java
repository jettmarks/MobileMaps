/**
 * 
 */
package com.jettmarks.bikeTrains.server.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;

/**
 * This is a solution I found for providing the same service to separate GWT
 * apps.
 * 
 * @author jett
 */
public class RemoteServiceServletSeparatePaths extends RemoteServiceServlet {
	private static final long serialVersionUID = 791696605414203827L;

	protected SerializationPolicy doGetSerializationPolicy(
			HttpServletRequest request, String moduleBaseURL, String strongName) {
		// The request can tell you the path of the web app relative to the
		// container root.

		SerializationPolicy serializationPolicy = null;
		String serializationPolicyFilePath = "";
		InputStream is = null;

		String contextPath = request.getContextPath();

		String modulePath = null;
		if (moduleBaseURL != null) {
			try {
				modulePath = new URL(moduleBaseURL).getPath();
			} catch (MalformedURLException ex) {
				// log the information, we will default
				getServletContext().log(
						"Malformed moduleBaseURL: " + moduleBaseURL, ex);
			}
		} else { // Just quit, if we do not know the module base. (07/11/08 -
					// Danny)
			return serializationPolicy;
		}

		// Disregard same web source. (07/11/08 - Danny)

		if (modulePath == null) {
			String message = "ERROR: The module path requested, "
					+ modulePath
					+ ", is null, "
					+ contextPath
					+ ".  Your module may not be properly configured or your client and server code maybe out of date.";
			getServletContext().log(message);
		} else {
			// Set up input stream for serialization policy file, based on /
			// servlet call. (07/11/08 - Danny)localhost.2011-08-17.log
			if (contextPath.equals("/servlet")) {
				try {
					URL baseURL = new URL(moduleBaseURL + strongName
							+ ".gwt.rpc");
					URLConnection baseURLConnection = baseURL.openConnection();
					is = baseURLConnection.getInputStream();
				} catch (Exception ex) {
					String message = "ERROR: Could not open policy file, "
							+ modulePath
							+ ", is null, "
							+ contextPath
							+ ".  Your module may not be properly configured or your client and server code maybe out of date."
							+ " Exception=" + ex.toString();
					getServletContext().log(message);
					return serializationPolicy;
				}
			} else {
				// Strip off the context path from the module base URL. It
				// should be a
				// strict prefix.
				getServletContext().log(
						"modulePath: " + modulePath + " ContextPath: "
								+ contextPath);
				String contextRelativePath;
				if (modulePath.contains(contextPath)) {
					contextRelativePath = modulePath.substring(contextPath
							.length());
				} else {
					contextRelativePath = modulePath;
				}

				// Adding the context of '/showRoutes' in case it is missing
				// ('/' only)
				if ("/".equals(modulePath)) {
					contextRelativePath = "/showRoutes/";
				}

				serializationPolicyFilePath = SerializationPolicyLoader
						.getSerializationPolicyFileName(contextRelativePath
								+ strongName);

				// Open the RPC resource file read its contents.
				is = getServletContext().getResourceAsStream(
						serializationPolicyFilePath);
			}
			try {
				if (is != null) {
					try {
						serializationPolicy = SerializationPolicyLoader
								.loadFromStream(is, null);
					} catch (ParseException e) {
						getServletContext().log(
								"ERROR: Failed to parse the policy file '"
										+ serializationPolicyFilePath + "'", e);
					} catch (IOException e) {
						getServletContext().log(
								"ERROR: Could not read the policy file '"
										+ serializationPolicyFilePath + "'", e);
					}
				} else {
					String message = "ERROR: The serialization policy file '"
							+ serializationPolicyFilePath
							+ "' was not found; did you forget to include it in this deployment?";
					getServletContext().log(message);
				}
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						// Ignore this error
					}
				}
			}
		}

		return serializationPolicy;
	}
}
