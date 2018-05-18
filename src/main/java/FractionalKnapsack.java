import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FractionalKnapsack {
  private static double getOptimalValue(int capacity, int[] values, int[] weights) {
    double totalValue = 0;

    TreeMap<Double, int[]> map = new TreeMap<>();

    for (int i = 0; i < values.length; i++) {
      int value = values[i];
      int weight = weights[i];
      map.put(((double) value) / weight, new int[] { value, weight });
    }

    for (Map.Entry<Double, int[]> entry : map.descendingMap().entrySet()) {
      double valuePerUnit = entry.getKey();
      int weight = entry.getValue()[1];
      if (capacity > weight) {
        capacity -= weight;
        totalValue += weight * valuePerUnit;
      } else {
        totalValue += capacity * valuePerUnit;
        capacity = 0;
        break;
      }
    }

    return totalValue;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int capacity = scanner.nextInt();
    int[] values = new int[n];
    int[] weights = new int[n];
    for (int i = 0; i < n; i++) {
      values[i] = scanner.nextInt();
      weights[i] = scanner.nextInt();
    }
    System.out.println(getOptimalValue(capacity, values, weights));
  }
} 
