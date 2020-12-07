import java.util.Arrays;
public class ShellDemo {
    public static void main(String[] args) {
        int[] arr = SortTestHelper.getRandomArray(15, 0, 10);
        System.out.println("希尔排序前： " + Arrays.toString(arr));
        shellSort(arr);
        System.out.println("希尔排序后： " + Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        for(int gap = arr.length / 2; gap > 0; gap /= 2) { //步长逐步缩小
            for(int i = gap; i < arr.length; i++) { //在同一步长内
                //同一步长内排序方式是插入排序
                int temp = arr[i], j;
                //j-gap代表有序数组中最大数的下标，j-gap表示有序数组的前一个元素，减去gao就是减去偏移量就是减去步长
                for(j = i; j >= gap && temp < arr[j - gap]; j -= gap)
                    arr[j] = arr[j - gap]; //原有数组最大的后移一位
                arr[j] = temp; //找到了合适的位置插入
            }
        }
    }
}

class SortTestHelper {
    public static int[] getRandomArray(int n, int rangeL, int rangeR) {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random() * (rangeR - rangeL + 1)) + rangeL;
        }
        return arr;
    }
}