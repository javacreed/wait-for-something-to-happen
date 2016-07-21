/*
 * #%L
 * Wait for Something to Happen
 * %%
 * Copyright (C) 2012 - 2016 Java Creed
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.javacreed.examples.concurrency.wait;

import org.junit.Test;

/**
 * The {@link WaitForValue} has two options, {@link WaitForValue#succeeded(Object)} or
 * {@link WaitForValue#failed(Exception)}. If both methods are invoked, an {@link IllegalStateException} is thrown as
 * either it succeeded or it failed. but not both as these are mutually exclusive.
 *
 * @author Albert Attard
 */
public class IllegalStateTest {

  /**
   * Invokes {@link WaitForValue#succeeded(Object)} after the {@link WaitForValue#failed(Exception)}, which cause an
   * {@link IllegalStateException}
   *
   * @throws Exception
   */
  @Test(expected = IllegalStateException.class)
  public void testFailSucceed() throws Exception {
    final WaitForValue<String> wait = new WaitForValue<>();
    wait.failed(new RuntimeException());
    wait.succeeded("Hello");
  }

  /**
   * Invokes {@link WaitForValue#failed(Exception)} after the {@link WaitForValue#succeeded(Object)}, which cause an
   * {@link IllegalStateException}
   *
   * @throws Exception
   */
  @Test(expected = IllegalStateException.class)
  public void testSucceedFail() throws Exception {
    final WaitForValue<String> wait = new WaitForValue<>();
    wait.succeeded("Hello");
    wait.failed(new RuntimeException());
  }
}