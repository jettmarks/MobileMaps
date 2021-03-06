/**
 *   Copyright 2014 Jett Marks
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
 * Created Aug 19, 2014
 */
package com.jettmarks.routes.client;

import com.google.gwt.place.shared.Place;

/**
 * Description.
 * 
 * @author jett
 */
public class NavLink {
    private String displayName;
    private Place place;
    private String url;

    /**
     * @param string
     */
    public NavLink(String name) {
	displayName = name;
    }

    /**
     * @param string
     * @param eventSelectionPlace
     */
    public NavLink(String name, Place place) {
	this.displayName = name;
	this.place = place;
    }

    /**
     * @param string
     * @param url2
     */
    public NavLink(String name, String url) {
	this.displayName = name;
	this.url = url;
    }

    /**
     * @return the url
     */
    public String getUrl() {
	return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
	this.url = url;
    }

    /**
     * @return the place
     */
    public Place getPlace() {
	return place;
    }

    /**
     * @param place
     *            the place to set
     */
    public void setPlace(Place place) {
	this.place = place;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
	return displayName;
    }

    /**
     * @param displayName
     *            the displayName to set
     */
    public void setDisplayName(String displayName) {
	this.displayName = displayName;
    }
}
