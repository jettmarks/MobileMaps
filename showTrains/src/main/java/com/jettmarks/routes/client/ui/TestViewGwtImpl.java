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
 * Created Mar 31, 2015
 */
package com.jettmarks.routes.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.panel.flex.RootFlexPanel;

/**
 * Description.
 *
 * @author jett
 *
 */
public class TestViewGwtImpl implements TestView {
	protected HTML title;
	protected RootFlexPanel main;

	public TestViewGwtImpl() {
		main = new RootFlexPanel();
		title = new HTML();
		main.add(title);
	}

	/**
	 * 
	 * @see com.google.gwt.user.client.ui.IsWidget#asWidget()
	 */
	@Override
	public Widget asWidget() {
		return main;
	}

	/**
	 * @see com.jettmarks.routes.client.ui.TestView#getHeader()
	 */
	@Override
	public HasText getHeader() {
		return title;
	}

}