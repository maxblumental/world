package dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlacingParenthesesTest {

  @Test public void test1() {
    long value = PlacingParentheses.getMaximValue("1+5");

    assertEquals(6, value);
  }

  @Test public void test2() {
    long value = PlacingParentheses.getMaximValue("5-8+7*4-8+9");

    assertEquals(200, value);
  }
}