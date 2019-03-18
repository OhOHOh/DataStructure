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
            System.out.println(a[i]);
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
        BSTNode current = root;
        boolean is_leftChild = false;
        //定位到删除点 current, 并且记录是左子树还是右子树
        while (current.val != key) {
            parent = current;
            if (current.val > key) { //暂时左子树
                is_leftChild = true;
                current = current.left;
            } else {
                is_leftChild = false;
                current = current.right;
            }
            if (current == null) return root;
        }// while

        // 如果删除节点的 左节点为空 , 右节点也为空
        if (current.left==null && current.right==null) {
            if (current == root) {
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
        if (current.right == null) { //右节点为空
            if (current == root) {//要删除节点是root的子节点
                root = current.left;
                root.left = null;
                return root;
            }
            if (is_leftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
            return root;
        }
        if (current.left == null) { //左节点为空
            if (current == root) {//要删除节点是root的子节点
                root = current.right;
                root.right = null;
                return root;
            }
            if (is_leftChild) {
                parent.left = current.right;
            } else {
                parent.right  = current.right;
            }
            return root;
        }

        // 如果删除节点的 左右子节点都不为空
        BSTNode successor = getDeleteSuccessor(current);
        if (current == root) {
            root.val = successor.val;
            return root;
        }
        if (is_leftChild) {
            parent.left = successor;
        } else {
            parent.right = successor;
        }
        successor.left = current.left;
        return root;
    }// deleteBST

    /**
     * 获取删除节点的后继者
     *      删除节点的后继者是在其右节点树中最小的节点, 要将之删除
     * @param deleteNode: root 根节点
     * @return
     */
    private BSTNode getDeleteSuccessor(BSTNode deleteNode) {
        BSTNode successorParent = deleteNode;
        BSTNode successor = deleteNode.right;

        BSTNode current = successor.left;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }

        if (successor.right != null) {
            successorParent.left = successor.right;
        } else {
            successorParent.left = null;
        }

        return successor;
    }
}