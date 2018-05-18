import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CoveringSegments {

  public static int[] optimalPoints(Segment[] segments) {
    List<Integer> points = new ArrayList<>();
    List<Integer> ends =
        Arrays.stream(segments).map(s -> s.end).sorted().collect(Collectors.toList());
    List<Segment> list = new ArrayList<>(Arrays.asList(segments));
    while (!ends.isEmpty()) {
      int end = ends.get(0);
      points.add(end);
      list.stream()
          .filter(s -> s.start <= end && end <= s.end)
          .forEach(s -> ends.remove(new Integer(s.end)));
      list.removeIf(s -> s.start <= end && end <= s.end);
    }
    return points.stream().mapToInt(i -> i).toArray();
  }

  public static class Segment {
    int start, end;

    Segment(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    Segment[] segments = new Segment[n];
    for (int i = 0; i < n; i++) {
      int start, end;
      start = scanner.nextInt();
      end = scanner.nextInt();
      segments[i] = new Segment(start, end);
    }
    int[] points = optimalPoints(segments);
    System.out.println(points.length);
    for (int point : points) {
      System.out.print(point + " ");
    }
  }
}
 
