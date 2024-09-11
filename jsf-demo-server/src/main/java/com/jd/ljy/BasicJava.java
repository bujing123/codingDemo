package com.jd.ljy;

import com.jd.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 *
 */
public class BasicJava {
    @Test
    public void sort() {
        int[] a = {3, 5, 6, 9, 4, 1, 5, 0, 4, 7, 2, 8};
        bubbleSort2(a);
        System.out.println(JSON.toJSONString(a));
        bubbleSort(a);
        selectSort(a);
        insertSort(a);
        quickLomutoSort(a);
        quickDouble(a);
    }

    /**
     * 双边循环 不稳定排序
     *
     * @param a
     */
    public void quickDouble(int[] a) {
        quick(a, 0, a.length - 1);
    }

    public void quick(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partitionDouble(a, l, r);
        quick(a, l, p - 1);
        quick(a, p + 1, r);
    }

    /**
     * 快速排序（双边循环）
     * 以分区左边元素作为基准元素，i指针从左至右寻找比基准元素大的元素，j指针从右至左寻找比基准元素小的元素，都找到以后互相交换，直到i、j指针相交，再将i、j指针与基准元素交换位置
     */
    public int partitionDouble(int[] a, int l, int r) {
        int basic = a[l];
        //i 不能从 l+1 开始原因：从l+1开始查找的话，就会存在最后需要排序区间内只有两个数字时，导致i和j指针的数字是同一个数，然后就会跳过while循环，直接把i、j指的数字和基准交换，就会存在问题
        int i = l, j = r;

        //先循环j，再循环i的原因：需要先找更小的元素，再去找更大的元素，不然可能找到更大的元素的时候，i、j重合，然后就把更大的元素和基准元素交换了
        while (i < j) {
            //内层循环还需要加 i<j  原因：比如说当j找到了一个小于基准值之后，i移动到了j左边那个元素，但是还是比基准元素小，就会继续右移，右移到比j更大的地方，这样就错误交换了位置
            while (i < j && a[j] > basic) {
                j--;
            }
            while (i < j && a[i] <= basic) {   //内层循环这里是 <= 原因： 如果不加这个的话，第一次i查询比基准值大的数的时候，就会直接查到基准值上，因为基准值是等于i从最左边找到的值的，这样子就会把基准值和j找到的数交换，基准值就被交换走了
                i++;
            }
            if (i != j) {
                swap(a, i, j);
            }
        }
        swap(a, l, j);
        System.out.println(Arrays.toString(a));
        return j;

    }

    /**
     * 快速排序（单边循环）
     * 选择最右元素作为基准元素
     * i,j指针从左至右，j负责找比基准元素小的值，找到以后与i指针元素交换位置；然后i指针右移一位
     * i指针负责维护小于基准元素的区间边界；
     * 最后i指针和基准元素交换，则完成一次快速排序（第一次分区完成）
     */
    public void quickLomutoSort(int[] a) {
        quick(a, 0, a.length - 1);
    }

    /**
     * @param a
     * @param l
     * @param r
     * @return 下标为基准元素分区位置
     */
    public int partition(int[] a, int l, int r) {
        int basic = a[r];  //最右值作为基准元素
        int i = l;
        for (int j = l; j < r; j++) {
            if (a[j] < basic) {
                if (i != j) {     //从分区第一个元素开始，就都比基准元素小，则会导致相同的一直交换
                    swap(a, i, j);
                }
                i++;
            }
        }
        if (i != r) {        //基准元素恰好是分区最大元素则存在这个情况
            swap(a, i, r);
        }
        System.out.println(Arrays.toString(a));
        return i;
    }


    /**
     * 每一轮，从第一个数开始，相邻两个数进行比较，把较大的数往后移动
     * 优化方式：每轮冒泡记录最后一次交换的索引，当索引为0时，这说明已经排序结束
     * 稳定排序；当比较到相同数字时，不会进行交换，则相同大小的数字的顺序不会被打乱
     *
     * @param a
     */
    public void bubbleSort(int[] a) {
        //最后一次排序的位置
        int n = a.length - 1;
        while (true) {
            int last = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                    last = i;
                }
            }
            n = last;
            if (n == 0) {
                break;
            }
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * 原始冒泡
     *
     * @param nums
     */
    public void bubbleSort2(int[] nums) {
        //优化后冒泡，每次选择最新的末尾
        int last = 0;
        int n = nums.length - 1;
        while (true) {
            for (int i = 0; i < n; i++) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                    last = i;
                }
            }
            n = last;

            if (last == 0) {
                break;
            }
        }

        //原始冒泡
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }


    }

    /**
     * 选择排序；每一轮记录最大数字的下标，然后移动到最后（不稳定排序，可能打乱数组中相同数字的顺序：因为选择到大数字后，可能排到相同数字的后边）
     */
    public void selectSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int s = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[s] > a[j]) {
                    s = j;
                }
            }
            if (s != i) {
                swap(a, i, s);
            }
            System.out.println(Arrays.toString(a));
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * 插入排序：每一轮从未排序区域拿一个数据到排序区域中
     * 从第二个开始，依次将前面的排好顺序
     */
    public void insertSort(int[] a) {

        for (int i = 1; i < a.length; i++) {
            //记录需要插入正确顺序的数字
            int t = a[i];
            //代表已排序完成的元素索引
            int j = i - 1;
            while (j >= 0) {
                //把大于t的都往后移动
                if (t < a[j]) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
                j--;
            }
            a[j + 1] = t;
            System.out.println(Arrays.toString(a));
        }
    }

    /**
     * 将数组中两个下标的数字兑换
     */
    public void swap(int[] a, int i, int j) {
        int n = a[i];
        a[i] = a[j];
        a[j] = n;
    }


    /**
     * 希尔排序
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        int length = array.length;
        while (length > 1) {
            length = length / 2;
            shell(array, length);
        }
        shell(array, 1);

    }

    /**
     * 每次希尔的gap去循环都是一次插入排序
     *
     * @param array
     * @param gap
     */
    private static void shell(int[] array, int gap) {
        for (int i = gap; i < array.length; i++) {
            int tmp = array[i];
            int j = i - gap;
            for (j = j; j >= 0; j = j - gap) {
                if (array[j] > tmp) {
                    array[j + gap] = array[j];
                } else {
                    break;
                }
            }
            array[j + gap] = tmp;
        }
    }


    /**
     * 堆排序
     * @param arr
     */
    public static void heapSort(int arr[]) {
        int temp = 0;

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /**
         * 将堆项元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 重新调整结构，使其满足堆定义，然后继续交换堆项元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
         */

        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);

        }


    }


    /**
     * 将一个数组（二叉树）调整成一个大根堆
     * 功能：完成将以i对应的非叶子结点的树调整成大顶堆
     * 举例int arr[]={4, 6,8,5,9};=>i=1=> adjustHeap=>得到{4,9,8,5, 6}
     * 如果我们再次调用adjustHeap 传入的是i=0=>得到{4,9, 8,5,6}=> {9,6,8,5, 4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中索引
     * @param length 表示对多少个元素继续调整，length 是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int length) {

        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整.
        //说明:k=i*2+1k是i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > arr[i]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


    /**
     * 归并排序
     * @param arr
     * @param low
     * @param high
     * @param tmp
     */
    public static void mergeSort(int[] arr, int low, int high, int[] tmp) {
        if (low < high) {
            // 求中间位置，用于将数组拆分成两部分
            int mid = (low + high) / 2;
            //对左边序列递归划分
            mergeSort(arr, low, mid, tmp);
            //对右边序列进行递归划分
            mergeSort(arr, mid + 1, high, tmp);
            //合并两个有序序列
            merge(arr, low, mid, high, tmp);
        }
    }

    public static void merge(int[] arr, int low, int mid, int high, int[] tmp) {
        // 用于遍历 tmp 数组的指针
        int i = 0;
        //左边序列和右边序列起始索引
        int j = low, k = mid + 1;
        // 比较左右两个有序数组的元素，并按大小依次放入 tmp 数组中
        while (j <= mid && k <= high) {
            //左半区第一个元素小于右半区第一个元素
            if (arr[j] < arr[k]) {
                //接着往后继续比
                tmp[i++] = arr[j++];
            }
            //右半区第一个元素更小，先放右半区第一个元素
            else {
                tmp[i++] = arr[k++];
            }
//            // 输出排序过程中数组 arr 的变化
            System.out.println(Arrays.toString(arr));
        }
        //若左边序列还有剩余，则将其全部拷贝进tmp[]中
        while (j <= mid) {
            tmp[i++] = arr[j++];
        }

        while (k <= high) {
            tmp[i++] = arr[k++];
        }
        // 将排好序的 tmp 数组复制到原数组 arr 中

        for (int t = 0; t < i; t++) {
            arr[low + t] = tmp[t];
        }
    }

}
