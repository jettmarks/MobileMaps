/**
 * 
 */
package com.jettmarks.routes.client.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author jett
 * 
 */
public class ScreenSize
{
  private int height;

  private int width;

  static List<Widget> registeredResizeList = new ArrayList<Widget>();

  private static ScreenSize instance = new ScreenSize();

  private ScreenSize()
  {
    setHeight(Window.getClientHeight());
    setWidth(Window.getClientWidth());

    Window.addResizeHandler(new ResizeHandler()
    {

      @Override
      public void onResize(ResizeEvent event)
      {
        setHeight(event.getHeight());
        setWidth(event.getWidth());
        for (Widget w : registeredResizeList)
        {
          // w.setPixelSize(width, height - 40);
          w.setHeight((height - 89) + "px");
        }
      }
    });
  }

  public static int getHeight()
  {
    return instance.height;
  }

  protected void setHeight(int height)
  {
    this.height = height;
  }

  public static int getWidth()
  {
    return instance.width;
  }

  protected void setWidth(int width)
  {
    this.width = width;
  }

  public static void addRegistration(Widget w)
  {
    registeredResizeList.add(w);
  }

}
