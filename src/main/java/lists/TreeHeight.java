package lists;

import java.util.*;
import java.io.*;

public class TreeHeight {

  static class Node {
    List<Node> children = new ArrayList<>();
    int depth;
  }

  private static int[] read() throws IOException {
    FastScanner in = new FastScanner();
    int n = in.nextInt();
    int[] parents = new int[n];
    for (int i = 0; i < n; i++) {
      parents[i] = in.nextInt();
    }
    return parents;
  }

  private static Node buildTree(int[] parents) {
    int n = parents.length;
    Node[] nodes = new Node[n];
    for (int i = 0; i < n; i++) {
      nodes[i] = new Node();
    }
    Node root = null;
    for (int i = 0; i < n; i++) {
      int parent = parents[i];
      if (parent == -1) {
        root = nodes[i];
      } else {
        nodes[parent].children.add(nodes[i]);
      }
    }
    if (root == null) {
      throw new IllegalStateException("Root can't be null!");
    }
    return root;
  }

  private static int computeHeight(Node root) {
    int maxHeight = 1;
    ArrayDeque<Node> stack = new ArrayDeque<>();
    stack.push(root);
    root.depth = 1;
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      if (node.children.isEmpty()) {
        if (maxHeight < node.depth) {
          maxHeight = node.depth;
        }
        continue;
      }
      for (Node child : node.children) {
        child.depth = node.depth + 1;
        stack.push(child);
      }
    }
    return maxHeight;
  }

  static int run(int[] parents) {
    Node root = buildTree(parents);
    return computeHeight(root);
  }

  public static void main(String[] args) throws IOException {
    int[] parents = read();
    int height = run(parents);
    System.out.println(height);
  }

  static class FastScanner {
    StringTokenizer tok = new StringTokenizer("");
    BufferedReader in;

    FastScanner() {
      in = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() throws IOException {
      while (!tok.hasMoreElements()) {
        tok = new StringTokenizer(in.readLine());
      }
      return tok.nextToken();
    }

    int nextInt() throws IOException {
      return Integer.parseInt(next());
    }
  }
}
