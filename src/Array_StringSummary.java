import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Array_StringSummary {
    public static void main(String args[]) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        int[] a = {2,4,-7,5,2,-1,2,-4,3};
//        int[] a1 = {-10, -1, -3, -2};
//        int[][] a2D = {
//                {-1, -10, 2},
//                {-2, -3, 2},
//                {1, -2, 3}
//        };
//        int[] a3 = {1,2,4,5,6};
//        System.out.println(MaxSubSum_1(a1));
//        System.out.println(MaxSubSum_2(a));
//        System.out.println(MaxSubSum_3(a1));
//        System.out.println("===============");
//        System.out.println(MaxSubSum2D(a2D));
//        System.out.println("===============");
//        RightShift(a3, 3);
//        for (int i = 0; i < a3.length; i++) {
//            System.out.print(a3[i] + " ");
//        }
//        System.out.println();
//        System.out.println("===============");
//        char[] s1 = {'A', 'A', 'B', 'C', 'D'};
//        char[] s2 = {'C', 'D', 'A', 'A'};
//        System.out.println(s1RightShiftS2(s1, s2));
//        System.out.println("===============");
//        String sa = "travelling";
//        String sb = "traveling";
//        System.out.println(calculateStrDistance_Rec(sa.toCharArray(), 0, sa.length()-1, sb.toCharArray(), 0, sb.length()-1));
//        System.out.println(calculateStrDistance(sa.toCharArray(), sb.toCharArray()));
//        System.out.println();
//        System.out.println("==========================");
//        int[] arr = {2,10,4,9,6,4,1};
//        quickSort(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
//        System.out.println();
//        System.out.println("==========================");
//        int[] sort_a = {1, 3, 5, 6, 8};
//        System.out.println(binarySearch_Rec(sort_a, 8, 0, sort_a.length-1));
//        System.out.println(binarySearch(sort_a, 0));
//        System.out.println("==========================");
        String s3 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println(minWindow(s3, t1));
    }// main
    //========================Java 数组实现快排========================
    private static void quickSort(int[] arr) {
        qsort(arr, 0, arr.length-1);
    }
    private static void qsort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot_index = partition(arr, low, high);
            qsort(arr, low, pivot_index-1);
            qsort(arr, pivot_index+1, high);
        }
    }
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];

        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }

        arr[low] = pivot;
        return low;
    }

    //========================Java 数组实现归并排序========================
    private static void merge_sort(int[] arr, int low, int high) {
        /**
         * 归并排序, 应该是先分解, 然后再归并(调用 merge)
         */
        int mid = (low+high) / 2;
        if (low < high) {
            merge_sort(arr, low, mid);
            merge_sort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }
    private static void merge(int[] a, int low, int mid, int high) {
        /**
         * 将数组 a[low, ..., mid] 和数组 a[mid+1, ..., high] 进行合并
         */
        int[] temp = new int[high-low+1];
        int i=low, j=mid+1;
        int k = 0;
        // 将较小的数字先移到新数组中, 从小到大排序
        while (i<=mid && j<=high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }// while
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 将新数组覆盖 a 中 low-high 的部分
        for (int x = 0; x <= temp.length; x++) {
            a[x+low] = temp[x];
        }
    }

    //========================Java 数组实现冒泡排序========================
    public static void bubbleSort(int[] a) {
        /**
         * 外层有 len-1 轮, 每运行一轮, 数组最右边的一个就不用考虑了(已经是这轮中的最大值了)
         */
        int temp;
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len-i; j++) {
                if (a[j] > a[j+1]) {
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
    }
    //========================Java 数组实现选择排序========================
    public static void selectSort(int[] a) {
        int len = a.length;
        int temp;
        for (int i = 0; i < len-1; i++) {
            int k = i; //记录在这轮循环中最小值的下标
            for (int j = i+1; j < len; j++) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            temp = a[k];
            a[k] = a[i];
            a[i] = temp;
        }
    }
    //========================Java 数组实现二分查找========================
    public static int binarySearch_Rec(int[] a, int target, int s, int e) {
        /**
         * 在有序数组中寻找 target, 若找到,返回对应index; 若没有找到,则返回-1
         */
        if (s > e) {
            return -1;
        }
        int mid = (s - e) / 2 + e;
        if (a[mid] == target) {
            return mid;
        }
        if (target > a[mid]) {
            return binarySearch_Rec(a, target, mid + 1, e);
        } else {
            return binarySearch_Rec(a, target, s, mid - 1);
        }
    }
    public static int binarySearch(int[] a, int target) {
        int len = a.length;
        int low = 0, high = len-1;
        int mid = 0; //单纯初始化
        while (low <= high) {  // <= !!!
            mid = low + (high - low) / 2;
            if (a[mid] == target) {
                return mid;
            }
            if (target > a[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 数组中最大子数组和, 子数组要连续!
     * -1 暴力解决, O(n^2)
     * -2 利用最大和的特性, 但是当数组全部为负数的时候, 需要添加一些新代码 O(n)
     */
    public static int MaxSubSum_1(int[] a) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            int sum = 0;
            for (int j = i; j < a.length; j++) {
                sum += a[j];
                maxSum = Math.max(sum, maxSum);
            }
        }
        return maxSum;
    }
    public static int MaxSubSum_2(int[] a) {
        int currentSum = 0;
        int maxNeg = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                maxNeg = Math.max(maxNeg, a[i]);
            }
            currentSum += a[i];
            if (currentSum < 0) {
                currentSum = 0;
            } else if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return Math.max(maxSum, maxNeg);
    }
    public static int MaxSubSum_3(int[] a) {
        int maxSum = Integer.MIN_VALUE;
        int[] dp = new int[a.length];
        dp[0] = a[0];
        for (int i = 1; i < a.length; i++) {
            dp[i] = (dp[i-1] > 0) ? dp[i-1]+a[i] : a[i];
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
    /**
     * 数组中最大子数组和(二维)
     * i, j 分别是上下界, 确定了之后, 将每一列看成一个数, 就转化为1维的问题了! O(N*N*M)
     */
    public static int MaxSubSum2D(int[][] a) {
        int m = a.length;       //行数
        int n = a[0].length;    //列数
        int maxSum = Integer.MIN_VALUE;
        int maxNeg = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int thisSum = 0;
                for (int k = 0; k < n; k++) {   //从第0列到第n-1列遍历
                    if (a[j][k] < 0) {
                        maxNeg = Math.max(a[j][k], maxNeg);
                    }
                    thisSum += BC(a, i, j, k);
                    if (thisSum < 0) {
                        thisSum = 0;
                    } else if (thisSum > maxSum) {
                        maxSum = thisSum;
                    }
                }
            }
        }
        return Math.max(maxSum, maxNeg);
    }
    private static int BC(int[][] a, int up, int down, int colNum) {
        int rtn = 0;
        for (int i = up; i <= down; i++) {
            rtn += a[i][colNum];
        }
        return rtn;
    }
    /**
     * 数组中最长递增子序列长度, 不用连续
     */
    public static int MaxListLen(int[] a) {
        int maxLen = 0;
        int[] dp = new int[a.length]; //以a[i]为结尾的最长递增子序列的长度
        dp[0] = 1;
        for (int i = 1; i < a.length; i++) {
            int index = -1;
            int maxDp = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    if (dp[j] > maxDp) {
                        maxDp = dp[j];
                        index = j;
                    }
                }
            }
            dp[i] = (index==-1) ? 1 : dp[index]+1;
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
    /**
     * 循环右移 K 位得到新数组
     */
    public static void RightShift(int[] a, int k) {
        int start = a.length - 1 - k % a.length;
        Reverse(a, 0, start);
        Reverse(a, start+1, a.length-1);
        Reverse(a, 0, a.length-1);
    }
    private static void Reverse(int[] a, int b, int e) {
        // 使数组逆序排列
        while (b < e) {
            int tmp = a[b];
            a[b] = a[e];
            a[e] = tmp;
            b++;
            e--;
        }
    }
    /**
     * s1循环右移中的某种情况下能够包含 s2
     */
    public static boolean s1RightShiftS2(char[] s1, char[] s2) {
        int s1Len = s1.length;
        int s2Len = s2.length;
        int k = 0;
        for (int i = 0; i < s1Len*2; i++) {
            if (s1[i % s1Len] == s2[k]) {
                k++;
            }
            if (k == s2Len) {
                break;
            }
        }

        return k==s2Len;
    }
    /**
     * 求2个字符串之间的编辑距离
     * 1. 递归 - 见《编程之美》3.3
     * 2. DP, edit[i][j] 代表了 s1中长度为i的子串 到 s2中长度为j的子串 的编辑距离
     */
    public static int calculateStrDistance_Rec(char[] s1, int s1S, int s1E, char[] s2, int s2S, int s2E) {
        if (s1S > s1E) {
            if (s2S > s2E) return 0;
            else return s2E-s2S+1;
        }
        if (s2S > s2E) {
            if (s1S > s1E) return 0;
            else return s1S-s1E+1;
        }
        if (s1[s1S] == s2[s2S]) {
            return calculateStrDistance_Rec(s1, s1S+1, s1E, s2, s2S+1, s2E);
        } else {
            int t1 = calculateStrDistance_Rec(s1, s1S+1, s1E, s2, s2S, s2E);
            int t2 = calculateStrDistance_Rec(s1, s1S, s1E, s2, s2S+1, s2E);
            int t3 = calculateStrDistance_Rec(s1, s1S+1, s1E, s2, s2S+1, s2E);
            return Math.min(Math.min(t1, t2), t3) + 1;
        }
    }
    public static int calculateStrDistance(char[] s1, char[] s2) {
        int[][] edit = new int[s1.length+1][s2.length+1];
        for (int i = 0; i < s2.length+1; i++) {
            edit[0][i] = i;
        }
        for (int i = 0; i < s1.length+1; i++) {
            edit[i][0] = i;
        }
        for (int i = 1; i < s1.length+1; i++) {
            for (int j = 1; j < s2.length+1; j++) {
                if (s1[i-1] == s2[j-1]) {
                    edit[i][j] = edit[i-1][j-1];
                } else {
                    edit[i][j] = 1 + Math.min(Math.min(edit[i-1][j], edit[i][j-1]), edit[i-1][j-1]);
                }
            }
        }
        return edit[s1.length][s2.length];
    }

    /**
     * 求两个字符串之间的最长公共子串, 不要求连续
     */
    public static int longestSubString(String a, String b) {
        int[][] dp = new int[a.length()+1][b.length()+1];
        for (int i = 0; i < a.length()+1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < b.length()+1; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[a.length()][b.length()];
    }
    /**
     * 求两个字符串之间的最长公共子序列, 要求连续
     */
    public static int longestSubStr(String a, String b) {
        int[][] dp = new int[a.length()+1][b.length()+1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    /**
     * LeetCode11: 能容纳最多水的容器
     * 设计2个指针, 从左和从右向中间逼近
     * 可将这个问题拓展为2维的坐标轴!(x, y) 先按x轴进行排序, 然后再对y设置2个指针
     */
    public static int maxArea(int[] a) {
        int l=0, r=a.length-1;
        int contain = 0;
        while (l < r) {
            contain = Math.max(contain, (r-l)*Math.min(a[l], a[r]));
            if (a[l] < a[r]) {
                l = l + 1;
            } else {
                r = r - 1;
            }
        }
        return contain;
    }

    /**
     * LeetCode42 接雨水, 问最多能接多少个格子的水, 两边的边界不考虑
     */
    public static int trap(int[] h) {
        if (h==null || h.length<3) {
            return 0;
        }
        int[] leftMax = new int[h.length];
        int[] rightMax = new int[h.length];
        int max = h[0];
        for (int i = 1; i < h.length - 1; i++) {
            if (max > h[i]) {
                leftMax[i] = max;
            } else {
                max = h[i];
            }
        }
        max = h[h.length-1];
        for (int i = h.length-2; i >= 0; i--) {
            if (max > h[i]) {
                rightMax[i] = max;
            } else {
                max = h[i];
            }
        }

        int rtn = 0;
        for (int i = 1; i < h.length - 1; i++) {
            if (leftMax[i]!=0 && rightMax[i]!=0) {
                rtn += Math.min(leftMax[i], rightMax[i]) - h[i];
            }
        }
        return rtn;
    }
    /**
     * LeetCode76 最小覆盖子串
     * 1. 注意到题目的关键："所有字母的最小子串"，也就是说两个串都只能是字母。
     2. 于是，可以开辟一个大小为64的数组，来存放数组中字母的频率(Frequency)。准确的说，
     通过字母的ASCII码作为数组的索引，开辟空间的大小为26+6+26=58：26个大写字母，26个小写字母，
     还有中间的6个非字母  A~Z[65~90]  非字母[91~96]  a~z[97~122]
     3. 滑动窗口的使用：分三种情况来移动窗口：（这里令当前窗口的左右边界分别为l，r，窗口的大小为winSize=r-l+1）
     1) 当winSize < t.size()  r++;  也就是窗口右边界向右移动
     2) 当winSize == t.size() :
     2.1) 当窗口中的字符已经符合要求了，直接返回return，已经找到了
     2.2) 否则r++，窗口右边界向右移动
     3) 当winSize > t.size()
     2.1) 当窗口中的字符已经符合要求了，l++，窗口左边界向右移动
     2.2) 否则r++，窗口右边界向右移动

     4. 上面是滑动窗口的使用思路，具体实现上有一定的不同，下面是需要考虑到的要点：
     1) 啥叫作窗口中的字符已经符合要求了？
     1) 窗口滑动时的操作是关键
     2) 要考虑到数组越界的问题
     */
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int l=0, r=-1, fl=-1, fr=s.length()-1;
        int[] sFreq = new int[60];
        int[] tFreq = new int[60];
        for (int i = 0; i < t.length(); i++) {
            tFreq[t.charAt(i)-'A']++;
        }
        while (l <= s.length()-t.length()) {
            if (r-l+1 < t.length()) { //window.size < t.size
                r++;
                if (r == s.length()) { //数组越界
                    break;
                }
                sFreq[s.charAt(r)-'A']++;
                continue;
            }
            if (r-l+1 >= t.length()) { //window.size>=t.size
                boolean succ = true;
                for (int i = 0; i < 60; i++) {
                    if (sFreq[i] < tFreq[i]) {
                        succ = false;
                        break;
                    }
                }
                if (succ) { // 若能匹配
                    if (r-l+1 == t.length()) { //若 window.size==t.size
                        fl=l; fr=r;
                        break;
                    } else {//若 window.size > t.size
                        if (r-l < fr-fl) {
                            fl = l; fr=r;
                        }
                        sFreq[s.charAt(l)-'A']--;
                        l++;
                    }
                } else { //若不能匹配
                    if (r < s.length()-1) {
                        r++;
                        sFreq[s.charAt(r)-'A']++;
                    } else {
                        sFreq[s.charAt(l)-'A']--;
                        l++;
                    }
                }
            }
        }//while
        return fl==-1 ? "" : s.substring(fl, fr+1);
    }

    //========================实现易混淆数========================
//    private final static int[] array_for_confusingNumber = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
//    public static boolean confusingNumber(int N) {
//        if (N < 0) {
//            return false;
//        }
//        String N_str = String.valueOf(N);
//        String N_str_2 = Collections.reverse(N_str);
//
//    }

}