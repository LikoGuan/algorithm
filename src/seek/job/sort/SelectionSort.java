package seek.job.sort;

import seek.job.AbstractSort;

import java.util.Random;

/**
 * Created by likoguan on 22/12/18.
 */
public class SelectionSort extends AbstractSort {
    @Override
    public void sort(Comparable[] a) {
        if (a == null || a.length == 0) {
            return;
        }

        int min = 0;
        for (int i=0; i<a.length; i++) {
            min = i;
            for (int j=i+1; j<a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = createRandomIntegerArray(10, 100);
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.show(arr);
        selectionSort.sort(arr);
        selectionSort.show(arr);
    }

    public static Integer[] createRandomIntegerArray(int num, int max) {
        Random random = new Random();
        Integer[] arr = new Integer[num];
        for (int i=0; i<num; i++) {
            arr[i] = random.nextInt(max);
        }
        return arr;
    }
}
