/**
 * 
 */
package com.jettmarks.routes.client.ui.tab;

import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;

/**
 * @author jett
 *
 */
public class MapTabBarButton extends TabBarButtonBase {
	/**
	 * @param css
	 * @param imageResource
	 */
	public MapTabBarButton() {
		super(null, null, null);
		// super(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarCss(),
		// AppBundle.INSTANCE.tabBarMapImage());
		setText("Map");
	}

}
