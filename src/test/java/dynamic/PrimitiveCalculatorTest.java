package dynamic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimitiveCalculatorTest {

  @Test
  public void test1() {
    List<Integer> integers = PrimitiveCalculator.optimalSequence(1);

    assertEquals(0, integers.size() - 1);
    assertEquals(Collections.singletonList(1), integers);
  }

  @Test
  public void test2() {
    List<Integer> integers = PrimitiveCalculator.optimalSequence(5);

    assertEquals(3, integers.size() - 1);
    assertEquals(Arrays.asList(1, 2, 4, 5), integers);
  }

  @Test
  public void test3() {
    List<Integer> integers = PrimitiveCalculator.optimalSequence(96234);

    assertEquals(14, integers.size() - 1);
    assertEquals(
        Arrays.asList(1, 3, 9, 10, 11, 22, 66, 198, 594, 1782, 5346, 16038, 16039, 32078, 96234),
        integers);
  }
}