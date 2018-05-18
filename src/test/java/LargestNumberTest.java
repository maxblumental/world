import java.util.Random;
import org.junit.Test;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;
import static org.junit.Assert.*;

public class LargestNumberTest {
  Random random = new Random(2);

  @Test public void test() {
    for (int i = 0; i < 100000; i++) {
      String[] a = { next(), next() };
      String s = LargestNumber.largestNumber(a);
      int max = max(parseInt(a[0] + a[1]), parseInt(a[1] + a[0]));
      assertEquals(String.valueOf(max), s);
    }
  }

  private String next() {
    return String.valueOf(1 + random.nextInt(1000));
  }
}