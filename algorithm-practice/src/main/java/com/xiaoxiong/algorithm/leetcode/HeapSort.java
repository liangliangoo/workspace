package com.xiaoxiong.algorithm.leetcode;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @Author 六月
 * @Date 2023/6/13 20:42
 * @Version 1.0
 */
public class HeapSort {

    public static void sort(int[] arr) {
        int n = arr.length;

        // 构建堆（大顶堆）
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // 依次取出堆顶元素，重新构建堆
        for (int i = n - 1; i > 0; i--) {
            // 将堆顶元素（最大值）与当前未排序部分的最后一个元素交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 重新构建堆
            heapify(arr, i, 0);
        }
    }

    // 堆化
    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // 最大值的下标
        int left = 2 * i + 1; // 左子节点的下标
        int right = 2 * i + 2; // 右子节点的下标

        // 如果左子节点大于最大值，则更新最大值
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // 如果右子节点大于最大值，则更新最大值
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // 如果最大值不是根节点，则交换根节点和最大值节点，并继续堆化
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 0, 3, 9, 1, 7, 4, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr)); // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

}
