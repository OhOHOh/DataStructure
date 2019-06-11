import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree {

    public static class BSTNode {
        int val;
        BSTNode left;
        BSTNode right;
        private BSTNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    /**
     *  构建二叉查找树(根据数组来构建)
     * @param a: 输入的数组
     * @return: BST
     */
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
                    p = p.left;
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
                    p = p.right;
                }
                if (is_find) {
                    break;
                }
            }// while
            if (is_left) {
                p.left = tmp;
            } else {
                p.right = tmp;
            }
        }
        return root;
    }
    public BSTNode constructBST_byInsert(int[] a) {
        BSTNode root = new BSTNode(a[0]);
        for (int i = 1; i < a.length; i++) {
            insertBST(root, a[i]);
//            System.out.println(a[i]);
        }
        return root;
    }
    public void inOrder(BSTNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }
    /**
     * 在二叉查找树中按 key 进行搜索
     * @param key: 查询的值
     * @return: BSTNode, 若树中没有key, 则返回 null
     */
    public BSTNode searchBST(BSTNode root, int key) {
        BSTNode rtn = root;
        while (rtn != null) {
            if (rtn.val == key) {
                break;
            }
            while (rtn != null && rtn.val < key) {
                rtn = rtn.right;
            }
            while (rtn != null && rtn.val > key) {
                rtn = rtn.left;
            }
        }
        return rtn;
    }

    /**
     * 插入一个新的数字
     * @param root: 原来树
     * @param key: 插入值
     * @return
     */
    public BSTNode insertBST(BSTNode root, int key) {
        BSTNode p = root;
        BSTNode tmp = new BSTNode(key);
        boolean is_find = false;
        while (true) {
            if (p.val == key) break;
            while (p.val > key) {
                if (p.left == null) {
                    is_find = true;
                    p.left = tmp;
                    break;
                }
                p = p.left;
            }
            if (is_find) break;
            while (p.val < key) {
                if (p.right == null) {
                    is_find = true;
                    p.right = tmp;
                    break;
                }
                p = p.right;
            }
            if (is_find) break;
        }// while
        return root;
    }
    public void insertBST_2(BSTNode root, int key) {
        BSTNode p = root, p_Parent=root;
        boolean is_left = false;
        while (p != null) {
            if (key == p.val) break;
            if (key > p.val) {
                p_Parent = p;
                p = p.right;
                is_left = false;
            } else {
                p_Parent = p;
                p = p.left;
                is_left = true;
            }
        }//while
        if (is_left) {
            p_Parent.left = new BSTNode(key);
        } else {
            p_Parent.right = new BSTNode(key);
        }
    }

    /**
     * 删除一个指定的数字 key, 若树中没有 key, 那么就不变
     * @param root: 原来树
     * @param key: 要删除的值
     * @return
     */
    public BSTNode deleteBST(BSTNode root, int key) {
//        if (root.left==null && root.right==null) {
//            return null;
//        }
        BSTNode parent = root;
        BSTNode deleteNode = root;
        boolean is_leftChild = false;
        //定位到删除点 current, 并且记录是左子树还是右子树
        while (deleteNode.val != key && deleteNode != null) {
            parent = deleteNode;
            if (deleteNode.val > key) { //暂时左子树
                is_leftChild = true;
                deleteNode = deleteNode.left;
            } else {//暂时右子树
                is_leftChild = false;
                deleteNode = deleteNode.right;
            }
            if (deleteNode == null) return root;  // 没有找到
        }// while

        // 如果删除节点的 左节点为空 , 右节点也为空
        if (deleteNode.left==null && deleteNode.right==null) {
            if (deleteNode == root) {//要删除节点是root的子节点======
                return null;
            }
            if (is_leftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return root;
        }
        // 如果删除节点只有一个子节点 右节点 或者 左节点
        if (deleteNode.right == null) { //右节点为空
            if (deleteNode == root) {//要删除节点是root的子节点======
                root = deleteNode.left;
                return root;
            }
            if (is_leftChild) {
                parent.left = deleteNode.left;
            } else {
                parent.right = deleteNode.left;
            }
            return root;
        }
        if (deleteNode.left == null) { //左节点为空
            if (deleteNode == root) {//要删除节点是root的子节点======
                root = deleteNode.right;
                return root;
            }
            if (is_leftChild) {
                parent.left = deleteNode.right;
            } else {
                parent.right  = deleteNode.right;
            }
            return root;
        }

        // 如果删除节点的 左右子节点都不为空
        BSTNode successor = getDeleteSuccessor(deleteNode);
        System.out.println(successor.val);
        if (deleteNode == root) {
            root.val = successor.val;
            return root;
        }
        if (is_leftChild) {
            parent.left = successor;
        } else {
            parent.right = successor;
        }
        successor.left = deleteNode.left;
        successor.right = deleteNode.right;
        return root;
    }// deleteBST

    /**
     * 获取删除节点的后继者
     *      删除节点的后继者是在其右节点树中最小的节点, 要将之删除
     * @param deleteNode: root 根节点
     * @return
     */
    private BSTNode getDeleteSuccessor(BSTNode deleteNode) {
        BSTNode rtnParent = deleteNode;
        BSTNode rtn = deleteNode.right; //指向 deleteNode 的右子树中最小的节点

        while (rtn.left != null) {
            rtnParent = rtn;
            rtn = rtn.left;
        }

        if (rtnParent == deleteNode) {
            rtnParent.right = rtn.right;
            return rtn;
        }

        if (rtn.right != null) {
            rtnParent.left = rtn.right;
        } else {
            rtnParent.left = null;
        }
        return rtn;
    }


    //=============================================二叉搜索树的几个问题=============================================
    /**
     * 给定一个整数n, 求以 1...n 为节点组成的二叉搜索树有多少种
     * https://leetcode-cn.com/problems/unique-binary-search-trees/comments/
     */
    public static int numBSTrees(int n) {
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;

        for (int i = 2; i < n+1; i++) {
            for (int j = 1; j < i+1; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
    /**
     * 给定一个整数n, 求以 1...n 为节点组成的二叉搜索树有多少种, 求出所有的这些BST
     */
    public static List<BSTNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<BSTNode>();
        }
        return generateTrees(1, n);
    }
    private static List<BSTNode> generateTrees(int start, int end) {
        List<BSTNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<BSTNode> subLeftTree = generateTrees(start, i-1);
            List<BSTNode> subRightTree = generateTrees(i+1, end);
            for (BSTNode left: subLeftTree) {
                for (BSTNode right: subRightTree) {
                    BSTNode node = new BSTNode(i);
                    node.left = left;
                    node.right = right;
                    res.add(node);
                }
            }
        }
        return res;
    }

    /**
     * 验证是否是二叉搜索树
     */
    private boolean is_validBST_result = true;
    public boolean isValidBST(BSTNode root) {
        if (root == null) {
            return true;
        }
        isValidBST_fun(root);
        return is_validBST_result;
    }
    private void isValidBST_fun(BSTNode root) {
        if (root.left==null && root.right==null) {
            return;
        }
        if (root.left != null) {
            if (root.val > root.left.val) {
                isValidBST_fun(root.left);
            } else {
                is_validBST_result = false;
                return;
            }
        }
        if (root.right != null) {
            if (root.val < root.right.val) {
                isValidBST_fun(root.right);
            } else {
                is_validBST_result = false;
                return;
            }
        }
    }
    // 以上的思路是错误的! 主要思路是：中序遍历之后得到的数组是否是升序的即可！
    private List<Integer> tmp_BST = new ArrayList<>();
    private void dps(BSTNode root) {
        if (root != null) {
            dps(root.left);
            tmp_BST.add(root.val);
            dps(root.right);
        }
    }
    public boolean isValidBST_final(BSTNode root) {
        dps(root);
        for (int i = 0; i < tmp_BST.size()-1; i++) {
            if (tmp_BST.get(i) >= tmp_BST.get(i+1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断数组是否是一颗二叉搜索树的后序遍历
     */
    public boolean verifyPostOrderBST(int[] a) {
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


}
