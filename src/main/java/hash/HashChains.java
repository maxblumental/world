package hash;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

  private FastScanner in;
  private PrintWriter out;
  // store all strings in one list
  private Bucket[] elems;
  // for hash function
  private int bucketCount;
  private int prime = 1000000007;
  private int multiplier = 263;

  public static void main(String[] args) throws IOException {
    new HashChains().processQueries();
  }

  private int hashFunc(String s) {
    long hash = 0;
    for (int i = s.length() - 1; i >= 0; --i) {
      hash = (hash * multiplier + s.charAt(i)) % prime;
    }
    return (int) hash % bucketCount;
  }

  private Query readQuery() throws IOException {
    String type = in.next();
    if (!type.equals("check")) {
      String s = in.next();
      return new Query(type, s);
    } else {
      int ind = in.nextInt();
      return new Query(type, ind);
    }
  }

  private void writeSearchResult(boolean wasFound) {
    out.println(wasFound ? "yes" : "no");
    // Uncomment the following if you want to play with the program interactively.
    // out.flush();
  }

  private void processQuery(Query query) {
    int index = query.s == null ? query.ind : hashFunc(query.s);
    Bucket bucket = elems[index];
    if (bucket == null) {
      bucket = new Bucket();
      elems[index] = bucket;
    }
    switch (query.type) {
      case "add":
        if (bucket.list == null) {
          bucket.list = new ArrayList<>();
        }
        if (!bucket.list.contains(query.s)) {
          bucket.list.add(query.s);
        }
        break;
      case "del":
        if (bucket.list != null) {
          bucket.list.remove(query.s);
        }
        break;
      case "find":
        writeSearchResult(bucket.find(query.s));
        break;
      case "check":
        List<String> list = bucket.list;
        if (list != null) {
          for (int i = list.size() - 1; i >= 0; i--) {
            out.print(list.get(i) + " ");
          }
        }
        out.println();
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
        break;
      default:
        throw new IllegalStateException("Unknown query: " + query.type);
    }
  }

  private void processQueries() throws IOException {
    in = new FastScanner();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    bucketCount = in.nextInt();
    elems = new Bucket[bucketCount];
    int queryCount = in.nextInt();
    for (int i = 0; i < queryCount; ++i) {
      processQuery(readQuery());
    }
    out.close();
  }

  static class Query {
    String type;
    String s;
    int ind;

    Query(String type, String s) {
      this.type = type;
      this.s = s;
    }

    Query(String type, int ind) {
      this.type = type;
      this.ind = ind;
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

  static class Bucket {
    List<String> list;

    boolean find(String key) {
      return list != null && list.contains(key);
    }
  }
}
