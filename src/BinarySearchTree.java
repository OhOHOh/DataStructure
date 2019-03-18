/**
 * 1. 构建二叉查找树(根据数组来构建)
 */

public class BinarySearchTree {
    public static class BSTNode {
        int val;
        BSTNode left;
        BSTNode right;

        public BSTNode(int val) {
            this.val = val;
        }
    }

    public BSTNode constructBST(int[] a) {
        BSTNode root = new BSTNode(a[0]);
        BSTNode p = root;
        boolean is_left = false;

        for (int i = 1; i < a.length; i++) {
            BSTNode tmp = new BSTNode(a[i]);
            boolean is_find = false;
            p = root;
            while (true) {
                while (p.val > a[i]) {
                    is_left = true;
                    if (p.left == null) {
                        is_find = true;
                        break;
                    }
                }
                if (is_find) {
                    break;
                }
                while (p.val < a[i]) {
                    is_left = false;
                    if (p.right == null) {
                        is_find = true;
                        break;
                    }
                }
                if (is_find) {
                    break;
                }
            }
            if (is_left) {
                p.left = tmp;
            } else {
                p.right = tmp;
            }
        }
        return root;
    }



}
