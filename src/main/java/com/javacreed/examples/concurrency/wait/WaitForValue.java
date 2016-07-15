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

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Albert Attard
 *
 * @param <T>
 */
public class WaitForValue<T> {

  /** */
  private final CountDownLatch latch = new CountDownLatch(1);

  /** */
  private final AtomicReference<OutcomeWrapper<T>> outcomeWrapper = new AtomicReference<OutcomeWrapper<T>>();

  /**
   *
   * @param e
   * @throws IllegalStateException
   */
  public void failed(final Exception e) throws IllegalStateException {
    setOutcome(new ErrorWrapper<T>(e));
  }

  /**
   *
   * @return
   * @throws InterruptedException
   * @throws ExecutionException
   */
  public T get() throws InterruptedException, ExecutionException {
    latch.await();
    return outcomeWrapper.get().get();
  }

  /**
   *
   * @param time
   * @param timeUnit
   * @return
   * @throws InterruptedException
   * @throws ExecutionException
   * @throws TimeoutException
   */
  public T get(final long time, final TimeUnit timeUnit)
      throws InterruptedException, ExecutionException, TimeoutException {
    if (false == latch.await(time, timeUnit)) {
      throw new TimeoutException();
    }

    return outcomeWrapper.get().get();
  }

  /**
   *
   * @param wrapper
   * @throws NullPointerException
   * @throws IllegalStateException
   */
  private void setOutcome(final OutcomeWrapper<T> wrapper) throws NullPointerException, IllegalStateException {
    Objects.requireNonNull(wrapper);
    if (false == this.outcomeWrapper.compareAndSet(null, wrapper)) {
      throw new IllegalStateException("The outcome value is already set");
    }
    latch.countDown();
  }

  /**
   *
   * @param value
   * @throws IllegalStateException
   */
  public void succeeded(final T value) throws IllegalStateException {
    setOutcome(new ValueWrapper<T>(value));
  }
}