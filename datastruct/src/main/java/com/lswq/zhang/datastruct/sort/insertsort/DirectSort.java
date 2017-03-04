package com.lswq.zhang.datastruct.sort.insertsort;

/**
 * Created by zhangsw on 2017/1/1.
 */
public class DirectSort {

    /**
     * 直接插入排序
     *
     * @param wellSort
     */
    public void insertDirectSort(int[] wellSort) {


        int temp;

        for (int i = 1; i < wellSort.length; i++) {

            int j = i - 1;

            temp = wellSort[i];


            for (; j >= 0 && temp < wellSort[j]; j--) {

                wellSort[j + 1] = wellSort[j];                       //将大于temp的值整体后移一个单位  

            }

            wellSort[j + 1] = temp;


        }
    }


    /**
     * 希尔排序
     *
     * @param wellSort
     */
    public void shellSort(int[] wellSort) {

        double d1 = wellSort.length;

        int temp;

        while (true) {

            d1 = Math.ceil(d1 / 2);

            int d = (int) d1;

            for (int x = 0; x < d; x++) {

                for (int i = x + d; i < wellSort.length; i += d) {

                    int j = i - d;

                    temp = wellSort[i];

                    for (; j >= 0 && temp < wellSort[j]; j -= d) {

                        wellSort[j + d] = wellSort[j];

                    }

                    wellSort[j + d] = temp;

                }

            }

            if (d == 1)

                break;

        }

    }


    /**
     * 简单选择排序
     *
     * @param ints
     */
    public void selectSort(int[] ints) {


        int position;

        for (int i = 0; i < ints.length; i++) {


            int j = i + 1;

            position = i;

            int temp = ints[i];

            for (; j < ints.length; j++) {

                if (ints[j] < temp) {

                    temp = ints[j];

                    position = j;

                }

            }

            ints[position] = ints[i];

            ints[i] = temp;

        }

    }


    /**
     * 快速排序
     *
     * @param arr
     */
    public void quickSort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    private void qsort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);        //将数组分为两部分
            qsort(arr, low, pivot - 1);                   //递归排序左子数组
            qsort(arr, pivot + 1, high);                  //递归排序右子数组
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];     //枢轴记录
        while (low < high) {
            while (low < high && arr[high] >= pivot) --high;
            arr[low] = arr[high];             //交换比枢轴小的记录到左端
            while (low < high && arr[low] <= pivot) ++low;
            arr[high] = arr[low];           //交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        //返回的是枢轴的位置
        return low;
    }


    public synchronized void printArr(int[] ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + "    ");
        }
        System.err.println("一次打印");
    }


    public static void main(String[] args) {
        
        
        Integer.parseInt("1");

        int[] ints = {57, 54, 59, 52, 51};

        DirectSort sort = new DirectSort();

        //  直接插入排序
        //  sort.insertDirectSort(ints);

        //  希尔排序
        //  sort.shellSort(ints);

        // 简单选择排序
        // sort.selectSort(ints);

        sort.quickSort(ints);

        sort.printArr(ints);
    }
}
