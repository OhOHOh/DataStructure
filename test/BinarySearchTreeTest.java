public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree test = new BinarySearchTree();
        //50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 100
        int[] a = {24, 15, 10, 18, 40, 45, 30, 25, 35};
        //---------------------二叉搜索树的构建、中序遍历-----------------------------------------
        BinarySearchTree.BSTNode root = test.constructBST_byInsert(a);
        test.inOrder(root);
        System.out.println();
        //---------------------二叉搜索树的查找--------------------------------------------------
        System.out.print("二叉搜索树的查找: ");
        BinarySearchTree.BSTNode rtn = test.searchBST(root, 50);
        if (rtn == null) {
            System.out.println("没有找到");
        } else {
            System.out.println(rtn.val);
        }
//        //----------------------二叉搜索树的插入---------------------------------------------
//        System.out.println("下面进行二叉搜索树的插入: ");
//        test.insertBST(root, 30);
//        test.inOrder(root);
//        System.out.println();
        //----------------------二叉搜索树的节点删除---------------------------------------------
        System.out.print("插入节点后: "); test.inOrder(root);
        System.out.println();
        test.deleteBST(root, 15);
        System.out.print("删除节点后: "); test.inOrder(root);

    }
}
