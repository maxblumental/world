package heap;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
  private int[] data;

  private FastScanner in;
  private PrintWriter out;

  public static void main(String[] args) throws IOException {
    new BuildHeap().solve();
  }

  private void readData() throws IOException {
    int n = in.nextInt();
    data = new int[n];
    for (int i = 0; i < n; ++i) {
      data[i] = in.nextInt();
    }
  }

  private void writeResponse(List<Swap> swaps) {
    out.println(swaps.size());
    for (Swap swap : swaps) {
      out.println(swap.index1 + " " + swap.index2);
    }
  }

  static List<Swap> generateSwaps(int[] data) {
    List<Swap> swaps = new ArrayList<>();
    int middle = data.length / 2;
    for (int i = middle; i >= 0; i--) {
      siftDown(i, swaps, data);
    }
    return swaps;
  }

  private static void siftDown(int i, List<Swap> swaps, int[] data) {
    int min = i;
    int l = i * 2 + 1;
    int r = i * 2 + 2;
    if (l < data.length && data[i] > data[l]) {
      min = l;
    }
    if (r < data.length && data[min] > data[r]) {
      min = r;
    }
    if (min != i) {
      swaps.add(new Swap(i, min));
      swap(i, min, data);
      siftDown(min, swaps, data);
    }
  }

  static void swap(int i, int j, int[] data) {
    int tmp = data[i];
    data[i] = data[j];
    data[j] = tmp;
  }

  private void solve() throws IOException {
    in = new FastScanner();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    readData();
    List<Swap> swaps = generateSwaps(data);
    writeResponse(swaps);
    out.close();
  }

  static class Swap {
    int index1;
    int index2;

    Swap(int index1, int index2) {
      this.index1 = index1;
      this.index2 = index2;
    }

    @Override public String toString() {
      return "{" + index1 + ", " + index2 + '}';
    }
  }

  static class FastScanner {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner() {
      reader = new BufferedReader(new InputStreamReader(System.in));
      tokenizer = null;
    }

    public String next() throws IOException {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        tokenizer = new StringTokenizer(reader.readLine());
      }
      return tokenizer.nextToken();
    }

    public int nextInt() throws IOException {
      return Integer.parseInt(next());
    }
  }
}
