package seek.job.sort;

/**
 * Created by likoguan on 23/12/18.
 */
public class QuickSort extends Template {
    @Override
    public void sort(Comparable[] a) {
        //quickSort(a, 0, a.length-1);
        quickSortEx(a, 0, a.length-1);
    }

    private void quickSort(Comparable[] a, int low, int high) {
        Comparable key = a[low];
        int i=low, j=high;
        while (i != j) {
            while (less(key, a[j]) && j!=i) {
                j--;
            }
            if (j == i) {
                break;
            } else {
                swap(a, i, j);
            }

            while (less(a[i], key) && i!=j) {
                i++;
            }
            if (i == j) {
                break;
            } else {
                swap(a, i, j);
            }
        }

        a[i] = key;
        show(a);
        if (i > low) {
            quickSort(a, low, i - 1);
        }
        if (high > i) {
            quickSort(a, i + 1, high);
        }
    }

    private void quickSortEx(Comparable[] a, int low, int high) {
        Comparable key = a[low];
        int left = low;
        int right = high + 1;

        while (true) {
            while (less(key, a[--right])) {
                if (right == low)
                    break;
            }

            while (less(a[++left], key)) {
                if (left == high)
                    break;
            }

            if (left < right)
                swap(a, left, right);
            else
                break;
        }

        swap(a, low, right);

        if (right > low)
            quickSort(a, low, right-1);
        if (right < high)
            quickSort(a, right+1, high);
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        Integer[] arr = quickSort.createRandomIntegerArray(200, 1000);
        quickSort.show(arr);
        quickSort.sort(arr);
        quickSort.show(arr);
    }
}
