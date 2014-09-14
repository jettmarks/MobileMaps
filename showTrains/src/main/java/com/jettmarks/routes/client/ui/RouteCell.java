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
 * Created Jun 2, 2014
 */
package com.jettmarks.routes.client.ui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.widget.celllist.Cell;
import com.jettmarks.routes.client.bean.BikeTrainRoute;

/**
 * Builds the contents of our Route Cells.
 * 
 * @author jett
 */
public class RouteCell implements Cell<BikeTrainRoute> {
    private static Template TEMPLATE = GWT.create(Template.class);

    public interface Template extends SafeHtmlTemplates {
	@SafeHtmlTemplates.Template("<div>{0}</div>")
	SafeHtml content(String text);
    }

    /**
     * @see com.googlecode.mgwt.ui.client.widget.celllist.Cell#render(com.google.gwt
     *      .safehtml.shared.SafeHtmlBuilder, java.lang.Object)
     */
    @Override
    public void render(SafeHtmlBuilder safeHtmlBuilder, BikeTrainRoute model) {
	if (model == null)
	    return;
	SafeHtml content = TEMPLATE
		.content((model.getDisplayName() != null) ? model
			.getDisplayName() : model.getName());
	safeHtmlBuilder.append(content);
    }

    /**
     * @see com.googlecode.mgwt.ui.client.widget.celllist.Cell#canBeSelected(java
     *      .lang .Object)
     */
    @Override
    public boolean canBeSelected(BikeTrainRoute model) {
	return true;
    }

}
