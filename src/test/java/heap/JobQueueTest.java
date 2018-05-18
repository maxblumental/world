package heap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

import static org.junit.Assert.*;

public class JobQueueTest {

  @Test
  public void test1() {
    long[][] result = JobQueue.assignJobs(2, new int[] { 1, 2, 3, 4, 5 });
    List<String> strings = Stream.of(result).map(Arrays::toString).collect(Collectors.toList());
    String message = String.join(", ", strings);
    assertArrayEquals(message,
        new long[][] {
            { 0, 0 },
            { 1, 0 },
            { 0, 1 },
            { 1, 2 },
            { 0, 4 }
        }, result);
  }

  @Test
  public void test2() {
    long[][] result = JobQueue.assignJobs(4,
        new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 });
    List<String> strings = Stream.of(result).map(Arrays::toString).collect(Collectors.toList());
    String message = String.join(", ", strings);
    assertArrayEquals(message,
        new long[][] {
            { 0, 0 },
            { 1, 0 },
            { 2, 0 },
            { 3, 0 },
            { 0, 1 },
            { 1, 1 },
            { 2, 1 },
            { 3, 1 },
            { 0, 2 },
            { 1, 2 },
            { 2, 2 },
            { 3, 2 },
            { 0, 3 },
            { 1, 3 },
            { 2, 3 },
            { 3, 3 },
            { 0, 4 },
            { 1, 4 },
            { 2, 4 },
            { 3, 4 }
        }, result);
  }
}