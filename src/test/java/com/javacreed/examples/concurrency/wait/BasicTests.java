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

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Carries out two basic tests, succeed and failed, involving only one thread.
 *
 * @author Albert Attard
 */
public class BasicTests {

  /**
   * Test the failed condition where an {@link ExecutionException} is thrown when the {@link WaitForValue#get()} is
   * invoked.
   *
   * @throws ExecutionException
   *           is expected to be thrown
   * @throws Exception
   *           if an expected error occurs
   */
  @Test(expected = ExecutionException.class)
  public void testFailed() throws Exception {
    final WaitForValue<String> wait = new WaitForValue<>();
    wait.failed(new RuntimeException());
    wait.get();
  }

  /**
   * Tests the succeeded condition. In the case the {@link WaitForValue#get()} should simply return the set value.
   *
   * @throws Exception
   *           if an expected error occurs
   */
  @Test
  public void testSucceeded() throws Exception {
    final WaitForValue<String> wait = new WaitForValue<>();
    wait.succeeded("hello");
    Assert.assertEquals("hello", wait.get());
  }
}