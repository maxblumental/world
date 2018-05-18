package lists;

import java.util.List;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class ProcessPackagesTest {

  @Test public void test1() {
    List<ProcessPackages.Response> responses = ProcessPackages.run(emptyList(), 1);

    assertEquals(emptyList(), responses);
  }

  @Test public void test2() {
    List<ProcessPackages.Request> requests = singletonList(new ProcessPackages.Request(0, 0));
    List<ProcessPackages.Response> responses = ProcessPackages.run(requests, 1);

    List<ProcessPackages.Response> expected = singletonList(new ProcessPackages.Response(false, 0));
    assertEquals(expected, responses);
  }

  @Test public void test3() {
    List<ProcessPackages.Request> requests = asList(
        new ProcessPackages.Request(0, 1),
        new ProcessPackages.Request(0, 1)
    );
    List<ProcessPackages.Response> responses = ProcessPackages.run(requests, 1);

    List<ProcessPackages.Response> expected = asList(
        new ProcessPackages.Response(false, 0),
        new ProcessPackages.Response(true, -1)
    );
    assertEquals(expected, responses);
  }

  @Test public void test4() {
    List<ProcessPackages.Request> requests = asList(
        new ProcessPackages.Request(0, 1),
        new ProcessPackages.Request(1, 1)
    );
    List<ProcessPackages.Response> responses = ProcessPackages.run(requests, 1);

    List<ProcessPackages.Response> expected = asList(
        new ProcessPackages.Response(false, 0),
        new ProcessPackages.Response(false, 1)
    );
    assertEquals(expected, responses);
  }
}