/*
 * Created on Feb 25, 2011
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
package org.fest.reflect.beanproperty;

import java.beans.*;

import org.fest.reflect.exception.ReflectionError;
import org.fest.reflect.reference.TypeRef;

/**
 * Implements a fluent interface for accessing JavaBeans properties.
 * @param <T> the type of the property to access.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Properties<T> implements Name<T>, Target<T>, Invoker<T> {

  /**
   * Sets the type of the property to access.
   * @param <T> the generic type of the property to access.
   * @param type the type of the property to access.
   * @return the next object in the fluent interface.
   * @throws NullPointerException if the given type is {@code null}.
   */
  public static <T> Name<T> propertyOfType(Class<T> type) {
    if (type == null) throw new NullPointerException("The type of the property to access should not be null");
    return new Properties<T>(type);
  }

  /**
   * Sets the type of the property to access.
   * @param <T> the generic type of the property to access.
   * @param typeRef a reference to the type of the property to access. Used to overcome type erasure in generics.
   * @return the next object in the fluent interface.
   * @throws NullPointerException if the given type reference is {@code null}.
   */
  public static <T> Name<T> propertyOfType(TypeRef<T> typeRef) {
    if (typeRef == null)
      throw new NullPointerException("The reference to the type of the property to access should not be null");
    return new Properties<T>(typeRef.rawType());
  }

  private final Class<T> type;
  private String name;
  private Object target;
  private PropertyDescriptor descriptor;

  private Properties(Class<T> type) {
    this.type = type;
  }

  /** {@inheritDoc} */
  public Target<T> withName(String name) {
    if (name == null) throw new NullPointerException("The name of the property to access should not be null");
    if (name.length() == 0)
      throw new IllegalArgumentException("The name of the property to access should not be empty");
    this.name = name;
    return this;
  }

  /** {@inheritDoc} */
  public Invoker<T> in(Object target) {
    if (target == null) throw new NullPointerException("The target object should not be null");
    this.target = target;
    findPropertyDescriptor();
    return this;
  }

  private void findPropertyDescriptor() {
    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());
      for (PropertyDescriptor d : beanInfo.getPropertyDescriptors()) {
        if (!name.equals(d.getName())) continue;
        descriptor = checkRightType(d);
        break;
      }
    } catch (IntrospectionException e) {
      String message = String.format("Unable to get BeanInfo for type %s", target.getClass().getName());
      throw new ReflectionError(message, e);
    }
    if (descriptor == null) throw cannotFindProperty();
  }

  private PropertyDescriptor checkRightType(PropertyDescriptor d) {
    Class<?> actualType = d.getPropertyType();
    if (!type.isAssignableFrom(actualType)) throw wrongType(actualType);
    return d;
  }

  private ReflectionError wrongType(Class<?> actual) {
    String format = "The type of the property '%s' in %s should be <%s> but was <%s>";
    String message = String.format(format, name, target.getClass().getName(), type.getName(), actual.getName());
    throw new ReflectionError(message);
  }

  private ReflectionError cannotFindProperty() {
    String message = String.format("Unable to find property '%s' in %s", name, target.getClass().getName());
    return new ReflectionError(message);
  }

  /** {@inheritDoc} */
  public T get() {
    try {
      return type.cast(descriptor.getReadMethod().invoke(target));
    } catch (Exception e) {
      String message = String.format("Unable to obtain the value in property '%s'", name);
      throw new ReflectionError(message, e);
    }
  }

  /** {@inheritDoc} */
  public void set(T value) {
    try {
      descriptor.getWriteMethod().invoke(target, value);
    } catch (Exception e) {
      String message = String.format("Unable to update the value in property '%s'", name);
      throw new ReflectionError(message, e);
    }
  }

  /** {@inheritDoc} */
  public PropertyDescriptor info() {
    return descriptor;
  }
}
