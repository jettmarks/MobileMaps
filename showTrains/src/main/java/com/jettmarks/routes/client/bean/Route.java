/**
 *   Copyright 2009 Jett Marks
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
 * Created on Mar 26, 2009
 */
package com.jettmarks.routes.client.bean;

import java.util.HashMap;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.overlays.Polyline;
import com.google.gwt.maps.client.overlays.PolylineOptions;
import com.jettmarks.routes.client.common.FacilityType;

/**
 * @author jett
 * 
 */
public class Route
{
  private LatLng[] points;
  private LatLngBounds bounds = null;
  protected String name = null;
  protected String description = null;
  protected Polyline polyline = null;
  protected Polyline selectedPolyline = null;
  protected Polyline highlightedPolyline = null;
//  protected EncodedPolyline encodedPolyline = null;
  protected EncodedTrack encodedTrack = null;

  // TODO: These should be on either the map or the request for a group
  protected static Route selectedRoute = null;

  protected double distance;
  private String[] elevations;
  /** Holds link to external source if appropriate; null otherwise. */
  protected String extSourceURL = null;
  /** Human Readable name instead of "ID". */
  protected String displayName = null;
  
  /**
   * Holds the name of the route that goes along with a particular Polyline
   * instance.
   */
  protected static HashMap<Polyline, String> namePerPolyline = new HashMap<Polyline, String>();
  protected int facilityType = FacilityType.UNKNOWN;
  //  protected PolyStyleOptions highlightedStyleOptions = 
  //    PolyStyleOptions.getInstance(); 
  //  protected PolyStyleOptions bikeLaneStyleOptions =
  //    PolyStyleOptions.getInstance();
  //  protected PolyStyleOptions bikeRouteStyleOptions =
  //    PolyStyleOptions.getInstance();
  //  protected PolyStyleOptions segregatedStyleOptions =
  //    PolyStyleOptions.getInstance();
  
    /**
     * Route isn't useful until we can put it on the map.
     */
    public Route(String name)
    {
      this.name = name;
      
      unselectedStyleOptions.setStrokeColor("#0000CC");
      unselectedStyleOptions.setStrokeWeight(4);
      unselectedStyleOptions.setStrokeOpacity(0.5);
      
      highlightedStyleOptions.setStrokeColor("#0000CC");
      highlightedStyleOptions.setStrokeWeight(6);
      highlightedStyleOptions.setStrokeOpacity(0.9);
  //    
  //    segregatedStyleOptions.setColor("#006633");
  //    segregatedStyleOptions.setWeight(4);
  //    segregatedStyleOptions.setOpacity(0.8);
  //    
  //    bikeLaneStyleOptions.setColor("#339966");
  //    bikeLaneStyleOptions.setWeight(4);
  //    bikeLaneStyleOptions.setOpacity(0.8);
  //    
  //    bikeRouteStyleOptions.setColor("#66FFFF");
  //    bikeRouteStyleOptions.setWeight(4);
  //    bikeRouteStyleOptions.setOpacity(0.8);
  //    
  //    selectedStyleOptions.setColor("#CC00CC");
  //    selectedStyleOptions.setWeight(4);
  //    selectedStyleOptions.setOpacity(0.5);
    }

  /**
     * @param mapWidget
     */
    public void setMap(MapWidget mapWidget)
    {
      polyline.setMap(mapWidget);
      highlightedPolyline.setMap(mapWidget);
      highlight(false);
    }

  /**
   * @return the facilityType
   */
  public int getFacilityType()
  {
    return facilityType;
  }

  /**
   * @param facilityType the facilityType to set
   */
  public void setFacilityType(int facilityType)
  {
    this.facilityType = facilityType;
  }

  /** Good candidates to factor out; only need a handful of different styles. */
  protected PolylineOptions unselectedStyleOptions =
    PolylineOptions.newInstance(); 
  protected PolylineOptions highlightedStyleOptions = 
    PolylineOptions.newInstance(); 
//  protected PolyStyleOptions highlightedStyleOptions = 
//    PolyStyleOptions.getInstance(); 
//  protected PolyStyleOptions bikeLaneStyleOptions =
//    PolyStyleOptions.getInstance();
//  protected PolyStyleOptions bikeRouteStyleOptions =
//    PolyStyleOptions.getInstance();
//  protected PolyStyleOptions segregatedStyleOptions =
//    PolyStyleOptions.getInstance();

  /**
   * Takes several parameters from an EncodedTrack to create a Route:
   * <UL>
   * <LI>Name
   * <LI>Distance
   * <LI>The encoded points that can generate a Polyline
   * </UL>
   * 
   * From the list above, this creates:
   * <UL>
   * <LI>polyline - in the default color & width
   * <LI>selectedPolyline - in the default selected color and width
   * <LI>the bounds of this route
   * <LI>highlightedPolyline - something to show up better on a mouse-over.
   * <UL>
   * 
   * @param encodedTrack
   */
  public void setEncodedTrack(EncodedTrack encTrk)
  {
    this.encodedTrack = encTrk; 
    this.name = encodedTrack.getRouteName();
    this.distance = encodedTrack.getDistance();
    setFacilityType(encodedTrack.getFacilityType());
    this.displayName = encodedTrack.getDisplayName();
    this.extSourceURL = encodedTrack.getSourceUrl();

//    int zoomFactor = 2;
//    int numLevels = 18;
    
//    polyline = Polyline.fromEncoded(
//        encodedTrack.getEncodedPoints(), 
//        zoomFactor, 
//        encodedTrack.getEncodedLevels(), 
//        numLevels);
    
    polyline = Polyline.newInstance(unselectedStyleOptions);
    highlightedPolyline = Polyline.newInstance(highlightedStyleOptions);

//    polyline.setPath(EncodingUtils.decodePath(encodedTrack.getEncodedPoints()));
    @SuppressWarnings("unchecked")
    JsArray<LatLng> path = (JsArray<LatLng>) JsArray.createArray();
    double[] lats = encTrk.getLats();
    double[] lons = encTrk.getLons();
    for (int i=0; i<lats.length; i++ ) {
      LatLng point = LatLng.newInstance(lats[i], lons[i]);
      path.set(i, point);
    }
    polyline.setPath(path);
    highlightedPolyline.setPath(path);
    
//    if (facilityType == FacilityType.SEGREGATED)
//      polyline.setStrokeStyle(segregatedStyleOptions);
//    else if (facilityType == FacilityType.BIKE_ROUTE)
//      polyline.setStrokeStyle(bikeLaneStyleOptions);
//    else if (facilityType == FacilityType.BIKE_LANE)
//      polyline.setStrokeStyle(bikeRouteStyleOptions);
//    else 
//	    polyline.setStrokeStyle(unselectedStyleOptions);
//    
//    selectedPolyline = Polyline.fromEncoded( 
//        encodedTrack.getEncodedPoints(), 
//        zoomFactor, 
//        encodedTrack.getEncodedLevels(), 
//        numLevels);
//    selectedPolyline.setStrokeStyle(selectedStyleOptions);
//    
//    highlightedPolyline = Polyline.fromEncoded( 
//        encodedTrack.getEncodedPoints(), 
//        zoomFactor, 
//        encodedTrack.getEncodedLevels(), 
//        numLevels);
//    highlightedPolyline.setStrokeStyle(highlightedStyleOptions);
    
    LatLng ne = LatLng.newInstance(encodedTrack.getMaxLat(), encodedTrack.getMaxLon());
    LatLng sw = LatLng.newInstance(encodedTrack.getMinLat(), encodedTrack.getMinLon());
    
    if (bounds == null) {
      bounds = LatLngBounds.newInstance(sw, ne);
    } else {
      bounds.extend(ne);
      bounds.extend(sw);
    }
   
  }

  public LatLng[] getPoints()
  {
    return points;
  }

  /**
   * Called when we just have a set of points, and doesn't interact with most
   * of the EncodedTrack aspects.
   * 
   * Currently called by the RoutePanel.
   * 
   * @param points
   * @param color
   */
  public void setPoints(LatLng[] points, String color)
  {
    this.points = points;
    
    // Calculate the Bounds
    for (LatLng point : points)
    {
      if( !bounds.contains(point))
      {
        bounds.extend(point);
      }
    }
    
    // Don't bother with putting on the map if we don't have one (save w/o disp)
//    if (MapPanel.getMap() != null)
//    {
//      selectedPolyline = new MyPolyline(points, "#CC00CC", 3, name);
//      MapPanel.getMap().addOverlay(selectedPolyline);
//
//      highlightedPolyline = new MyPolyline(points, color, 5, name);
//      MapPanel.getMap().addOverlay(highlightedPolyline);
//
//      polyline = new MyPolyline(points, color, 3, name);
//      MapPanel.getMap().addOverlay(polyline);
//      addMouseHandlers(polyline);
//
//      deSelect();
//      highlight(false);
//    }
  }

  /**
   * @param p - the Polyline whose mouse movements we want to respond to.
   */
  protected void addMouseHandlers(Polyline p)
  {
    namePerPolyline.put(p, name);
//    
//    p.addPolylineClickHandler(new PolylineClickHandler()
//    {
//      public void onClick(PolylineClickEvent event)
//      {
//        if (selectedRoute != null)
//        {
//          selectedRoute.deSelect();
//        }
//        RoutePanel.select(namePerPolyline.get(event.getSender()));
//      }
//    });

  }

  public String[] getElevations()
  {
    return elevations;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public Polyline getPolyline()
  {
    return polyline;
  }

  /**
   * Turns on or off the highlighting.
   * 
   * @param on
   */
  public void highlight(boolean on)
  {
    // Turn on before turning off
    if (on)
    {
	    highlightedPolyline.setVisible(on);
	    polyline.setVisible(!on);
    }
    else
    {
	    polyline.setVisible(!on);
	    highlightedPolyline.setVisible(on);
    }
  }
  
  public void select()
  {
    polyline.setVisible(false);
    selectedPolyline.setVisible(true);
    selectedRoute = this;

  }

  public void deSelect()
  {
    polyline.setVisible(true);
    if (selectedPolyline != null) {
      selectedPolyline.setVisible(false);
    }
  }

  /**
   * 
   * @return
   */
//  public Overlay getSelectedPolyline()
//  {
//    return selectedPolyline;
//  }

//  public String getGpxContent()
//  {
//    if (this.points.length == 0)
//    {
//      throw (new IllegalArgumentException("Length of this Route is 0; no GPX representation"));
//    }
//    return GPXBuilder.createGPX(this);
//  }

  public LatLngBounds getBounds()
  {
    return bounds;
  }

  public void setBounds(LatLngBounds bounds)
  {
    this.bounds = bounds;
  }

  /**
   * @param elevations
   */
  public void setElevations(String[] elevations)
  {
    this.elevations = elevations;
  }

  /**
   * @return the extSourceURL
   */
  public String getExtSourceURL()
  {
    return extSourceURL;
  }

  /**
   * @param extSourceURL the extSourceURL to set
   */
  public void setExtSourceURL(String extSourceURL)
  {
    this.extSourceURL = extSourceURL;
  }

  /**
   * @return the displayName
   */
  public String getDisplayName()
  {
    return displayName;
  }

  /**
   * @param displayName the displayName to set
   */
  public void setDisplayName(String displayName)
  {
    this.displayName = displayName;
  }

}
