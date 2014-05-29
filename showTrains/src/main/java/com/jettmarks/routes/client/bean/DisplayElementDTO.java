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
package com.jettmarks.routes.client.bean;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.jettmarks.routes.server.bean.DisplayElement;

/**
 * Represents a generic Element to be displayed on a GWT map, and particularly
 * the route maps.
 * 
 * Expecting this to carry a good bit of the information for how to assemble
 * the element from underlying pieces.  There should be a class named here that
 * can assist in the creation of this element.
 *
 * @author jett
 */
public class DisplayElementDTO implements Serializable, IsSerializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1587572581693079249L;

	Integer id;

	private String className = null;
	private Integer sourceId = null;


	private DisplayElementType type = DisplayElementType.ROUTE;

	/**
	 * 
	 */
	public DisplayElementDTO() {
	}

	/**
	 * @return the className
	 */
	public String getClassName()
	{
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className)
	{
		this.className = className;
	}
	/**
	 * @return the sourceId
	 */
	public Integer getSourceId()
	{
		return sourceId;
	}
	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(Integer sourceId)
	{
		this.sourceId = sourceId;
	}
	/**
	 * @return the id
	 */
	public Integer getId()
	{
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id)
	{
		this.id = id;
	}
	/**
	 * @return the type
	 */
	public DisplayElementType getType()
	{
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(DisplayElementType type)
	{
		this.type = type;
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

		retValue.append("DisplayElement ( ")
		.append(super.toString()).append(TAB)
		.append("id = ").append(this.id).append(TAB)
		.append("className = ").append(this.className).append(TAB)
		.append("sourceId = ").append(this.sourceId).append(TAB)
		.append("type = ").append(this.type).append(TAB)
		.append(" )");

		return retValue.toString();
	}
}
