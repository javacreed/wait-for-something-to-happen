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

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Albert Attard
 */
public class SucceededTwoThreadTest {

  @Test(timeout = 10000)
  public void test() throws Exception {
    final WaitForValue<String> wait = new WaitForValue<>();

    final AtomicReference<Exception> error = new AtomicReference<>();

    final Thread setter = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          wait.succeeded("Hello");
        } catch (final Exception e) {
          error.compareAndSet(null, e);
        }
      }
    }, "SETTER-THREAD");

    final AtomicReference<String> value = new AtomicReference<>();
    final Thread getter = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          value.set(wait.get());
        } catch (final Exception e) {
          error.compareAndSet(null, e);
        }
      }
    }, "GETTER-THREAD");
    getter.start();

    Assert.assertNull(value.get());

    setter.start();
    setter.join();

    getter.join();

    if (error.get() != null) {
      throw error.get();
    }

    Assert.assertEquals("Hello", value.get());
  }
}