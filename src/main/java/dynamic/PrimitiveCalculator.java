package dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PrimitiveCalculator {

  static class Cell {
    int prev;
    int opNumber;
    int value;

    Cell(int prev, int opNumber, int value) {
      this.prev = prev;
      this.opNumber = opNumber;
      this.value = value;
    }
  }

  static List<Integer> optimalSequence(int n) {
    List<Cell> sequence = new ArrayList<>(n);
    sequence.add(new Cell(-1, 0, 1));
    for (int i = 1; i < n; i++) {
      int prev = i - 1;
      int opNumber = sequence.get(prev).opNumber + 1;
      int value = i + 1;

      if (value % 2 == 0 && sequence.get(value / 2 - 1).opNumber < opNumber) {
        prev = value / 2 - 1;
        opNumber = sequence.get(value / 2 - 1).opNumber + 1;
      }

      if (value % 3 == 0 && sequence.get(value / 3 - 1).opNumber < opNumber) {
        prev = value / 3 - 1;
        opNumber = sequence.get(value / 3 - 1).opNumber + 1;
      }

      sequence.add(new Cell(prev, opNumber, value));
    }

    ArrayList<Integer> answer = new ArrayList<>();
    Cell current = sequence.get(n - 1);
    while (current.prev >= 0) {
      answer.add(current.value);
      current = sequence.get(current.prev);
    }
    answer.add(1);
    Collections.reverse(answer);
    return answer;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    List<Integer> sequence = optimalSequence(n);
    System.out.println(sequence.size() - 1);
    for (Integer x : sequence) {
      System.out.print(x + " ");
    }
  }
}

