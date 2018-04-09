import java.util.Scanner;

public class Heap {

    private static int heap_length; // 堆的长度

    public static void main(String args[]) {
//        int[] h = new int[10]; // 12 3 5 1 6 9 10 20 15 2
//        heap_length = 10;
//        buildHeap_siftup(h);
//        int[] h = {12, 3, 5, 1, 6, 9, 10, 20, 15, 2};
//        heap_length = 10;
//        buildHeap_siftdown(h);
        int[] h = new int[10]; // 12 3 5 1 6 9 10 20 15 2
        heap_length = 10;
        buildHeap_siftup_max(h);

        System.out.println("堆已经建立，如下：");
        for (int i = 0; i < h.length; i++) {
            System.out.print(h[i] + " ");
        }
        System.out.println();

        //sort_heap_1(h);

        sort_heap_2(h);
        System.out.println("最终结果如下：");
        for (int i = 0; i < h.length; i++) {
            System.out.print(h[i] + " ");
        }
    }

    private static void siftdown(int i, int[] h) {
        /**
         *  最小堆 , 向下调整, 用于维护 堆, 保持堆的特性
         *  i 不断增加
         */
        int t;
        boolean flag = true;

        while (i*2+1 < heap_length && flag) {
            if (h[i] > h[i*2+1]) {  // 左节点比较
                t = i * 2 + 1;
            } else {
                t = i;
            }
            if (i*2+2 < heap_length && h[t] > h[i*2+2]) { // 右节点比较
                t = i * 2 + 2;
            }

            if (t == i) {
                flag = false;
            } else {  // 上面的 2 个 if 中至少有一个成立了
                int temp = h[i];
                h[i] = h[t];
                h[t] = temp;

                i = t;
            }
        }// while
    }
    private static void siftup(int i, int[] h) {
        /**
         * 最小堆, 向上调整, 用于新增节点时向上调整
         * i 不断减小
         */
        boolean flag = true;

        while ((i-1)/2 >= 0 && flag) {
            if (h[i] < h[(i-1)/2]) {
                int temp = h[i];
                h[i] = h[(i-1)/2];
                h[(i-1)/2] = temp;
            } else {
                flag = false;
            }
            i = (i-1)/2;
        }
    }
    private static void buildHeap_siftdown(int[] h) {
        /**
         * 使用 siftdown 的方法来构建 堆
         * 把数组 h 看作一个 完全二叉树!!! 时间复杂度 O(n)
         * @input:
         *      heap_length: 堆的长度
         *      h: 数组
         */
        int mid = (heap_length-2) / 2;
        for (int i = mid; i >= 0; i--) {
            siftdown(i, h);
        }
    }
    private static void buildHeap_siftup(int[] h) {
        /**
         * 使用 siftup 的方法来构建 堆, 时间复杂度是 O(n*logn)
         * 在输入数据的时候就进行排序
         */
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < heap_length; i++) {
            h[i] = in.nextInt();
            siftup(i, h);
        }
    }

    // ============================================ 最大堆的实现 ============================================

    private static void siftdown_max(int i, int[] h) {
        /**
         *  最小堆 , 向下调整, 用于维护 堆, 保持堆的特性
         *  i 不断增加
         */
        int t;
        boolean flag = true;

        while (i*2+1 < heap_length && flag) {
            if (h[i] < h[i*2+1]) {  // 左节点比较
                t = i * 2 + 1;
            } else {
                t = i;
            }
            if (i*2+2 < heap_length && h[t] < h[i*2+2]) { // 右节点比较
                t = i * 2 + 2;
            }

            if (t == i) {
                flag = false;
            } else {  // 上面的 2 个 if 中至少有一个成立了
                int temp = h[i];
                h[i] = h[t];
                h[t] = temp;

                i = t;
            }
        }// while
    }
    private static void siftup_max(int i, int[] h) {
        /**
         * 最小堆, 向上调整, 用于新增节点时向上调整
         * i 不断减小
         */
        boolean flag = true;

        while ((i-1)/2 >= 0 && flag) {
            if (h[i] > h[(i-1)/2]) {
                int temp = h[i];
                h[i] = h[(i-1)/2];
                h[(i-1)/2] = temp;
            } else {
                flag = false;
            }
            i = (i-1)/2;
        }
    }
    private static void buildHeap_siftdown_max(int[] h) {
        /**
         * 使用 siftdown 的方法来构建 堆
         * 把数组 h 看作一个 完全二叉树!!! 时间复杂度 O(n)
         * @input:
         *      heap_length: 堆的长度
         *      h: 数组
         */
        int mid = (heap_length-2) / 2;
        for (int i = mid; i >= 0; i--) {
            siftdown_max(i, h);
        }
    }
    private static void buildHeap_siftup_max(int[] h) {
        /**
         * 使用 siftup 的方法来构建 堆, 时间复杂度是 O(n*logn)
         * 在输入数据的时候就进行排序
         */
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < heap_length; i++) {
            h[i] = in.nextInt();
            siftup_max(i, h);
        }
    }

    // ============================================ 堆排序 ============================================
    //private static int n;

    private static int delete_top(int[] h) { // 改变 heap_length
        /**
         * @input:
         *      n: 堆 的大小, 并非是 h 的长度
         *      h: 堆的数组
         */
        int result = h[0];
        h[0] = h[heap_length-1];
        heap_length--;
        siftdown(0, h);

        return result;
    }
    private static void sort_heap_1(int[] h) {
        /**
         * 堆排序, 从小到大, 推荐使用 最大堆!!!
         * 这里使用 最小堆
         * h 已经是最小堆了
         */
        int temp = heap_length;
        System.out.println("最终结果如下：");
        for (int i = 0; i < temp; i++) {
            System.out.print(delete_top(h) + " ");
        }

    }
    private static void sort_heap_2(int[] h) { // 改变 heap_length
        /**
         * 堆排序, 从小到大, 使用 最大堆
         */
        int temp = heap_length;
        while (heap_length > 1) {
            int i = h[heap_length-1];
            h[heap_length-1] = h[0];
            h[0] = i;

            heap_length--;
            siftdown_max(0, h);
        }
    }

}
