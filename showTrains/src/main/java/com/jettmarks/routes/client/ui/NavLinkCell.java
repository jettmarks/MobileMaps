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
package com.jettmarks.routes.client.ui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.widget.celllist.Cell;
import com.jettmarks.routes.client.NavLink;

/**
 * Description.
 * 
 * @author jett
 */
public class NavLinkCell implements Cell<NavLink> {

    private static Template TEMPLATE = GWT.create(Template.class);

    public interface Template extends SafeHtmlTemplates {
	@SafeHtmlTemplates.Template("<div>{0}</div>")
	SafeHtml content(String text);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.googlecode.mgwt.ui.client.widget.celllist.Cell#render(com.google.
     * gwt.safehtml.shared.SafeHtmlBuilder, java.lang.Object)
     */
    @Override
    public void render(SafeHtmlBuilder safeHtmlBuilder, NavLink model) {
	if (model == null)
	    return;
	SafeHtml content = TEMPLATE.content(model.getDisplayName());
	safeHtmlBuilder.append(content);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.googlecode.mgwt.ui.client.widget.celllist.Cell#canBeSelected(java
     * .lang.Object)
     */
    @Override
    public boolean canBeSelected(NavLink model) {
	// TODO Auto-generated method stub
	return false;
    }

}
