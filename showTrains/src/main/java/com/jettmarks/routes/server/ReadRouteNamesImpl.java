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
 * Created April 2009
 */
package com.jettmarks.routes.server;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.jettmarks.routes.client.service.ReadRouteNames;
import com.jettmarks.routes.common.LocalDrive;
import com.jettmarks.routes.json.JsonRouteFromRwGPS;
import com.jettmarks.routes.server.common.RemoteServiceServletSeparatePaths;
import com.jettmarks.routes.server.rtsrc.RouteSource;
import com.jettmarks.routes.server.rtsrc.RouteSourceBase;

public class ReadRouteNamesImpl extends RemoteServiceServletSeparatePaths implements
                                                            ReadRouteNames
{
  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(ReadRouteNamesImpl.class);

  private static final long serialVersionUID = 3030395750170280992L;

  /**
   * @see com.jettmarks.routes.client.service.ReadRouteNames#getRouteNames(java.lang.String)
   */
  public String[] getRouteNames(String routeSourceName)
  {
    RouteSource routeSource = RouteSourceBase.getInstance(routeSourceName);
    String sUrl = routeSource.getFeedURL();
    // TODO - Get rid of this "case" statement (getRouteNames) (Factory pattern)
    if (sUrl == null)
    {
      logger.debug("getRouteNames(String) - sUrl=" + sUrl + " (local)");
      return getLocalRouteNames();
    }

    if (sUrl.contains("ridewithgps"))
    {
      return getRouteNamesFromRwGPS(sUrl);
    }
    
    if (sUrl.contains("rss"))
    {
      logger.debug("getRouteNames(String) - sUrl=" + sUrl + " (RSS Feed)");
      return getRouteNamesFromBikelyRSS(sUrl);
    }
    else
    {
      logger.debug("getRouteNames(String) - sUrl=" + sUrl + " (Not Recognized)");
      return getRouteNamesFromBikelyListby(sUrl);
    }
  }

  /**
   * Experimental at this time.
   * 
   * Considering implementation in routeMgr project.
   * 
   * @param sUrl
   * @return
   */
  public String[] getRouteNamesFromRwGPS(String sUrl)
  {
    String[] names = null;
    JsonRouteFromRwGPS[] workingList = null;
    URL url;
    try
    {
      url = new URL(sUrl);
      ObjectMapper mapper = new ObjectMapper();
      workingList = mapper.readValue(url, JsonRouteFromRwGPS[].class);
      names = new String[workingList.length];
      int i=0;
      for (JsonRouteFromRwGPS route : workingList) {
        names[i++] = route.getId()+"";
      }
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (JsonParseException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return names;
  }

  /**
   * @see com.jettmarks.routes.client.service.ReadRouteNames#getLocalRouteNames()
   */
  public String[] getLocalRouteNames()
  {
    String tag = System.getProperty("show.tag", "master");
    return getLocalRouteNames(tag);
  }

  /**
   * @see com.jettmarks.routes.client.service.ReadRouteNames#getLocalRouteNames(java.lang.String)
   */
  public String[] getLocalRouteNames(String tag)
  {
    return getRouteNamesFromLocalDirectory(LocalDrive.getRoutePathLocal()
                                           + tag
                                           + "/");
  }

  /**
   * @see com.jettmarks.routes.client.service.ReadRouteNames#getLocalOverlayNames()
   */
  public String[] getLocalOverlayNames()
  {
    return getOverlayNames(LocalDrive.getRoutePathLocal() + "master/");
  }

  /**
   * 
   * @param string
   * @return
   */
  private String[] getOverlayNames(String sUrl)
  {
    FilenameFilter filter = new OverlayFilenameFilter();
    return getNamesFromLocalDirectory(sUrl, filter);
  }

  /**
   * Assumes this is a Bikely User Account at this time.
   * 
   * @see com.jettmarks.routes.client.service.ReadRouteNames#getUsersRouteNames(java.lang.String)
   */
  public String[] getUsersRouteNames(String user)
  {
    return getRouteNamesFromBikelyListby("http://www.bikely.com/listpaths/by/"
                                         + user);
  }

  /**
   * Reads local directory for all files with the extension .gpx and returns the
   * base names without the extension.
   * 
   * @param url
   *          - Path without the protocol.
   * @return String[] of route names or null if no matching names.
   */
  public String[] getRouteNamesFromLocalDirectory(String url)
  {
    FilenameFilter filter = new GPXFilenameFilter();
    return getNamesFromLocalDirectory(url, filter);
  }

  /**
   * 
   * @param url
   * @param filter
   * @return Array of the file names in this directory without the extension,
   * or null if there are no files.
   */
  private String[] getNamesFromLocalDirectory(String url, FilenameFilter filter)
  {
    File directory = new File(url);
    // This grabs files with extension; we want name without extension
    String[] files = directory.list(filter);
    if (files == null)
    {
      return null;
    }
    String[] names = new String[files.length];
    for (int i = 0; i < files.length; i++)
    {
      names[i] = files[i].substring(0, files[i].lastIndexOf('.'));
    }
    return names;
  }

  /**
   * @see com.jettmarks.routes.client.service.ReadRouteNames#getRouteNames(java.lang.String)
   */
  private String[] getRouteNamesFromBikelyListby(String sUrl)
  {
    WebClient wc = new WebClient();
    wc.setJavaScriptEnabled(false);
    NodeList tableList = null;
    List<String> list = new ArrayList<String>();
    HtmlPage page;
    try
    {
      page = wc.getPage(sUrl);
      tableList = page.getElementsByTagName("table");
      HtmlTable routeTable = (HtmlTable) tableList.item(0);
      List<HtmlTableRow> routeTitleList = routeTable.getRows();
      for (java.util.Iterator<HtmlTableRow> iter = routeTitleList.iterator(); iter.hasNext();)
      {
        HtmlTableRow row = iter.next();
        HtmlTableCell cell = row.getCell(0);
        String classValue = cell.getAttribute("class");
        if ("rtitle".equals(classValue))
        {
          // Include this row's value
          Iterator<HtmlElement> childIter = cell.getChildElements().iterator();
          while (childIter.hasNext())
          {
            HtmlElement element = childIter.next();
            if (element.hasAttribute("href"))
            {
              String link = ((HtmlAnchor) (element)).getHrefAttribute();
              String routeName = link.substring(link.lastIndexOf('/') + 1);
              list.add(routeName);
            }
          }
        }
      }
    }
    catch (FailingHttpStatusCodeException e)
    {
      e.printStackTrace();
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    String result[] = new String[list.size()];
    for (int i = 0; i < list.size(); i++)
    {
      result[i] = list.get(i);
    }
    return result;
  }

  /**
   * 
   * @param url
   * @return
   */
  private String[] getRouteNamesFromBikelyRSS(String sUrl)
  {
    String[] result = new String[0];
    ArrayList<String> routeNames = new ArrayList<String>();
    XMLReader producer;
    DefaultHandler consumer;

    // Get an instance of the default XML parser class
    try
    {
      producer = XMLReaderFactory.createXMLReader();
    }
    catch (SAXException e)
    {
      System.err.println("Can't get parser, check configuration: "
                         + e.getMessage());
      return null;
    }
    try
    {

      // Get a consumer for all the parser events
      consumer = new RSSResourceHandler(routeNames);

      // Connect the most important standard handler
      producer.setContentHandler(consumer);

      // Arrange error handling
      producer.setErrorHandler(consumer);
    }
    catch (Exception e)
    {
      // Consumer setup can uncover errors,
      // though this simple one shouldn't
      System.err.println("Can't set up consumers:" + e.getMessage());
      return null;
    }

    try
    {
      producer.parse(sUrl);
    }
    catch (IOException e)
    {
      System.err.println("I/O error: ");
      e.printStackTrace();
    }
    catch (SAXException e)
    {
      System.err.println("Parsing error: ");
      e.printStackTrace();
    }

    result = routeNames.toArray(result);

    return result;
  }

  /**
   * 
   * @author jett
   * 
   */
  public class RSSResourceHandler extends DefaultHandler
  {
    ArrayList<String> routeNameList = null;

    /**
     * @param routeNames
     */
    public RSSResourceHandler(ArrayList<String> routeNames)
    {
      routeNameList = routeNames;
    }

    @Override
    public void startElement(String arg0,
                             String arg1,
                             String arg2,
                             Attributes arg3) throws SAXException
    {
      super.startElement(arg0, arg1, arg2, arg3);
      if (arg1.equals("li"))
      {
        for (int i = 0; i < arg3.getLength(); i++)
        {
          String fullURL = arg3.getValue(i);
          routeNameList.add(fullURL.substring(fullURL.lastIndexOf('/') + 1));
        }
      }
    }

  }

  /**
   * 
   * @author jett
   * 
   */
  public class GPXFilenameFilter implements FilenameFilter
  {
    /**
     * @see java.io.FilenameFilter#accept(java.io.File)
     */
    public boolean accept(File arg0, String name)
    {
      if ("template.gpx".equals(name))
      {
        return false;
      }

      return name.endsWith(".gpx");
    }

  }

  /**
   * Recognizes .kml and .kmz files.
   * 
   * @author jett
   */
  public class OverlayFilenameFilter implements FilenameFilter
  {
    /**
     * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
     */
    public boolean accept(File arg0, String name)
    {
      if (name.endsWith(".kml") || name.endsWith(".kmz"))
      {
        return true;
      }
      else
      {
        return false;
      }
    }
  
  }
}
