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
 * Created Apr 16, 2015
 */
package com.jettmarks.routes.client.ui.widget.button;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ImageResource;

/**
 * Description.
 *
 * @author jett
 *
 */
public class ImageHolder {
	private static final Appearance APPEARANCE = GWT
			.create(ImageHolder.Appearance.class);

	public interface Appearance {
		public interface Images {
			ImageResource home();
		}

		Images get();
	}

	public static Appearance.Images get() {
		return APPEARANCE.get();
	}
}
