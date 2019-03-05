import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 关于java中二叉树的操作
 * 1. 二叉树的几种遍历
 * 2. 求二叉搜索树中的第K大的结（findKthNode）
 * 3. 根据前序遍历+中序遍历来构建二叉树
 * 4. 如何构建一颗二叉查找树(二叉搜索树)(二叉排序树)、判断数组是否是一颗二叉查找树的后续遍历
 * 5. 如何实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */

public class LinkedTreeSummary {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    int KthNode_index = 0;

    //========================Java 二叉树的几种遍历========================
    public void preOrder(TreeNode root) {
        /**
         *  前序遍历 - 递归
         */
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }
    public void preOrderNonRecursive(TreeNode root) {
        /**
         *  前序遍历 - 非递归
         *  使用栈!
         */
        Stack<TreeNode> s = new Stack<>();
        while (true) {
            while (root != null) {  // 入栈
                System.out.println(root.val);
                s.push(root);
                root = root.left;
            }
            // 当 root==null 时，说明程序应走到"最左边"了，接下来要出栈了
            if (!s.isEmpty()) {
                root = s.pop();
                root = root.right;
            }
        }
    }
    public void inOrder(TreeNode root) {
        /**
         *  中序遍历 - 递归
         */
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.left);
    }
    public void inOrderNonRecursive(TreeNode root) {
        /**
         *  中序遍历 - 非递归
         *  使用栈!
         */
        Stack<TreeNode> s = new Stack<>();
        while (true) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            root = s.pop();
            System.out.println(root.val);
            root = root.right;
        }
    }
    public void postOrder(TreeNode root) {
        /**
         *  后序遍历 - 递归
         */
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }
    public void postOrderNoRecursive(TreeNode root) {
        /**
         *  后序遍历 - 非递归
         *  使用栈!
         */
        Stack<TreeNode> s = new Stack<>();
        if (root == null) {
            return;
        }
        s.push(root);
        while (!s.isEmpty()) {
            root = s.peek();
            if (root.left == null && root.right == null) {
                System.out.println(root.val);
                s.pop();
                continue;
            }
            if (root.right != null) {
                s.push(root.right);
            }
            if (root.left != null) {
                s.push(root.left);
            }
        }
    }
    public void levelOrder(TreeNode root) {
        /**
         *  层次遍历, 使用队列
         */
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            root = q.poll();
            System.out.println(root.val);
            if (root.left != null) {
                q.offer(root.left);
            }
            if (root.right != null) {
                q.offer(root.right);
            }
        }// while
    }


    public TreeNode findKthNode(TreeNode root, int k) {
        if (root != null) { //中序遍历寻找第k个
            TreeNode node = findKthNode(root.left, k);
            if (node != null)
                return node;
            KthNode_index++;
            if (KthNode_index == k)
                return root;
            node = findKthNode(root.right, k);
            if (node != null)
                return node;
        }
        return null;
    }

    public TreeNode reConstructTree(int[] pre, int[] in) {
        /**
         * 给出一颗二叉树的前序遍历与中序遍历, 重建这棵树
         */
        TreeNode root = reConstructTree_fun(pre, 0, pre.length - 1, in, 0, in.length - 1);
        return root;
    }
    private TreeNode reConstructTree_fun(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        for (int i = inStart; i <= inEnd; i++) {
            if (pre[preStart] == in[i]) {
                root.left = reConstructTree_fun(pre, preStart + 1, i - inStart + preStart, in, inStart, i - 1);
                root.right = reConstructTree_fun(pre, preStart + 1 + i - inStart, preEnd, in, i + 1, inEnd);
            }
        }// for
        return root;
    }

    public TreeNode constructBinSearchTree(int[] arr) {
        /**
         * 根据输入的数组, 构建二叉查找树, 就是给一个数字, 按查找的方式添加到树中
         */
        TreeNode root = new TreeNode(arr[0]);
        TreeNode tmp = new TreeNode(0);
        boolean putLeft = false;
        for (int i = 1; i < arr.length; i++) {
            TreeNode head = root;
            TreeNode a = new TreeNode(arr[i]);
            while (head != null) {
                if (head.left == null || head.right == null) {
                    tmp = head;
                }
                if (arr[i] < head.val) {
                    head = head.left;
                } else {
                    head = head.right;
                }
            }//while
            if (tmp.val < arr[i]) {
                tmp.right = a;
            } else {
                tmp.left = a;
            }
        }//for
        return root;
    }

    public boolean verifyPostOrderBST(int[] a) {
        /**
         * 判断数组是否是一颗二叉查找树的后续遍历
         */
        if (a == null || a.length <= 0) {
            return false;
        }
        if (a.length == 1) {
            return true;
        }

        return verifyPostOrderBST_fun(a, 0, a.length - 1);
    }
    private boolean verifyPostOrderBST_fun(int[] a, int low, int high) {
        if (low >= high) {
            return true;
        }
        int i = low;
        while (a[i] < a[high]) {
            i++;
        }
        int j = i;
        while (j < high) {
            if (a[j] < a[high]) {
                return false;
            }
            j++;
        }
        boolean left = verifyPostOrderBST_fun(a, low, i - 1);
        boolean right = verifyPostOrderBST_fun(a, i, high - 1);

        return left && right;
    }

    private ArrayList<ArrayList<Integer>> Print_Z(TreeNode root) {
        /**
         * 实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印
         * 其他行以此类推。
         * 用双端队列来做, 每次近队列的方向是不变的, 但是出队列的方向每次都变换
         */
//        int i = 0;
//        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//        Deque<TreeNode> deque = new LinkedList<>();
//        deque.addFirst(root);
//        TreeNode last = root;
//        while (!deque.isEmpty()) {// 每次循环一层
//
//        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty() || !s2.isEmpty()) {// 每一层一个大循环
            ArrayList<Integer> tmp = new ArrayList<>();
            if (!s1.isEmpty()) {
                while (!s1.isEmpty()) {
                    TreeNode tmpNode = s1.pop();
                    tmp.add(tmpNode.val);
                    if (tmpNode.left != null) s2.push(tmpNode.left);
                    if (tmpNode.right != null) s2.push(tmpNode.right);

                }//while
                result.add(tmp);
            } else {
                while (!s2.isEmpty()) {
                    TreeNode tmpNode = s2.pop();
                    tmp.add(tmpNode.val);
                    if (tmpNode.right != null) s1.push(tmpNode.right);
                    if (tmpNode.left != null) s1.push(tmpNode.left);
                }// while
                result.add(tmp);
            }
        }
        return result;
    }
    ArrayList<ArrayList<Integer> > Print(TreeNode root) {
        /**
         * 打印二叉树, 每层都是一个数组, 而不是一棵树对应一个数组
         */
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            
        }

        return result;
    }
}
