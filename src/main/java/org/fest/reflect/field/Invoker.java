/*
 * Created on Feb 26, 2011
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2011 the original author or authors.
 */
package org.fest.reflect.field;

import java.lang.reflect.Field;

import org.fest.reflect.exception.ReflectionError;

/**
 * Reads and writes to a field.
 * @param <T> the type of the field to access.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public interface Invoker<T> {

  /**
   * Reads the value of the field.
   * @return the value of the field.
   * @throws ReflectionError if the value of the field cannot be read.
   */
  T get();

  /**
   * Writes a value to the field.
   * @param value the value to set.
   * @throws ReflectionError if the value cannot be set on the field.
   */
  void set(T value);

  /**
   * Returns the underlying <code>{@link Field}</code>.
   * @return the underlying field.
   */
  Field info();
}
