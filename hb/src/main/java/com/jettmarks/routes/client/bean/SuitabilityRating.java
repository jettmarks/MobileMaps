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
 * Created Oct 20, 2010
 */
package com.jettmarks.routes.client.bean;

/**
 * Values of this Enum match the three defined ratings for a route by color, and 
 * adds a fourth for UNRATED.
 *
 * @author jett
 */
public enum SuitabilityRating {
  UNRATED,
  GREEN,
  YELLOW,
  RED
}
