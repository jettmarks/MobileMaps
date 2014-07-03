package com.jettmarks.routes.helper;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jettmarks.db.HibernateUtil;
import com.jettmarks.routes.client.bean.DisplayElementType;
import com.jettmarks.routes.server.bean.BikeTrain;
import com.jettmarks.routes.server.bean.BikeTrainDAO;
import com.jettmarks.routes.server.bean.DisplayElement;
import com.jettmarks.routes.server.bean.DisplayElementDAO;
import com.jettmarks.routes.server.bean.DisplayGroup;
import com.jettmarks.routes.server.bean.DisplayGroupDAO;
import com.jettmarks.routes.util.BikeTrainGroups;

public class RouteSaveHelper {
	
  private static final Logger logger = Logger.getLogger(RouteSaveHelper.class);
	/**
	 * @param bikeTrain
	 * @param displayGroupId
	 * @return
	 * @throws HibernateException
	 */
	public static Integer saveBikeTrain(BikeTrain bikeTrain, Integer displayGroupId) {
		Integer id = null;
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			id = createNewBikeTrain(bikeTrain, displayGroupId, session);

			tx.commit();
		}
		catch (HibernateException he) {
			
		}
		return id;
	}


	  /**
	   * Creates a new Bike Train record as well as the supporting DisplayElement
	   * and also the link record over to the desired Display Group.
	   * 
	   * @param bikeTrain
	   * @param displayGroupId
	   * @param session
	   * @return
	   */
	  public static Integer createNewBikeTrain(BikeTrain bikeTrain,
	                                 Integer displayGroupId,
	                                 Session session)
	  {
	    // 1. Bike Train Record
	    Integer id = mergeBikeTrain(bikeTrain, session);
	    
	    // 2.a Link Bike Train record to new DisplayElement record ... 
	    DisplayElement de = new DisplayElement();
	    de.setSourceId(id);
	    de.setClassName("BikeTrain");
	    de.setType(DisplayElementType.ROUTE);
	    
	    // 2.b ... and persist
	    DisplayElementDAO deDao = new DisplayElementDAO(session);
	    deDao.save(de);
	    
	    // 3. Group is expected to exist; not sure how to handle group not found
	    if (displayGroupId == 0)
	    {
	      displayGroupId = BikeTrainGroups.getLatestDisplayGroupID();
	    }
		  logger.debug("Using Display Group ID: "+displayGroupId);
	    
	    DisplayGroupDAO dgDao = new DisplayGroupDAO(session);
	    DisplayGroup displayGroup = dgDao.findById(displayGroupId);
	    displayGroup.getElements().add(de);
	    dgDao.save(displayGroup);
	    return id;
	  }
	  

	  /**
	   * Takes existing Bike Train object and updates existing record -- or creates
	   * a new record if it doesn't exist -- and returns the ID of that record.
	   * 
	   * I was hoping the merge could look at the ID and do the logic I've got 
	   * below: test for presence of ID, and pull up DB object by ID before copying
	   * values into the "DB-attached" object.  (I guess I still have more to learn)
	   * 
	   * @param bikeTrain
	   * @param session
	   * @return
	   */
	  public static Integer mergeBikeTrain(BikeTrain bikeTrain,
	                                 Session session)
	  {
	    BikeTrainDAO btDAO = new BikeTrainDAO(session);
	    
	    Integer id = bikeTrain.getId();
	    if (id != null)
	    {
	      BikeTrain dbInstance = btDAO.findById(id);
	      dbInstance.setArrivalTime(bikeTrain.getArrivalTime());
	      dbInstance.setDepartureTime(bikeTrain.getDepartureTime());
	      dbInstance.setLeaderEmail(bikeTrain.getLeaderEmail());
	      dbInstance.setLeaderName(bikeTrain.getLeaderName());
	      dbInstance.setLeaderPhone(bikeTrain.getLeaderPhone());
	      dbInstance.setNotes(bikeTrain.getNotes());
	      dbInstance.setRouteName(bikeTrain.getRouteName());
	      btDAO.merge(dbInstance);
	    }
	    else
	    {
	      id = btDAO.merge(bikeTrain).getId();
	    }
	    return id;
	  }
	  

	  /**
	   * @param bikeTrain
	   * @param displayGroupId
	   * @return
	   */
	  public static int copyToBikeTrain(BikeTrain bikeTrain, Integer displayGroupId) {
	  	int copyToBikeTrainID;
	  	Session session = HibernateUtil.getSession();
	      Transaction tx = session.beginTransaction();
	   
	      BikeTrain copiedBikeTrain = new BikeTrain();
	      copiedBikeTrain.setArrivalTime(bikeTrain.getArrivalTime());
	      copiedBikeTrain.setDepartureTime(bikeTrain.getDepartureTime());
	      copiedBikeTrain.setLeaderEmail(bikeTrain.getLeaderEmail());
	      copiedBikeTrain.setLeaderName(bikeTrain.getLeaderName());
	      copiedBikeTrain.setLeaderPhone(bikeTrain.getLeaderPhone());
	      copiedBikeTrain.setNotes(bikeTrain.getNotes());
	      copiedBikeTrain.setRouteName(bikeTrain.getRouteName());
	      
	      copyToBikeTrainID = createNewBikeTrain(copiedBikeTrain, displayGroupId, 
	          session );
	      
	      tx.commit();
	  	return copyToBikeTrainID;
	  }

}
