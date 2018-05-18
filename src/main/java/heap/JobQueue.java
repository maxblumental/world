package heap;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
  private int numWorkers;
  private int[] jobs;

  private FastScanner in;
  private PrintWriter out;

  public static void main(String[] args) throws IOException {
    new JobQueue().solve();
  }

  private void readData() throws IOException {
    numWorkers = in.nextInt();
    int m = in.nextInt();
    jobs = new int[m];
    for (int i = 0; i < m; ++i) {
      jobs[i] = in.nextInt();
    }
  }

  private void writeResponse(long[][] result) {
    for (int i = 0; i < jobs.length; ++i) {
      out.println(result[i][0] + " " + result[i][1]);
    }
  }

  static long[][] assignJobs(int numWorkers, int[] jobs) {
    int m = jobs.length;
    long[][] result = new long[m][2];

    PriorityQueue<long[]> queue = new PriorityQueue<>(numWorkers,
        Comparator.comparingLong((long[] o) -> o[1])
            .thenComparingLong(o -> o[0])
    );

    for (int i = 0; i < numWorkers; i++) {
      queue.add(new long[] { i, 0 });
    }

    for (int i = 0; i < m; i++) {
      long[] bestWorker = queue.poll();
      result[i][0] = bestWorker[0];
      result[i][1] = bestWorker[1];
      bestWorker[1] += jobs[i];
      queue.add(bestWorker);
    }
    return result;
  }

  private void solve() throws IOException {
    in = new FastScanner();
    out = new PrintWriter(new BufferedOutputStream(System.out));
    readData();
    long[][] result = assignJobs(numWorkers, jobs);
    writeResponse(result);
    out.close();
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
