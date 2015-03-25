/**
 * 
 */
package com.jettmarks.routes.client.ui.tab;

import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;

/**
 * @author jett
 *
 */
public class ListTabBarButton extends TabBarButtonBase {
	/**
	 * @param css
	 * @param imageResource
	 */
	public ListTabBarButton() {
		super(null, null, null);
		// super(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarCss(),
		// AppBundle.INSTANCE.tabBarListImage());
		setText("List");
	}

}
