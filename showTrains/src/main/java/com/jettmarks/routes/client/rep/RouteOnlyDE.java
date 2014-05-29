/**
 * 
 */
package com.jettmarks.routes.client.rep;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jettmarks.routes.client.bean.DisplayElementDTO;

/**
 * Holds a Route which doesn't have a record in the database, but only exists
 * on the Local Drive as a GPX file.
 * 
 * Written to help assemble other DisplayElements that are specialized for one
 * of the other types of DisplayElement.
 * 
 * These are used to build up a RouteRequest to be used by Container components.
 * 
 * Whereas most DisplayElements will have an ID, this one won't have an ID yet 
 * and will need to be known by its name.
 * 
 * @author jett
 */
public class RouteOnlyDE extends DisplayElementDTO implements Serializable, IsSerializable
{
  /**
   * 
   */
  private static final long serialVersionUID = -377877119741920222L;
  private String routeName = null;

  /** Default constructor required for Serialization to work. **/
  public RouteOnlyDE()
  {
    
  }
  
  /**
   * @param routeName
   */
  public RouteOnlyDE(String routeName)
  {
    this.routeName = routeName;
  }

  /**
   * @return the routeName
   */
  public String getRouteName()
  {
    return routeName;
  }

  /**
   * @param routeName the routeName to set
   */
  public void setRouteName(String routeName)
  {
    this.routeName = routeName;
  }
  

}
