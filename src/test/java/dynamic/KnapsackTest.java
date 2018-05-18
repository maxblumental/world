package dynamic;

import org.junit.Test;

import static org.junit.Assert.*;

public class KnapsackTest {

  @Test
  public void test1() {
    int weight = Knapsack.optimalWeight(10, new int[] { 1, 4, 8 });

    assertEquals(9, weight);
  }
}