package seek.job.search;

/**
 * Created by likoguan on 22/12/18.
 */
public class BinarySearch {
    public static int search(int key, int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }

        int low = 0, mid = 0, high = arr.length - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = null;
        System.out.println(search(3, a));

        int[] b = {};
        System.out.println(search(3, b));

        int[] c = {1};
        System.out.println(search(1, c));
        System.out.println(search(0, c));
        System.out.println(search(3, c));

        int[] d = {1,2};
        System.out.println(search(1, d));
        System.out.println(search(2, d));
        System.out.println(search(0, d));
        System.out.println(search(3, d));

        int[] e = {1,2,3,4,5};
        System.out.println(search(0, e));
        System.out.println(search(1, e));
        System.out.println(search(2, e));
        System.out.println(search(3, e));
        System.out.println(search(4, e));
        System.out.println(search(5, e));
        System.out.println(search(6, e));
    }
}
