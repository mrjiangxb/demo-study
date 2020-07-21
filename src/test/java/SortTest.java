import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author jiangxiangbo
 * @date 2020/6/23
 * @Description: TODO
 */
public class SortTest {

    int[] arr = {5, 2, 9, 6, 10, 21, 16};
    int len = arr.length;

    @Test
    public void bubbleSort() {
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void selectionSort() {
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            int min = arr[minIndex];
            for (int j = i + 1; j < len - 1; j++) {
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            int temp = arr[i];
            arr[i] = min;
            arr[minIndex] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void ShellSort() {
        for (int gap = len / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < len; i++) {
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && temp < arr[j - gap]) {
                    arr[j] = arr[j  - gap];
                    j = j - gap;
                }
                arr[j] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
