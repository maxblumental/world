import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class PointsAndSegments {

  static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
    int[] count = new int[points.length];
    List<Region> regions = buildRegions(starts, ends);
    for (int i = 0; i < points.length; i++) {
      int point = points[i];
      int result = Collections.binarySearch(regions, point);
      result = result >= 0 ? result : -result - 1;

      if (result == 0 && point < regions.get(0).a) {
        count[i] = 0;
      } else if (result == regions.size() - 1 && point == regions.get(regions.size() - 1).a) {
        count[i] = regions.get(regions.size() - 1).aCount - 1;
      } else if (result == 0) {
        count[i] = regions.get(0).aCount;
      } else if (result == regions.size() - 1 && point < regions.get(regions.size() - 1).a) {
        count[i] = regions.get(regions.size() - 2).count;
      } else if (result >= regions.size()) {
        count[i] = 0;
      } else if (point == regions.get(result).a) {
        int aCount = regions.get(result).aCount;
        count[i] = aCount;
      } else {
        count[i] = regions.get(result - 1).count;
      }
    }

    return count;
  }

  static List<Region> buildRegions(int[] starts, int[] ends) {
    Arrays.sort(starts);
    Arrays.sort(ends);
    ArrayList<Region> list = new ArrayList<>();
    int i = 1;
    int j = 0;
    int currStarts = 1;
    int currEnds = 0;
    int prev = starts[0];
    int acc = 1; // equality starts accumulator
    while (i < starts.length && j < ends.length) {
      int t;
      boolean updateStart = false;
      boolean updateEnd = false;
      if (starts[i] > ends[j]) {
        t = ends[j];
        j++;
        updateEnd = true;
      } else if (starts[i] < ends[j]) {
        t = starts[i];
        i++;
        updateStart = true;
      } else {
        t = starts[i];
        i++;
        j++;
        updateStart = true;
        updateEnd = true;
      }
      if (t == prev) {
        currStarts += toInt(updateStart);
        currEnds += toInt(updateEnd);
        acc += toInt(updateStart);
        continue;
      }
      int prevCount = list.isEmpty() ? 0 : list.get(list.size() - 1).count;
      Region region = new Region(currStarts - currEnds, prevCount + acc, prev, t);
      acc = 0;
      list.add(region);
      prev = t;
      currStarts += toInt(updateStart);
      currEnds += toInt(updateEnd);
      acc += toInt(updateStart);
    }
    while (j < ends.length) {
      if (prev == ends[j]) {
        currEnds++;
        j++;
        continue;
      }
      int prevCount = list.isEmpty() ? 0 : list.get(list.size() - 1).count;
      list.add(new Region(currStarts - currEnds, prevCount + acc, prev, ends[j]));
      prev = ends[j];
      acc = 0;
      currEnds++;
      j++;
    }
    return list;
  }

  private static int toInt(boolean updateStart) {
    return updateStart ? 1 : 0;
  }

  static class Region implements Comparable<Integer> {
    private int count;
    private int aCount;
    private int a;
    private int b;

    Region(int count, int aCount, int a, int b) {
      this.count = count;
      this.aCount = aCount;
      this.a = a;
      this.b = b;
    }

    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Region region = (Region) o;

      if (count != region.count) return false;
      if (aCount != region.aCount) return false;
      if (a != region.a) return false;
      return b == region.b;
    }

    @Override public int hashCode() {
      int result = count;
      result = 31 * result + aCount;
      result = 31 * result + a;
      result = 31 * result + b;
      return result;
    }

    @Override public String toString() {
      return "{" +
          "c=" + count +
          ", aC=" + aCount +
          ", (" + a +
          ", " + b +
          ") }";
    }

    @Override public int compareTo(Integer o) {
      return Integer.compare(a, o);
    }
  }

  static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
    int[] cnt = new int[points.length];
    for (int i = 0; i < points.length; i++) {
      for (int j = 0; j < starts.length; j++) {
        if (starts[j] <= points[i] && points[i] <= ends[j]) {
          cnt[i]++;
        }
      }
    }
    return cnt;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n, m;
    n = scanner.nextInt();
    m = scanner.nextInt();
    int[] starts = new int[n + 1];
    int[] ends = new int[n + 1];
    int[] points = new int[m];
    //int[] starts = { 0, 7 };
    //int[] ends = { 5, 10 };
    //int[] points = { 1, 6, 11 };
    for (int i = 0; i < n; i++) {
      starts[i] = scanner.nextInt();
      ends[i] = scanner.nextInt();
    }
    int max = Arrays.stream(ends).max().getAsInt();
    starts[n] = max;
    ends[n] = max + 1;
    for (int i = 0; i < m; i++) {
      points[i] = scanner.nextInt();
    }
    //use fastCountSegments
    int[] cnt = fastCountSegments(starts, ends, points);
    for (int x : cnt) {
      System.out.print(x + " ");
    }
  }
}

