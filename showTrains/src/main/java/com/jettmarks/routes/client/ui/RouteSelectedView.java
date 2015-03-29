/**
 *   Copyright 2015 Jett Marks
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
 * Created Mar 28, 2015
 */
package com.jettmarks.routes.client.ui;

import com.jettmarks.routes.client.DetailView;

/**
 * Presents options for the user once they choose a particular route.
 *
 * Some potential options are:
 * <UL>
 * <LI>Show the name of the route selected.
 * <LI>Ask if they want to see details.
 * <LI>Provide other options and details so they know what they've selected and
 * how to navigate to details page.
 * </UL>
 * 
 * @author jett
 *
 */
public interface RouteSelectedView extends DetailView {

	public abstract void setRouteName(String routeName);

}
