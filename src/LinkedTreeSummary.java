import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *  关于java中二叉树的操作
 * 1. 二叉树的几种遍历
 * 2. 求二叉搜索树中的第K大的结点
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
            while(root != null) {  // 入栈
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
            if (root.left==null && root.right==null) {
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


    public TreeNode KthNode(TreeNode root, int k) {
        if(root != null){ //中序遍历寻找第k个
            TreeNode node = KthNode(root.left,k);
            if(node != null)
                return node;
            KthNode_index ++;
            if(KthNode_index == k)
                return root;
            node = KthNode(root.right,k);
            if(node != null)
                return node;
        }
        return null;
    }





}
