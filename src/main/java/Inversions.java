import java.util.Scanner;

public class Inversions {

  private static long getNumberOfInversions(int[] a, int left, int right) {
    if (right <= left) {
      return 0;
    }
    int mid = (left + right) >>> 1;
    long leftNumber = getNumberOfInversions(a, left, mid);
    long rightNumber = getNumberOfInversions(a, mid + 1, right);
    long mergingNumber = merge(a, left, right);
    return leftNumber + rightNumber + mergingNumber;
  }

  private static long merge(int[] a, int left, int right) {
    int mid = (left + right) >>> 1;
    long result = 0;
    int i = left;
    int[] merge = new int[right - left + 1];
    int j = mid + 1;
    int idx = 0;
    while (i <= mid && j <= right) {
      if (a[i] <= a[j]) {
        merge[idx++] = a[i++];
      } else {
        result += mid - i + 1;
        merge[idx++] = a[j++];
      }
    }
    while (i <= mid) {
      merge[idx++] = a[i++];
    }
    while (j <= right) {
      merge[idx++] = a[j++];
    }
    System.arraycopy(merge, 0, a, left, merge.length);
    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    System.out.println(getNumberOfInversions(a, 0, a.length - 1));
  }
}

