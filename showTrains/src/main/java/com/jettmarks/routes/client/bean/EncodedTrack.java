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
 * Created Jul 12, 2009
 */
package com.jettmarks.routes.client.bean;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jettmarks.routes.client.common.FacilityType;


/**
 * This is the response to an AJAX request for an Encoded Track.
 * 
 * The serializable manifestation of a route/track.
 * 
 * @author jett
 */
public class EncodedTrack implements IsSerializable
{
  private String encodedPoints;
  private String encodedLevels;
  private String routeName;
  private double distance = 0.0;
  
  // Concession to the v3 Google API which no longer supports encoding
  private double[] lons;
  private double[] lats;
  
  private Double maxLat, minLat, maxLon, minLon;
  
  private Double startLat, startLon;
  private Double endLat, endLon;
  
  /** Holds URL of external site that published this, if that was the source. */
  private String sourceUrl = null;
  
  /** Name by which this track is presented to the user. */
  private String displayName = null;
  
  /** Indicator of the source of the EncodedTrack. */
  private String routeSourceName = null;
  
  /** Hill Category as ranked by Hillarious Ride; zero is unranked. */
  private int hillCategory = 0;
  private Number maxSlope = 0.0;
  
  /** Facility Type as defined on wiki. */
  private int facilityType = FacilityType.UNKNOWN;

  /** BikeTrainRoute as defined on wiki. */
  private BikeTrainDTO bikeTrain;
  
  /** Suitability Rating; null means n/a and UNRATED means just that. */
  private SuitabilityRating rating = null;

  public void setBounds(HashMap<String, Double> bounds)
  {
    maxLat = bounds.get("maxlat");
    minLat = bounds.get("minlat");
    maxLon = bounds.get("maxlon");
    minLon = bounds.get("minlon");
  }

  /**
   * @return the routeSrc
   */
  public String getRouteSourceName()
  {
    return routeSourceName;
  }

  /**
   * @param routeSrc the routeSrc to set
   */
  public void setRouteSourceName(String routeSourceName)
  {
    this.routeSourceName = routeSourceName;
  }

  public Double getMaxLat()
  {
    return maxLat;
  }

  public Double getMinLat()
  {
    return minLat;
  }

  public Double getMaxLon()
  {
    return maxLon;
  }

  public Double getMinLon()
  {
    return minLon;
  }

  public String getEncodedPoints()
  {
    return encodedPoints;
  }

  public void setEncodedPoints(String encodedPoints)
  {
    this.encodedPoints = encodedPoints;
  }

  public String getEncodedLevels()
  { 
    return encodedLevels;
  }

  public void setEncodedLevels(String encodedLevels)
  {
    this.encodedLevels = encodedLevels;
  }

  /**
   * @param routeName the routeName to set
   */
  public void setRouteName(String routeName)
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
   * Constructs a <code>String</code> with all attributes
   * in name = value format.
   *
   * @return a <code>String</code> representation 
   * of this object.
   */
  public String toString()
  {
      final String TAB = "\n  ";
      
      StringBuffer retValue = new StringBuffer();
      
      retValue.append("EncodedTrack ( ")
          .append("routeName = ").append(this.routeName).append(TAB)
          .append("encodedPoints = ").append(this.encodedPoints).append(TAB)
          .append("encodedLevels = ").append(this.encodedLevels).append(TAB)
          .append(" )");
      
      return retValue.toString();
  }

  /**
   * @param distance the distance to set
   */
  public void setDistance(double distance)
  {
    this.distance = distance;
  }

  /**
   * @return the distance
   */
  public double getDistance()
  {
    return distance;
  }

  /**
   * @param hillCategory the hillCategory to set
   */
  public void setHillCategory(int hillCategory)
  {
    this.hillCategory = hillCategory;
  }

  /**
   * @return the hillCategory
   */
  public int getHillCategory()
  {
    return hillCategory;
  }

  /**
   * @param facilityType the facilityType to set
   */
  public void setFacilityType(int facilityType)
  {
    this.facilityType = facilityType;
  }

  /**
   * @return the facilityType
   */
  public int getFacilityType()
  {
    return facilityType;
  }

  /**
   * @param maxSlope the maxSlope to set
   */
  public void setMaxSlope(Number maxSlope)
  {
    this.maxSlope = maxSlope;
  }

  /**
   * @return the maxSlope
   */
  public Number getMaxSlope()
  {
    return maxSlope;
  }

  /**
   * @return
   */
  public BikeTrainDTO getBikeTrain()
  {
    return bikeTrain;
  }

  /**
   * @param bikeTrain the bikeTrain to set
   */
  public void setBikeTrain(BikeTrainDTO bikeTrain)
  {
    this.bikeTrain = bikeTrain;
  }

  /**
   * @return the startLat
   */
  public Double getStartLat()
  {
    return startLat;
  }

  /**
   * @param startLat the startLat to set
   */
  public void setStartLat(Double startLat)
  {
    this.startLat = startLat;
  }

  /**
   * @return the startLon
   */
  public Double getStartLon()
  {
    return startLon;
  }

  /**
   * @param startLon the startLon to set
   */
  public void setStartLon(Double startLon)
  {
    this.startLon = startLon;
  }

  /**
   * @return the endLat
   */
  public Double getEndLat()
  {
    return endLat;
  }

  /**
   * @param endLat the endLat to set
   */
  public void setEndLat(Double endLat)
  {
    this.endLat = endLat;
  }

  /**
   * @return the endLon
   */
  public Double getEndLon()
  {
    return endLon;
  }

  /**
   * @param endLon the endLon to set
   */
  public void setEndLon(Double endLon)
  {
    this.endLon = endLon;
  }

  /**
   * @return the rating
   */
  public SuitabilityRating getRating()
  {
    return rating;
  }

  /**
   * @param rating the rating to set
   */
  public void setRating(SuitabilityRating rating)
  {
    this.rating = rating;
  }

  /**
   * @return the sourceUrl
   */
  public String getSourceUrl()
  {
    return sourceUrl;
  }

  /**
   * @param sourceUrl the sourceUrl to set
   */
  public void setSourceUrl(String sourceUrl)
  {
    this.sourceUrl = sourceUrl;
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

  /**
   * @return the lons
   */
  public double[] getLons()
  {
    return lons;
  }

  /**
   * @param lons the lons to set
   */
  public void setLons(double[] lons)
  {
    this.lons = lons;
  }

  /**
   * @return the lats
   */
  public double[] getLats()
  {
    return lats;
  }

  /**
   * @param lats the lats to set
   */
  public void setLats(double[] lats)
  {
    this.lats = lats;
  }

}
