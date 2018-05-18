package lists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class CheckBrackets {
  public static void main(String[] args) throws IOException {
    InputStreamReader inputStream = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(inputStream);
    String text = reader.readLine();

    int check = check(text);

    System.out.println(check == -1 ? "Success" : check);
  }

  static class Token {
    private char token;
    private int position;

    Token(char token, int position) {
      this.token = token;
      this.position = position;
    }
  }

  static int check(String text) {
    Deque<Token> stack = new ArrayDeque<>();
    for (int position = 0; position < text.length(); ++position) {
      char next = text.charAt(position);

      if (next == '(' || next == '[' || next == '{') {
        stack.push(new Token(next, position + 1));
      }

      if (next == ')' || next == ']' || next == '}') {
        if (stack.isEmpty()) {
          return position + 1;
        }

        Token token = stack.pop();
        if (!isPair(token.token, next)) {
          return position + 1;
        }
      }
    }
    if (stack.isEmpty()) {
      return -1;
    }

    Token last = stack.pop();
    while (!stack.isEmpty()) {
      last = stack.pop();
    }
    return last.position;
  }

  private static boolean isPair(char a, char b) {
    return a == '(' && b == ')' || a == '{' && b == '}' || a == '[' && b == ']';
  }
}
