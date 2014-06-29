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
public class ListTabBarButton extends TabBarButtonBase
{
	/**
	 * @param css
	 * @param imageResource
	 */
  public ListTabBarButton()
  {
		super(MGWTStyle.getTheme().getMGWTClientBundle().getTabBarCss(), 
          AppBundle.INSTANCE.tabBarListImage());
    setText("List");
	}

}
