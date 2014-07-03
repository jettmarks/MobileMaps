/**
 * 
 */
package com.jettmarks.routes.client.ui.tab;

import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabBarButtonBase;
import com.jettmarks.routes.client.css.AppBundle;

/**
 * @author jett
 *
 */
public class MapTabBarButton extends TabBarButtonBase
{
	/**
	 * @param css
	 * @param imageResource
	 */
  public MapTabBarButton()
  {
		super(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarCss(), 
          AppBundle.INSTANCE.tabBarMapImage());
    setText("Map");
	}

}
