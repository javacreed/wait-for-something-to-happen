package com.javacreed.examples.concurrency.wait;

import org.junit.Assert;
import org.junit.Test;

/**
 * Retrieve the result multiple times
 *
 * @author Albert Attard
 */
public class RetrievedTheValueMultipleTimesTest {

  /**
   * Retrieve the result multiple times
   *
   * @throws Exception
   *           if an error occurs while testing
   */
  @Test
  public void test() throws Exception {
    final WaitForValue<String> wait = new WaitForValue<>();
    wait.succeeded("hello");
    Assert.assertEquals("hello", wait.get());
    Assert.assertEquals("hello", wait.get());
  }
}