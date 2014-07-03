/**
 * 
 */
package com.jettmarks.routes.util;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.jettmarks.db.HibernateUtil;
import com.jettmarks.routes.server.bean.DisplayGroup;
import com.jettmarks.routes.server.bean.DisplayGroupDAO;

/**
 * Knows how to obtain the latest Bike Train Group from the database.
 * 
 * @author jett
 */
public class BikeTrainGroups
{
  
  @SuppressWarnings("unchecked")
  public static Integer getLatestDisplayGroupID()
  {
	  Session session = HibernateUtil.getSession();
	  DisplayGroupDAO dgDao = new DisplayGroupDAO(session);
	  DisplayGroup btdg = new DisplayGroup();
//	  btdg.setDisplayName("bt%");
	  List<DisplayGroup> dgList = dgDao.findByExample(btdg);
	  DisplayGroup latestDG = null;
	  for (Iterator<DisplayGroup> iter=dgList.iterator(); iter.hasNext(); )
	  {
	    DisplayGroup dg = iter.next();
	    if (latestDG == null || dg.getId() > latestDG.getId())
	    {
	      latestDG = dg;
	    }
	  }
	  session.close();
	  return latestDG.getId();
  }

}
