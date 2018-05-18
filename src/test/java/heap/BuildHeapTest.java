package heap;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuildHeapTest {

  @Test
  public void test1() {
    int[] array = new int[] { 5, 4, 3, 2, 1 };
    int[] copy = array.clone();

    List<BuildHeap.Swap> swaps = BuildHeap.generateSwaps(array);

    for (BuildHeap.Swap swap : swaps) {
      BuildHeap.swap(swap.index1, swap.index2, copy);
    }

    assertHeap(copy, swaps);
  }

  @Test
  public void test2() {
    int[] array = new int[] { 1, 2, 3, 4, 5 };
    int[] copy = array.clone();

    List<BuildHeap.Swap> swaps = BuildHeap.generateSwaps(array);

    for (BuildHeap.Swap swap : swaps) {
      BuildHeap.swap(swap.index1, swap.index2, copy);
    }

    assertEquals(0, swaps.size());

    assertHeap(copy, swaps);
  }

  private void assertHeap(int[] array, List<BuildHeap.Swap> swaps) {
    for (int i = 0; i < array.length / 2 + 1; i++) {
      int l = 2 * i;
      if (l < array.length) {
        assertTrue(String.format("swaps: %s, heap: %s", swaps, Arrays.toString(array)),
            array[i] <= array[l]);
      }
      int r = 2 * i;
      if (r < array.length) {
        assertTrue(array[i] <= array[r]);
      }
    }
  }
}