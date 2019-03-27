public class AlgorithmSelf {
    public static void main(String args[]) {
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] a = {2,4,-7,5,2,-1,2,-4,3};
        int[] a1 = {-10, -1, -3, -2};
        int[][] a2D = {
                {-1, -10, 2},
                {-2, -3, 2},
                {1, -2, 3}
        };
        int[] a3 = {1,2,4,5,6};
        System.out.println(MaxSubSum_1(a1));
        System.out.println(MaxSubSum_2(a));
        System.out.println(MaxSubSum_3(a1));
        System.out.println("===============");
        System.out.println(MaxSubSum2D(a2D));
        System.out.println("===============");
        RightShift(a3, 3);
        for (int i = 0; i < a3.length; i++) {
            System.out.print(a3[i] + " ");
        }
        System.out.println();
        System.out.println("===============");
        char[] s1 = {'A', 'A', 'B', 'C', 'D'};
        char[] s2 = {'C', 'D', 'A', 'A'};
        System.out.println(s1RightShiftS2(s1, s2));
        System.out.println("===============");
        String sa = "travelling";
        String sb = "traveling";
        System.out.println(calculateStrDistance_Rec(sa.toCharArray(), 0, sa.length()-1, sb.toCharArray(), 0, sb.length()-1));
        System.out.println(calculateStrDistance(sa.toCharArray(), sb.toCharArray()));

    }


    /**
     * 数组中最大子数组和
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
     * 数组中最长递增子序列长度
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

}
