package lists;

import org.junit.Test;

import static lists.CheckBrackets.check;
import static org.junit.Assert.*;

public class CheckBracketsTest {

  @Test
  public void test1() {
    int check = check("[]");

    assertEquals(-1, check);
  }

  @Test
  public void test2() {
    int check = check("{}[]");

    assertEquals(-1, check);
  }

  @Test
  public void test3() {
    int check = check("[()]");

    assertEquals(-1, check);
  }

  @Test
  public void test4() {
    int check = check("(())");

    assertEquals(-1, check);
  }

  @Test
  public void test5() {
    int check = check("{[]}()");

    assertEquals(-1, check);
  }

  @Test
  public void test6() {
    int check = check("{");

    assertEquals(1, check);
  }

  @Test
  public void test7() {
    int check = check("{[}");

    assertEquals(3, check);
  }

  @Test
  public void test8() {
    int check = check("foo(bar);");

    assertEquals(-1, check);
  }

  @Test
  public void test9() {
    int check = check("foo(bar[i);");

    assertEquals(10, check);
  }

  @Test
  public void test10() {
    int check = check("}");

    assertEquals(1, check);
  }

  @Test
  public void test11() {
    int check = check("[](()");

    assertEquals(3, check);
  }
}