public class GraphSummary {
    /**
     * prime算法, 返回最小生成树的权值之和
     * @param matrix
     * @return
     */
    public static int prime(int[][] matrix) {
        int len = matrix.length;
        boolean[] visited = new boolean[len];
        for (int i = 0; i < len; i++) {
            visited[i] = false;
        }
        int index = 0;
        int sum = 0;

        int[] dist = new int[len];
        visited[0] = true;
        for (int i = 0; i < len; i++) {
            dist[i] = matrix[0][i];
        }

        for (int i = 1; i < len; i++) {  // 循环这么多次
            int min = Integer.MIN_VALUE;
            //从所有没有访问的节点中找一个到 树中节点 距离最小的
            for (int j = 0; j < len; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    index = j;
                }
            }
            visited[index] = true;
            sum += min;

            //更新距离 dist
            for (int j = 0; j < len; j++) {
                if (!visited[j] && dist[j]>matrix[index][j]) {
                    dist[j] = matrix[index][j];
                }
            }
        }
        return sum;
    }

    /**
     *  Djikstra 算法
     *  matrix ——> 邻接表, source ——> 源点, book 数组是标记作用
     *  dis 存放的是 source 点到其他各个点之间的最短路径, 我们就是要对 dis 数组进行操作
     */
    public static int[] Djikstra(int[][] matrix, int source) {
        int n = matrix.length;
        int[] dist = new int[n];
        int[] book = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = matrix[source][i];
        }
        for (int i = 0; i < n; i++) {
            book[i] = 0;
        }
        book[source] = 1;

        for (int time = 1; time < n; time++) {  //循环这么多次
            int min = Integer.MIN_VALUE;
            int index = 0;
            // 找到与 source 顶点最近的顶点——> index
            for (int i = 0; i < n; i++) {
                if (book[i]==0 && min>dist[i]) {
                    min = dist[i];
                    index = i;
                }
            }
            book[index] = 1;
            // 更新 dist 数组
            for (int i = 0; i < n; i++) {
                if (book[i]==0 && (dist[index]+matrix[index][i]<dist[i])) {
                    dist[i] = dist[index]+matrix[index][i];
                }
            }
        }//for
        return dist;
    }
}
