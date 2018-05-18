package conquer;

import java.util.Arrays;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import org.junit.Test;

import static org.junit.Assert.*;

public class MajorityElementTest {

  @Test
  public void test() throws Exception {
    Random random = new Random();
    int[] ints = IntStream.generate(random::nextInt).limit(5).toArray();
    //int brute = MajorityElement.brute(ints);
    //int element = MajorityElement.getMajorityElement(ints, 0, ints.length);
    //if (brute != element) {
    //  Arrays.stream(ints).forEach(i -> System.out.println(i + " "));
    //}
  }
}