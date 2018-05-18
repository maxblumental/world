package lists;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class ProcessPackages {

  private static List<Response> processRequests(List<Request> requests, Buffer buffer) {
    List<Response> responses = new ArrayList<>();
    for (Request request : requests) {
      responses.add(buffer.process(request));
    }
    return responses;
  }

  private static void printResponses(List<Response> responses) {
    for (Response response : responses) {
      if (response.dropped) {
        System.out.println(-1);
      } else {
        System.out.println(response.startTime);
      }
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int bufferMaxSize = scanner.nextInt();

    List<Request> requests = readQueries(scanner);
    List<Response> responses = run(requests, bufferMaxSize);
    printResponses(responses);
  }

  static List<Response> run(List<Request> requests, int bufferMaxSize) {
    Buffer buffer = new Buffer(bufferMaxSize);
    return processRequests(requests, buffer);
  }

  static class Request {
    int arrivalTime;
    int processTime;

    Request(int arrivalTime, int processTime) {
      this.arrivalTime = arrivalTime;
      this.processTime = processTime;
    }

    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Request request = (Request) o;
      return arrivalTime == request.arrivalTime && processTime == request.processTime;
    }

    @Override public int hashCode() {
      return Objects.hash(arrivalTime, processTime);
    }
  }

  static class Response {
    boolean dropped;
    int startTime;

    Response(boolean dropped, int startTime) {
      this.dropped = dropped;
      this.startTime = startTime;
    }

    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Response response = (Response) o;
      return dropped == response.dropped && startTime == response.startTime;
    }

    @Override public int hashCode() {
      return Objects.hash(dropped, startTime);
    }

    @Override public String toString() {
      return "(" + dropped + ", " + startTime + ')';
    }
  }

  static class Buffer {
    private int limit;
    private Deque<Integer> deque;

    Buffer(int limit) {
      this.limit = limit;
      deque = new ArrayDeque<>(limit);
    }

    Response process(Request request) {
      int now = request.arrivalTime;

      while (!deque.isEmpty() && deque.peek() <= now) {
        deque.remove();
      }

      if (deque.isEmpty()) {
        deque.add(now + request.processTime);
        return new Response(false, now);
      }

      if (deque.size() < limit) {
        Integer startTime = deque.peekLast();
        deque.add(startTime + request.processTime);
        return new Response(false, startTime);
      }

      return new Response(true, -1);
    }
  }

  private static List<Request> readQueries(Scanner scanner) {
    int requestsCount = scanner.nextInt();
    List<Request> requests = new ArrayList<>();
    for (int i = 0; i < requestsCount; ++i) {
      int arrivalTime = scanner.nextInt();
      int processTime = scanner.nextInt();
      requests.add(new Request(arrivalTime, processTime));
    }
    return requests;
  }
}
