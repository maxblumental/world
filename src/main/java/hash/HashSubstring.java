package hash;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

  private static FastScanner in;
  private static PrintWriter out;

  public static void main(String[] args) throws IOException {
    in = new FastScanner();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    printOccurrences(getOccurrences(readInput()));
    out.close();
  }

  private static Data readInput() throws IOException {
    String pattern = in.next();
    String text = in.next();
    return new Data(pattern, text);
  }

  private static void printOccurrences(List<Integer> ans) {
    for (Integer cur : ans) {
      out.print(cur);
      out.print(" ");
    }
  }

  private static int polyHash(String s, int p, int x) {
    long hash = 0;
    for (int i = s.length() - 1; i >= 0; --i) {
      hash = (hash * x + s.charAt(i)) % p;
    }
    return (int) hash;
  }

  private static int[] precomputeHashes(String text, String pattern, int p, int x) {
    int T = text.length();
    int P = pattern.length();
    char[] chars = text.toCharArray();
    int[] H = new int[T - P + 1];
    String s = text.substring(T - P, T);
    H[T - P] = polyHash(s, p, x);
    long y = 1;
    for (int i = 0; i < P; i++) {
      y = y * x % p;
    }
    for (int i = T - P - 1; i >= 0; i--) {
      long term1 = ((long) x * H[i + 1]) % p;
      long term2 = (y * chars[i + P]) % p;
      H[i] = (int) ((term1 + chars[i] - term2 + p) % p);
    }
    return H;
  }

  private static List<Integer> getOccurrences(Data input) {
    List<Integer> occurrences = new ArrayList<>();
    int p = 1_000_000_007;
    int x = 263;
    String pattern = input.pattern;
    String text = input.text;
    int[] hashes = precomputeHashes(text, pattern, p, x);
    int pHash = polyHash(pattern, p, x);
    int P = pattern.length();
    int T = text.length();
    for (int i = 0; i < T - P + 1; i++) {
      if (pHash != hashes[i]) {
        continue;
      }
      if (pattern.equals(text.substring(i, i + P))) {
        occurrences.add(i);
      }
    }
    return occurrences;
  }

  static class Data {
    String pattern;
    String text;

    Data(String pattern, String text) {
      this.pattern = pattern;
      this.text = text;
    }
  }

  static class FastScanner {
    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public FastScanner() {
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

