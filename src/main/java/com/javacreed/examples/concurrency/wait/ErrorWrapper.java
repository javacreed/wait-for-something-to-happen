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
 * Represents an error. This wrapper is used to indicate that wrapped computation failed with the given exception
 *
 * @author Albert Attard
 *
 * @param <E>
 *          the type of value (which is not used)
 */
public class ErrorWrapper<E> implements OutcomeWrapper<E> {

  /** The exception */
  private final ExecutionException e;

  /**
   * Creates an instance of this class
   *
   * @param e
   *          the exception (which can be {@code null})
   */
  public ErrorWrapper(final Exception e) {
    this.e = new ExecutionException(e);
  }

  @Override
  public E get() throws ExecutionException {
    throw e;
  }
}