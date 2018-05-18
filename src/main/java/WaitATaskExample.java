import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.stream.IntStream;

public class WaitATaskExample {
  public static void main(String[] args) {
    double sum = IntStream.of(1, 2).mapToDouble(new IntToDoubleFunction() {
      @Override public double applyAsDouble(int value) {
        return (Double) null;
      }
    }).sum();

    System.out.println(sum);
  }
}
