package dataStructures;

public class SortUtils {
    //快速排序
    void QuickSort(int arr[], int start, int end) {
        if (start >= end)
            return;
        int i = start;
        int j = end;
        // 基准数
        int baseval = arr[start];
        while (i < j) {
            // 从右向左找比基准数小的数
            while (i < j && arr[j] >= baseval) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            // 从左向右找比基准数大的数
            while (i < j && arr[i] < baseval) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        // 把基准数放到i的位置
        arr[i] = baseval;
        // 递归
        QuickSort(arr, start, i - 1);
        QuickSort(arr, i + 1, end);
    }

    // 归并排序
    void MergeSort(int arr[], int start, int end, int[] temp) {
        if (start >= end)
            return;
        int mid = (start + end) / 2;
        MergeSort(arr, start, mid, temp);
        MergeSort(arr, mid + 1, end, temp);

        // 合并两个有序序列
        int length = 0; // 表示辅助空间有多少个元素
        int iStart = start;
        int iEnd = mid;
        int jStart = mid + 1;
        int jEnd = end;
        while (iStart <= iEnd && jStart <= jEnd) {
            if (arr[iStart] < arr[jStart]) {
                temp[length] = arr[iStart];
                length++;
                iStart++;
            } else {
                temp[length] = arr[jStart];
                length++;
                jStart++;
            }
        }
        while (iStart <= iEnd) {
            temp[length] = arr[iStart];
            iStart++;
            length++;
        }
        while (jStart <= jEnd) {
            temp[length] = arr[jStart];
            length++;
            jStart++;
        }
        // 把辅助空间的数据放到原空间
        for (int i = 0; i < length; i++) {
            arr[start + i] = temp[i];
        }
    }


    // 冒泡排序
    void BubbleSort(int arr[], int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    // 选择排序
    void SelectionSort(int arr[], int length) {
        int index, temp;

        for (int i = 0; i < length; i++) {
            index = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[index])
                    index = j;
            }
            if (index != i) {
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
    }

    // 插入排序
    void InsertSort(int arr[], int length) {
        for (int i = 1; i < length; i++) {
            int j;
            if (arr[i] < arr[i - 1]) {
                int temp = arr[i];
                for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[j + 1] = temp;
            }
        }
    }

    // 堆排序
    /**
     *
     * @param arr 待调整的数组
     * @param i 待调整的结点的下标
     * @param length 数组的长度
     */
    void HeapAdjust(int arr[], int i, int length) {
        // 调整i位置的结点
        // 先保存当前结点的下标
        int max = i;
        // 当前结点左右孩子结点的下标
        int lchild = i * 2 + 1;
        int rchild = i * 2 + 2;
        if (lchild < length && arr[lchild] > arr[max]) {
            max = lchild;
        }
        if (rchild < length && arr[rchild] > arr[max]) {
            max = rchild;
        }
        // 若i处的值比其左右孩子结点的值小，就将其和最大值进行交换
        if (max != i) {
            int temp;
            temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
            // 递归
            HeapAdjust(arr, max, length);
        }
    }

    // 堆排序
    void HeapSort(int arr[], int length) {
        // 初始化堆
        // length / 2 - 1是二叉树中最后一个非叶子结点的序号
        for (int i = length / 2 - 1; i >= 0; i--) {
            HeapAdjust(arr, i, length);
        }
        // 交换堆顶元素和最后一个元素
        for (int i = length - 1; i >= 0; i--) {
            int temp;
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            HeapAdjust(arr, 0, i);
        }
    }
}
