import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LinkedTree {
    public static void main(String args[]) {
        System.out.println("Tree");
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.addLeft(2);
        root.addRight(3);
        root.leftchild.addLeft(4);
        root.leftchild.addRight(5);

        System.out.print("获取树的节点数: ");
        System.out.println(getTreeNumRec(root));
        System.out.print("获取第k层的节点数: ");
        System.out.println(getNumForKthLevelRec(root, -1));
        System.out.print("获取树的叶子节点数: ");
        System.out.println(getLeafNumRec(root));


        System.out.print("获取树前序遍历: ");
        preOrderTravelRec(root);

    }// main

    private static class TreeNode<T> {
        T value;
        TreeNode<T> leftchild;
        TreeNode<T> rightchild;

        TreeNode(T value) {
            this.value = value;
        }
        TreeNode(){}

        public void addLeft(T value) {
            /**   增加左子节点
             * addLeft:
             * @param value
             * void  返回类型
             */
            TreeNode<T> leftchild = new TreeNode<T>(value);
            this.leftchild = leftchild;
        }
        public void addRight(T value){
            /**
             * addRight: 增加右子节点
             * @param value
             * void  返回类型
             */
            TreeNode<T> rightchild = new TreeNode<T>(value);
            this.rightchild = rightchild;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TreeNode)) {
                return false;
            }

            return this.value.equals(((TreeNode<?>)obj).value);
        }
        @Override
        public int hashCode() {
            return this.value.hashCode();
        }
        @Override
        public String toString() {
            return this.value == null ? "" : this.value.toString();
        }
    }

    //====================================== 遍历 ======================================
    private static <T> void preOrderTravelRec(TreeNode<T> root) {
        /**
         * preOrderTravel: 前序遍历
         * @param root
         */
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrderTravelRec(root.leftchild);
        preOrderTravelRec(root.rightchild);
    }
    private static <T> void midOrderTravelRec(TreeNode<T> root) {
        /**
         * midOrderTravel: 中序遍历
         * @param root
         */
        if (root == null) {
            return;
        }
        midOrderTravelRec(root.leftchild);
        System.out.println(root.value);
        midOrderTravelRec(root.rightchild);
    }
    private static <T> void backOrderTravelRec(TreeNode<T> root) {
        /**
         * backOrderTravel: 后序遍历
         * @param root
         */
        if (root == null) {
            return;
        }
        backOrderTravelRec(root.leftchild);
        backOrderTravelRec(root.rightchild);
        System.out.println(root.value);
    }
    private static <T> void levelTravel(TreeNode<T> root) {
        /**
         * levelTravel: 分层遍历
         * @param root
         */
        Queue<TreeNode<T>> q = new LinkedList<>();
        q.offer(root); //add()

        while (!q.isEmpty()) {
            TreeNode<T> temp = q.poll(); //remove()
            System.out.println(temp.value);
            if (temp.leftchild != null) {
                q.offer(temp.leftchild);
            }
            if (temp.rightchild != null) {
                q.offer(temp.rightchild);
            }
        }// while
    }
    private static <T> void preOrderTravel(TreeNode<T> root) {
        /**
         * preOrderTravel: 前序遍历, 非递归
         * @param root
         */
        Stack<TreeNode<T>> s = new Stack<>();

        while (root != null || !s.isEmpty()) {
                //一直到最左的顶点
            while (root != null) {
                System.out.print(root.value + " ");
                s.push(root);
                root = root.leftchild;
            }

            if (!s.empty()) {
                root = s.pop().rightchild;
            }

        }// while
    }
    private static <T> void midOrderTravel(TreeNode<T> root) {
        /**
         * midOrderTravel: 中序遍历, 非递归
         * @param root
         */
        Stack<TreeNode<T>> s = new Stack<>();

        while (root != null || !s.empty()) {

            while (root != null) {
                s.push(root);
                root = root.leftchild;
            }
            if (!s.empty()) {
                root = s.pop();
                System.out.print(root.value + " ");
                root = root.rightchild;
            }
        }// while
    }
//    private static <T> void backOrderTravel(TreeNode<T> root) {
//        /**
//         * backOrderTravel: 后序遍历, 非递归
//         * @param root
//         */
//        Stack<TreeNode<T>> s = new Stack<>();
//
//        while (root != null || !s.empty()) {
//
//            while (root != null) {
//
//            }
//        }
//    }


    private static <T> int getTreeDepthRec(TreeNode<T> root) {
        /**
         * getTreeDepth: 判断树的深度
         * @param root 根节点
         * @return int 返回类型
         */
        if (root == null) {
            return 0;
        }
        int leftDepth = getTreeDepthRec(root.leftchild) + 1;
        int rightDepth = getTreeDepthRec(root.rightchild) + 1;

        return Math.max(leftDepth, rightDepth);
    }
    private static <T> int getTreeNumRec(TreeNode<T> root) {
        /**
         * getTreeNum: 判断树中节点个数, 递归
         * @param root 根节点
         * @return int 返回类型
         */
        if (root == null) {
            return 0;
        }
        return getTreeNumRec(root.leftchild) + getTreeNumRec(root.rightchild) + 1;
    }
    private static <T> int getNumForKthLevelRec(TreeNode<T> root, int k) {
        /**
         * getNumForKthLevel: 求第K层节点个数, k 从 1 开始
         *
         * @param root
         * @param k
         * @return int 返回类型
         */
        if (root == null || k < 1) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }

        int leftNum = getNumForKthLevelRec(root.leftchild, k-1);
        int rightNum = getNumForKthLevelRec(root.rightchild, k-1);

        return leftNum+rightNum;
    }
    private static <T> int getLeafNumRec(TreeNode<T> root) {
        /**
         * getLeafNum: 求二叉树中叶子节点的个数
         *
         * @param root
         */
        if (root == null) {
            return 0;
        }
        if (root.leftchild == null && root.rightchild == null) {
            return 1;
        }

        int leftNum = getLeafNumRec(root.leftchild);
        int rightNum = getLeafNumRec(root.rightchild);

        return leftNum+rightNum;
    }

}
