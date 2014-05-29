/**
 *   Copyright 2010 Jett Marks
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
 * Created Aug 10, 2010
 */
package com.jettmarks.routes.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.jettmarks.db.HibernateUtil;
import com.jettmarks.routes.client.bean.DisplayElementDTO;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;
import com.jettmarks.routes.client.bean.EncodedTrack;
import com.jettmarks.routes.client.bean.SuitabilitySegmentDTO;
import com.jettmarks.routes.client.rep.BikeTrainDE;
import com.jettmarks.routes.client.rep.RouteOnlyDE;
import com.jettmarks.routes.client.rep.SuitabilitySegmentDE;
import com.jettmarks.routes.client.service.GetDisplayElements;
import com.jettmarks.routes.server.bean.BikeTrain;
import com.jettmarks.routes.server.bean.BikeTrainDAO;
import com.jettmarks.routes.server.bean.BikeTrainWrapper;
import com.jettmarks.routes.server.bean.DisplayElement;
import com.jettmarks.routes.server.bean.DisplayElementWrapper;
import com.jettmarks.routes.server.bean.DisplayGroup;
import com.jettmarks.routes.server.bean.DisplayGroupDAO;
import com.jettmarks.routes.server.bean.DisplayGroupWrapper;
import com.jettmarks.routes.server.bean.SuitabilitySegment;
import com.jettmarks.routes.server.bean.SuitabilitySegmentDAO;
import com.jettmarks.routes.server.bean.SuitabilitySegmentWrapper;
import com.jettmarks.routes.server.bt.RouteOnlyBikeTrain;
import com.jettmarks.routes.server.common.RemoteServiceServletSeparatePaths;

/**
 * Description.
 *
 * @author jett
 */
public class GetDisplayElementsImpl extends RemoteServiceServletSeparatePaths implements
                                                                GetDisplayElements
{
  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(GetDisplayElementsImpl.class);

  /**
   * 
   */
  private static final long serialVersionUID = 2742474381457831304L;

  private List<RouteOnlyDE> routeOnlyDEList = null;

  /**
   * @see com.jettmarks.routes.client.service.GetDisplayElements#getElementList
   *   (com.jettmarks.routes.client.ui.DisplayGroupDTO)
   */
  @SuppressWarnings("unchecked")
  public List<DisplayElementDTO> getElementList(DisplayGroupDTO displayGroupDto)
  {
    logger.debug("getElementList(DisplayGroup) - start");

    List<DisplayElementDTO> deList = new ArrayList<DisplayElementDTO>();
    if ("test".equals(displayGroupDto.getDisplayName()))
    {
      for (int i=1; i<=3; i++)
      {
        DisplayElementDTO displayElement = new DisplayElementDTO();
        displayElement.setId(i * 3);
	      deList.add(displayElement);
      }
    }
    else
    {
      Session session = HibernateUtil.getSession();
      DisplayGroupDAO dgDao = new DisplayGroupDAO(session);
      DisplayGroup dgExample = DisplayGroupWrapper.getDisplayGroup(displayGroupDto);
      List<DisplayGroup> dgList = dgDao.findByExample(dgExample);
      if (dgList == null || dgList.size() == 0)
      {
        logger.debug("getElementList(DisplayGroup) - end");
		    session.close();
        return null;
      }
      
      DisplayGroup dgFromDB = dgList.get(0);
      Set<DisplayElement> elements = dgFromDB.getElements();
      Set<DisplayElementDTO> dtoElements = new HashSet<DisplayElementDTO>();
      for (DisplayElement de : elements) {
    	 DisplayElementDTO deDto = DisplayElementWrapper.getDisplayElementDTO(de); 
    	 dtoElements.add(deDto);
      }
      
      deList.addAll(dtoElements);
	    session.close();
    }

    logger.debug("getElementList(DisplayGroup) - end");
	  return deList;
  }
  
  public BikeTrainDE getBikeTrain(DisplayElementDTO displayElement)
  {
    logger.debug("getBikeTrain(DisplayElement) - start");

    // Retrieve BikeTrain from DB
    Session session = HibernateUtil.getSession();
    BikeTrainDAO btDao = new BikeTrainDAO(session);
    BikeTrain bikeTrain = btDao.findById(displayElement.getSourceId());
    
    // Retrieve EncodedTrack
    String tags[] = new String[1];
    tags[0] = "bikeTrain";    // All bike trains are stored here
    GetRouteImpl getRouteImpl = new GetRouteImpl();
    EncodedTrack et = getRouteImpl.getEncodedTrack(bikeTrain.getRouteName(), 
        "Local Drive", tags);
    
    // Assemble new Display Element for a Bike Train
    BikeTrainDE btde = new BikeTrainDE();
    btde.setBikeTrain(BikeTrainWrapper.getBikeTrainDTO(bikeTrain));
    btde.setEncodedTrack(et);
    session.close();

    logger.debug("getBikeTrain(DisplayElement) - end");
    return btde;
  }

  /* (non-Javadoc)
   * @see com.jettmarks.routes.client.service.GetDisplayElements#getSuitabilitySegment(com.jettmarks.routes.client.bean.DisplayElement)
   */
  public SuitabilitySegmentDE getSuitabilitySegment(DisplayElementDTO displayElement)
  {
    logger.debug("getSuitabilitySegment(DisplayElement) - start");
    
    // Retrieve SuitabilitySegment from DB
    Session session = HibernateUtil.getSession();
    SuitabilitySegmentDAO ssDao = new SuitabilitySegmentDAO(session);
    SuitabilitySegment sSegment = ssDao.findById(displayElement.getSourceId());
    
    // Retrieve EncodedTrack
    String tags[] = new String[1];
    tags[0] = "suitMap";
    GetRouteImpl getRouteImpl = new GetRouteImpl();
    EncodedTrack et = getRouteImpl.getEncodedTrack(sSegment.getRouteName(), 
        "Local Drive", tags );
    // this is what allows RouteFactory to distinguish this Display Element. 
    et.setRating(sSegment.getRating());
    
    // Assemble new Display Element for SuitabilitySegment
    SuitabilitySegmentDTO ssDto = SuitabilitySegmentWrapper.getSuitabilitySegment(sSegment);
    SuitabilitySegmentDE ssde = new SuitabilitySegmentDE(ssDto);
    ssde.setEncodedTrack(et);
    session.close();

    logger.debug("getSuitabilitySegment(DisplayElement) - end");
    return ssde;
  }

  /**
   * Uses the RouteOnlyBikeTrain class to help find the routes that are not
   * associated with a BikeTrain, but we're interested in making into a Bike 
   * Train.
   * 
   * @see com.jettmarks.routes.client.service.GetDisplayElements#getRouteOnlyList()
   */
  public List<RouteOnlyDE> getRouteOnlyList()
  {
    RouteOnlyBikeTrain roBT = new RouteOnlyBikeTrain();
    routeOnlyDEList = new ArrayList<RouteOnlyDE>();
    
//    DisplayGroupDTO dgFall = new DisplayGroupDTO();
//    dgFall.setDisplayName("btFall2010");
//    DisplayGroupDTO dgSummer = new DisplayGroupDTO();
//    dgSummer.setDisplayName("btSummer2010");
//    DisplayGroupDTO dgWinter = new DisplayGroupDTO();
//    dgWinter.setDisplayName("btWinter2011");
//    DisplayGroupDTO dgSpring = new DisplayGroupDTO();
//    dgSpring.setDisplayName("btSpring2011");
//    DisplayGroupDTO dgSummer11 = new DisplayGroupDTO();
//    dgSummer11.setDisplayName("btSummer2011");
//    DisplayGroupDTO dgFall11 = new DisplayGroupDTO();
//    dgFall11.setDisplayName("btFall2011");
//    DisplayGroupDTO dgSpring12 = new DisplayGroupDTO();
//    dgSpring12.setDisplayName("btSpring2012");
    
    // Get full set of Bike Train Display Groups
    List<DisplayGroupDTO> dgDtoList = new ArrayList<DisplayGroupDTO>();
    GetTagsImpl getTagsImpl = new GetTagsImpl();
    for (DisplayGroupDTO dgDto : getTagsImpl.getDisplayGroups()) {
      dgDtoList.add(dgDto);
    }
    
    GetDisplayElementsImpl gdeImpl = new GetDisplayElementsImpl();
    for (DisplayGroupDTO dgDto : dgDtoList) {
      if (dgDto != null) {
        roBT.addDisplayElements(gdeImpl.getElementList(dgDto));
      }
    }
    
    // TODO: This only handles three quarters; need to roll through added ones too
//    List<DisplayElementDTO> displayElementList = gdeImpl.getElementList(dgSummer);
//    roBT.addDisplayElements(displayElementList);
//    displayElementList = gdeImpl.getElementList(dgFall);
//    roBT.addDisplayElements(displayElementList);
//    displayElementList = gdeImpl.getElementList(dgWinter);
//    roBT.addDisplayElements(displayElementList);
//    displayElementList = gdeImpl.getElementList(dgSpring);
//    roBT.addDisplayElements(displayElementList);
//    displayElementList = gdeImpl.getElementList(dgSummer11);
//    roBT.addDisplayElements(displayElementList);
//    displayElementList = gdeImpl.getElementList(dgFall11);
//    roBT.addDisplayElements(displayElementList);
//    displayElementList = gdeImpl.getElementList(dgSpring12);
//    roBT.addDisplayElements(displayElementList);
    
    ReadRouteNamesImpl rrnImpl = new ReadRouteNamesImpl();
    String[] rns = rrnImpl.getLocalRouteNames("bikeTrain");
    List<String> routeNames = new ArrayList<String>(rns.length);
    for (String rn : rns)
    {
      routeNames.add(rn);
    }
    roBT.addRouteNames(routeNames);
    
    for (String routeName : roBT.getRoutesWithoutBikeTrains())
    {
      routeOnlyDEList.add(new RouteOnlyDE(routeName));
    }
    return routeOnlyDEList;
  }

}
