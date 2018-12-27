package seek.job.sort;

/**
 * Created by likoguan on 23/12/18.
 */
public class MergeSort extends Template {
    private Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];//原地归并操作不需要

        sort_top_down(a, 0, a.length-1);
    }

    //递归
    private void sort_top_down(Comparable[] a, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low)/2;
        sort_top_down(a, low, mid);
        sort_top_down(a, mid+1, high);
        merge(a, low, mid, high);
    }

    //迭代
    private void sort_bottom_up(Comparable[] a) {
        for (int size=1; size<a.length; size=size+size) {//size是子数组大小，从1开始
            /*两两合并
            //合并[0,size-1] 和 [size, size+size-1]
            //合并[size+size, size+size+size-1] 和 [size+size+size, size+size+size+size-1]
            //合并 .....
            */
            for (int low=0; low<a.length-size;low+=size+size) {
                merge(a, low, low+size-1, Math.min(low+size+size-1, a.length-1));
            }
        }
    }

    //借助临时数组
    private void merge(Comparable[] a, int low, int mid, int high) {
        for (int i=low; i<=high; i++) {
            aux[i] = a[i];
        }

        int p = low;
        int q = mid + 1;
        for (int j=low; j<=high; j++) {
            if (p > mid) {
                a[j++] = aux[q++];
            } else if (q > high) {
                a[j++] = aux[p++];
            } else if (less(aux[p], aux[q])) {
                a[j++] = aux[p++];
            } else {
                a[j++] = aux[q++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        Integer[] arr = mergeSort.createRandomIntegerArray(10000000, 10000);
        long start = System.currentTimeMillis();
        mergeSort.sort(arr);
        System.out.println(System.currentTimeMillis()-start);

        /*测试归并操作
        ShellSort shellSort = new ShellSort();
        Integer[] a = shellSort.createRandomIntegerArray(10, 100);
        Integer[] b = shellSort.createRandomIntegerArray(10, 100);
        shellSort.sort(a);
        shellSort.sort(b);
        Integer[] c = new Integer[20];
        for (int i=0; i<a.length; i++) {
            c[i] = a[i];
        }
        for (int i=0; i<a.length; i++) {
            c[i+10] = b[i];
        }
        shellSort.show(c);

        MergeSort mergeSort = new MergeSort();
        mergeSort.merge_locally(c, 0, 9, 19);
        mergeSort.merge(c, 0, 9, 19);
        mergeSort.show(c);
        */
    }

    //原地归并 交换次数多，性能不好
    private void merge_locally(Comparable[] a, int low, int mid, int high) {
        if (low == mid || mid == high) {
            return;
        }

        int p = low;
        int q = mid + 1;

        while (p <= mid && q <= high) {
            if (less(a[p], a[q])) {
                p++;
            } else {
                for (int j = q; j > p; j--) {
                    swap(a, j, j - 1);
                }
                p++;
                q++;
                mid++;
            }
        }
    }
}
