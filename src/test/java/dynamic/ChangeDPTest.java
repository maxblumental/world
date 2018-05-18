package dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeDPTest {

  @Test
  public void test1() {
    int change = ChangeDP.getChange(2);

    assertEquals(2, change);
  }

  @Test
  public void test2() {
    int change = ChangeDP.getChange(34);

    assertEquals(9, change);
  }
}