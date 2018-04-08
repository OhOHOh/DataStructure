public class shortestPath {
    public static int[] Djikstra_s(int[][] matrix, int[] book, int source) {
        /**
         *  Djikstra 算法
         *  matrix ——> 邻接表, source ——> 源点, book 数组是标记作用
         *  dis 存放的是 source 点到其他各个点之间的最短路径, 我们就是要对 dis 数组进行操作
         */
        int n = matrix[0].length;   // 顶点数
        int[] dis = new int[n];
        for (int i = 0; i < n; i++) {
            dis[i] = matrix[source][i];
        }

        for (int i = 0; i < n; i++) {
            book[i] = 0;
        }
        book[source] = 1;

        for (int time = 0; time < n-1; time++) { // 每次循环设置一个 book 为 1
            int min = Integer.MAX_VALUE;
            int min_i = 0;
            for (int i = 0; i < n; i++) { // 找到与 source 顶点最近的顶点——> min_i
                if (book[i] == 0 && dis[i] < min) {
                    min = dis[i];
                    min_i = i;
                }
            }
            book[min_i] = 1;
            // 接下来更新 dis 数组
            for (int i = 0; i < n; i++) {
                if (book[i] == 0 && (dis[min_i] + matrix[min_i][i] < dis[i])) {
                    // 更加严谨的话, 要对 matrix[min_i][i] 是否存在做一个验证
                    dis[i] = dis[min_i] + matrix[min_i][i];
                }
            }
        }

        return dis;
    }// end Djikstra
    public static void Floyd_Warshall(int[][] matrix) {
        int n = matrix[0].length;
        for (int k = 0; k < n; k++) {
            // 现在只允许经过前 k 个顶点, 0,1,2,3,...,k
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
    }// end Floyd_Warshall

    public static void main(String args[]) {
        System.out.println("Hello World!");
    }
}
