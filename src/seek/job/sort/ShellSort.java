package seek.job.sort;

import seek.job.AbstractSort;

/**
 * Created by likoguan on 23/12/18.
 */
public class ShellSort extends AbstractSort {
    @Override
    public void sort(Comparable[] a) {
        if (a == null || a.length == 0) {
            return;
        }

        int h = 1;
        while (h < a.length/3) {
            h = 3*h + 1;
        }

        while (h >= 1) {
            for (int i=h; i<a.length; i++) {
                for (int j=i; j>=h&&less(a[j], a[j-h]); j=j-h) {
                    swap(a, j, j-h);
                }
            }
            h = h/3;
        }
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        Integer[] arr = shellSort.createRandomIntegerArray(20, 200);
        shellSort.show(arr);
        shellSort.sort(arr);
        shellSort.show(arr);
    }
}
