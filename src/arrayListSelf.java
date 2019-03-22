import sun.jvm.hotspot.utilities.ReversePtrs;

public class arrayListSelf {
    public static void main(String args[]) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] a = {2,4,-7,5,2,-1,2,-4,3};
        int[] a1 = {-2, -3, -1};
        int[][] a2D = {
                {-1, -10, 2},
                {-2, -3, 2},
                {1, -2, 3}
        };
        int[] a3 = {1,2,4,5,6};
        System.out.println(MaxSubSum_1(a1));
        System.out.println(MaxSubSum_2(a1));
        System.out.println("===============");
        System.out.println(MaxSubSum2D(a2D));
        System.out.println("===============");
        RightShift(a3, 3);
        for (int i = 0; i < a3.length; i++) {
            System.out.print(a3[i] + " ");
        }

    }


    /**
     * 数组中最大子数组和
     * -1 暴力解决, O(n^2)
     * -2 利用最大和的特性, 但是当数组全部为负数的时候, 需要添加一些新代码
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
        int thisSum = 0;
        int maxNeg = Integer.MIN_VALUE;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                maxNeg = Math.max(maxNeg, a[i]);
            }
            thisSum += a[i];
            if (thisSum < 0) {
                thisSum = 0;
            } else if (thisSum > maxSum) {
                maxSum = thisSum;
            }
        }
        return Math.max(maxSum, maxNeg);
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
     * 数组中最长递增子序列长度
     */
    public static int MaxListLen(int[] a) {
        int maxLen = 0;


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


}
