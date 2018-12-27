package seek.job.sort;

/**
 * Created by likoguan on 22/12/18.
 */
public class InsertSort extends Template {
    @Override
    public void sort(Comparable[] a) {
        if (a == null || a.length == 0) {
            return;
        }

        //注释掉的方式更好，除了使用了一个哨兵
//        for (int i=1; i<a.length; i++) {
//            Comparable card = a[i];
//            int j = 0;
//            for (j=i-1; j>=0; j--) {
//                if (less(card, a[j])) {
//                    a[j+1] = a[j];
//                } else {
//                    break;
//                }
//            }
//            a[j+1] = card;
//        }
        for (int i=1; i<a.length; i++) {
            for (int j=i; j>0 && less(a[j], a[j-1]); j--) {
                swap(a, j, j-1);
            }
        }
    }

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort();
        Integer[] arr = insertSort.createRandomIntegerArray(10000, 10000);
        //insertSort.show(arr);
        long start = System.currentTimeMillis();
        insertSort.sort(arr);
        System.out.println(System.currentTimeMillis()-start);
        //insertSort.show(arr);
    }
}
