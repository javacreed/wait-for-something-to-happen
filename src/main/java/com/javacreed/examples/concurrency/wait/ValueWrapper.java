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

/**
 * Represents a value. This wrapper is used to contain the result obtained, which value may be {@code null}
 *
 * @author Albert Attard
 *
 * @param <E>
 *          the value type
 */
public class ValueWrapper<E> implements OutcomeWrapper<E> {

  /** The computation value (which may be {@code null}) */
  private final E value;

  /**
   * Creates an instance of this class
   *
   * @param value
   *          the computation value (which may be {@code null})
   */
  public ValueWrapper(final E value) {
    this.value = value;
  }

  @Override
  public E get() throws ExecutionException {
    return value;
  }
}