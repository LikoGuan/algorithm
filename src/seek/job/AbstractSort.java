package seek.job;

import java.util.Random;

public abstract class AbstractSort {

    public abstract void sort(Comparable[] a);

    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    protected boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public Integer[] createRandomIntegerArray(int num, int max) {
        Random random = new Random();
        Integer[] arr = new Integer[num];
        for (int i=0; i<num; i++) {
            arr[i] = random.nextInt(max);
        }
        return arr;
    }
}