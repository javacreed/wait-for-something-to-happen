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
 *
 * @author Albert
 *
 * @param <E>
 */
public class ValueWrapper<E> implements OutcomeWrapper<E> {

  /** */
  private final E value;

  /**
   *
   * @param value
   */
  public ValueWrapper(final E value) {
    this.value = value;
  }

  @Override
  public E get() throws ExecutionException {
    return value;
  }
}