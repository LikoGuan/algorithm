package seek.job.sort;

import seek.job.AbstractSort;

/**
 * Created by likoguan on 22/12/18.
 */
public class SelectSort extends AbstractSort {
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
        SelectSort selectSort = new SelectSort();
        Integer[] arr = selectSort.createRandomIntegerArray(10, 100);
        selectSort.show(arr);
        selectSort.sort(arr);
        selectSort.show(arr);
    }
}
