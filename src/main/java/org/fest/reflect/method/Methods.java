/*
 * Created on Feb 26, 2011
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
package org.fest.reflect.method;

import org.fest.reflect.reference.TypeRef;

/**
 * Fluent interface for invoking methods.
 * <p>
 * Examples:
 * 
 * <pre>
 * // import static {@link org.fest.reflect.method.Methods org.fest.reflect.method.Methods}.*;
 *
 * // Equivalent to call 'person.setName("Luke")'
 * {@link org.fest.reflect.method.Methods#methodWithName(String) methodWithName}("setName").{@link org.fest.reflect.method.ParameterTypes#withParameterTypes(Class...) withParameterTypes}(String.class)
 *                          .{@link org.fest.reflect.method.Target#in(Object) in}(person)
 *                          .{@link org.fest.reflect.method.Invoker#invoke(Object...) invoke}("Luke");
 *
 * // Equivalent to call 'person.concentrate()'
 * {@link org.fest.reflect.method.Methods#methodWithName(String) methodWithName}("concentrate").{@link org.fest.reflect.method.ParameterTypes#withNoParameters() withNoParameters}()
 *                              .{@link org.fest.reflect.method.Target#in(Object) in}(person)
 *                              .{@link org.fest.reflect.method.Invoker#invoke(Object...) invoke}();
 *
 * // Equivalent to call 'person.getName()'
 * String name = {@link org.fest.reflect.method.Methods#methodWithReturnType(Class) methodWithReturnType}(String.class).{@link org.fest.reflect.method.Name#withName(String) withName}("getName")
 *                                                 .{@link org.fest.reflect.method.ParameterTypes#withNoParameters() withNoParameters}()
 *                                                 .{@link org.fest.reflect.method.Target#in(Object) in}(person)
 *                                                 .{@link org.fest.reflect.method.Invoker#invoke(Object...) invoke}();
 *
 * // Equivalent to call 'Jedi.class.setCommonPower("Jump")'
 * {@link org.fest.reflect.method.Methods#methodWithName(String) methodWithName}("setCommonPower").{@link org.fest.reflect.method.ParameterTypes#withParameterTypes(Class...) withParameterTypes}(String.class)
 *                                 .{@link org.fest.reflect.method.Target#in(Class) in}(Jedi.class)
 *                                 .{@link org.fest.reflect.method.Invoker#invoke(Object...) invoke}("Jump");
 *
 * // Equivalent to call 'Jedi.class.addPadawan()'
 * {@link org.fest.reflect.method.Methods#methodWithName(String) methodWithName}("addPadawan").{@link org.fest.reflect.method.ParameterTypes#withNoParameters() withNoParameters}()
 *                             .{@link org.fest.reflect.method.Target#in(Class) in}(Jedi.class)
 *                             .{@link org.fest.reflect.method.Invoker#invoke(Object...) invoke}();
 *
 * // Equivalent to call 'Jedi.class.commonPowerCount()'
 * int count = {@link org.fest.reflect.method.Methods#methodWithReturnType(Class) methodWithReturnType}(int.class).{@link org.fest.reflect.method.Name#withName(String) withName}("getName")
 *                                            .{@link org.fest.reflect.method.ParameterTypes#withNoParameters() withNoParameters}()
 *                                            .{@link org.fest.reflect.method.Target#in(Class) in}(Jedi.class)
 *                                            .{@link org.fest.reflect.method.Invoker#invoke(Object...) invoke}();
 * </pre>
 * </p>
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Methods {

  /**
   * Sets the return type of the method to invoke.
   * @param <T> the generic type of the method to invoke.
   * @param type the type of the method to invoke.
   * @return the next object in the fluent interface.
   * @throws NullPointerException if the given type is {@code null}.
   */
  public static <T> Name<T> methodWithReturnType(Class<T> type) {
    return new FluentMethod<T>(type);
  }

  /**
   * Sets the return type of the method to invoke.
   * @param <T> the generic type of the method to invoke.
   * @param typeRef a reference to the type of the property to access. Used to overcome type erasure in generics.
   * @return the next object in the fluent interface.
   * @throws NullPointerException if the given type reference is {@code null}.
   */
  public static <T> Name<T> methodWithReturnType(TypeRef<T> typeRef) {
    return new FluentMethod<T>(typeRef);
  }

  /**
   * Sets the name of the method to invoke, indicating that the return type of such method is {@code void}. This is a shortcut
   * for:
   * 
   * <pre>
   * methodWithReturnType(Void.class).withName("setAge")
   * </pre>
   * @param name the name of the method to invoke.
   * @return the next object in the fluent interface.
   * @throws NullPointerException if the given name is {@code null}.
   * @throws IllegalArgumentException if the given name is empty.
   */
  public static ParameterTypes<Void> methodWithName(String name) {
    return new FluentMethod<Void>(Void.class).withName(name);
  }

  private Methods() {}
}
