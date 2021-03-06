/*
 * Created on Feb 25, 2011
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 * 
 * Copyright @2011 the original author or authors.
 */
package org.fest.reflect.javabean;

/**
 * Holds the name of the property to access.
 * @param <T> the type of the property to access.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public interface Name<T> {

  /**
   * Sets the name of the property to access.
   * @param name the name of the property.
   * @return the next object in the fluent interface.
   * @throws NullPointerException if the given name is {@code null}.
   * @throws IllegalArgumentException if the given name is empty.
   */
  Target<T> withName(String name);
}
