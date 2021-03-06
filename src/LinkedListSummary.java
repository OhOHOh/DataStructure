import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 *  关于java中链表的操作
 * 1. 求单链表中结点的个数: getListLength
 * 2. 将单链表反转: reverseList（遍历），reverseListRec（递归）
 * 3. 查找单链表中的倒数第K个结点（k > 0）: reGetKthNode
 * 4. 查找单链表的中间结点: getMiddleNode
 * 5. 从尾到头打印单链表: reversePrintListStack，reversePrintListRec（递归）
 * 6. 已知两个单链表pHead1 和pHead2 各自有序，把它们合并成一个链表依然有序: mergeSortedList, mergeSortedListRec
 * 7. 对单链表进行排序,listSort（归并）,insertionSortList（插入）
 * 8. 判断一个单链表中是否有环: hasCycle
 * 9. 判断两个单链表是否相交: isIntersect_CheckLastNode
 * 10. 已知一个单链表中存在环，求进入环中的第一个节点: getFirstNodeInCycle, getFirstNodeInCycleHashMap
 * 11. 给出一单链表头指针head和一节点指针delete，O(1)时间复杂度删除节点delete: deleteNode
 */

public class LinkedListSummary {
    public static class Node {
        int value;
        Node next;
        public Node(int n) {
            this.value = n;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        //输入
//        Scanner in = new Scanner(System.in);
//        Node head = null;
//        // 输入开始, 要求最后要输入一个非int型的字符
//        if (in.hasNextInt()) {
//            head = new Node(in.nextInt());
//        }
//        Node temp = head;
//        while (in.hasNextInt()) {
//            temp.next = new Node(in.nextInt());
//            temp = temp.next;
//        }

//        if (in.hasNext()) {
//            head = new Node(Integer.valueOf(in.next()));
//        }
//        Node temp = head;
//        while (in.hasNext()) {
//            temp.next = new Node(Integer.valueOf(in.next()));
//            temp = temp.next;
//        }


        //int len=getListLength(head);
        //Node reHead=reverseList(head);
        //reHead=reverseListRec(reHead);
        //Node node_k=reGetKthNode(head,3);
        //Node mid=getMiddleNode(head);
        //reversePrintListRec(head);
        //reversePrintListStack(head);
        //Node mergeHead=mergeSortedList(head,null);
        //Node sortHead=listSort(head);

        //Node reHead = reverseList_3(head);
        //display(head);

        // 创建 2 个单链表
//        Node head1 = new Node(1);
//        Node t1 = head1;
//        for (int i = 1; i <= 2; i++) {
//            t1.next = new Node(i*2+1);
//            t1 = t1.next;
//        }
//        Node head2 = new Node(2);
//        Node t2 = head2;
//        for (int i = 1; i <= 2; i++) {
//            t2.next = new Node(i*2+2);
//            t2 = t2.next;
//        }
//        display(head1);
//        display(head2);
//        display(mergeSortedListRec(head1, head2));


//        Node p = getMiddleNode(head);
//        if (p != null) {
//            System.out.println(p.value);
//        } else {
//            System.out.println("head为空，或者k太大");
//        }
        //display(insertionSortList(head));
    } // end main

    /**
     * 打印链表
     */
    private static void display(Node head) {
        if (head == null) {
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * 获取链表长度, 有环咋办?
     */
    public static int getListLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
    /**
     *  将单链表反转, 用 3 个指针, 先进行初始化
     *  单链表逆序
     */
    public static Node reverseList_1(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node p, q, r=head;
        // 初始化
        p = head;
        q = head.next;
        head.next = null;
        while (q != null) { // 最后 q 必然指向 null
            r = q.next;
            q.next = p;
            p = q;
            q = r;
        }
        return p;
    }
    /**
     *  将单链表反转
     *  对于一条链表，从第2个节点到第N个节点，依次逐节点插入到第1个节点(head节点)之后，(N-1)次这样的操作结束之后将第1个节点挪到新表的表尾即可
     *  https://blog.csdn.net/feliciafay/article/details/6841115
     */
    public static Node reverseList_2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node p, q;
        p = head.next;

        while (p.next != null) { // p 的指向一直不变, 但是在单链表中的位置一直往后移动 !!!
            q = p.next;
            p.next = q.next;
            q.next = head.next;
            head.next = q;
        }

        p.next = head;
        head = p.next.next;
        p.next.next = null;

        return head;
    }
    /**
     *  递归来求解单链表逆序
     */
    private static Node reverseList_3(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node reHead = reverseList_3(head.next);
        head.next.next = head;
        head.next = null;
        return reHead;
    }
    /**
     *  获取倒数第 k 个节点
     */
    private static Node reGetKthNode(Node head, int k) {
        if (head == null) {
            return null;
        }
        Node p=head, q=head;
        while (k > 0 && q != null) {
            q = q.next;
            k--;
        }

        if (q == null) {
            return null;
        }

        while (q != null) {
            p = p.next;
            q = q.next;
        }
        return p;
    }
    /**
     *  获取单链表的中间节点, 设置 2 个指针, target 每次跳 1 步, temp 每次跳 2 步, 当 temp 满足条件的时候, target 就指向了中间节点
     */
    private static Node getMiddleNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node target=head, temp=head;

        while (temp != null && temp.next != null) {
            target = target.next;
            temp = temp.next.next;
        }
        return target;
    }
    /**
     *  从尾到头打印单链表, 用递归的方式
     */
    private static void reversePrintListRec(Node head) {
        if (head == null) {
            return;
        } else {
            reversePrintListRec(head.next);
            System.out.println(head.value);
        }
    }
    /**
     *  从尾到头打印单链表, 用 栈 的方式
     */
    private static void reversePrintListStack(Node head) {
        Stack<Integer> s = new Stack<>();
        Node p = head;
        while (p != null) {
            s.push(p.value);
            p = p.next;
        }
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
    /**
     *  合并两个有序的单链表head1和head2
     */
    private static Node mergeSortedList(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        } else if (head2 == null) {
            return head1;
        }
        Node target = null;
        if (head1.value > head2.value) {
            target = head2;
            head2 = head2.next;
        } else {
            target = head1;
            head1 = head1.next;
        }
        target.next = null;
        Node mergeHead = target;
        while (head1 != null && head2 != null) {
            if (head1.value > head2.value) {
                target.next = head2;
                head2 = head2.next;
            } else {
                target.next = head1;
                head1 = head1.next;
            }
            target = target.next;
            target.next = null;
        }
        if (head1 == null) {
            target.next = head2;
        } else {
            target.next = head1;
        }
        return mergeHead;
    }
    /**
     *  合并两个有序的单链表head1和head2, 递归
     */
    private static Node mergeSortedListRec(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        if (head1.value > head2.value) {
            head2.next = mergeSortedListRec(head1, head2.next);
            return head2;
        } else {
            head1.next = mergeSortedListRec(head1.next, head2);
            return head1;
        }
    }
    /**
     * 对单链表进行排序  , 归并排序
     * 转化为 合并两个有序链表, 不建议递归  !!!
     */
    private static Node listSort(Node head) {
        Node nex = null;
        if (head == null || head.next == null) { // 就一个节点或者直接为null
            return head;
        } else if (head.next.next == null) { // 仅有2个节点
            nex = head.next;
            head.next = null;
        } else {
            Node mid = getMiddleNode(head);
            nex = mid.next;
            mid.next = null;
        }

        return mergeSortedList(listSort(head), listSort(nex));
    }
    /**
     * 插入排序
     * unsorted 指向未排序的部分
     * head 指向拍好序的部分, 最后返回 head !!!
     */
    private static Node insertionSortList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
//        Node pnex = head.next;
//        Node pnex_nex = null;
//        head.next = null;
//        while (pnex != null) {
//            pnex_nex = pnex.next;
//            Node temp = head;
//            Node temp_pre = null;
//            while (temp != null) {
//                if (temp.value > pnex.value) {
//                    break;
//                }
//                temp_pre = temp;
//                temp = temp.next;
//            }
//            if (temp_pre == null) {
//                head = pnex;
//                pnex.next = temp;
//            } else {
//                temp_pre.next = pnex;
//                pnex.next = temp;
//            }
//            pnex = pnex_nex;
//        }
        Node unsortedlist = head.next;   // unsorted 指向未排序的部分
        head.next = null;

        while (unsortedlist != null) {
            // while 每运行一次, 就有一个 节点 加入到已排序的界面
            Node unsorted = unsortedlist;
            unsortedlist = unsortedlist.next;
            unsorted.next = null; // 取得本次循环中要操作的 节点
            if (unsorted.value < head.value) { // 把 unsorted 插入到 head 的前面
                unsorted.next = head;
                head = unsorted;
                continue;
            }
            Node hnex = head.next;
            Node hnex_pre = head;
            while (hnex != null && unsorted.value > hnex.value) {
                /*  这个 while 循环 + 下面的 if , 旨在 在 head 所指的单链表中找到 unsorted 应该插入到的位置
                * hnex 为空, 第一种情况是第一次插入的时候, head 指向的只有1个节点（就是head本身）如果 unsorted.value 比 head.value 大的时候
                *   第2种情况是 head 指向的有很多节点，但是 unsorted.value 比这些点的 value 值都要大, unsorted 应该插在最后一个节点的后面
                *
                * */
                hnex_pre = hnex_pre.next;
                hnex = hnex.next;
            }
            unsorted.next = hnex;
            hnex_pre.next = unsorted;
        }// end while

        return head;
    }
    /**
     *  快排的思想
     */
    private static void quickSort(Node begin, Node end) {
        if (begin == null || begin == end) {
            return;
        }
        Node p = begin.next;
        Node small = begin;

        int pivot = begin.value;
        while (p != end) {
            if (p.value < pivot) {
                small = small.next;
                int temp = p.value;
                p.value = small.value;
                small.value = temp;
            }
            p = p.next;
        }
        begin.value = small.value;
        small.value = pivot;

        quickSort(begin, small);
        quickSort(small.next, end);
    }
    private static Node deleteNode(Node head, Node delete) {
        if (delete == null) {
            return null;
        }
        if (delete.next == null) {
            if (head.next == null) {
                head = null;
            } else {
                Node temp = head;
                while (temp.next != delete) {
                    temp = temp.next;
                }
                temp.next = null;
            }
        } else {
            delete.value = delete.next.value;
            delete.next = delete.next.next;
        }

        return head;
    }

    //========================判断链表是否有环========================
    private static boolean hasCycle(Node head) {
        /**
         *  判断一个单链表中是否有环,快慢指针
         *  2 指针, 第一个指针每次走 1 步, 第二个指针每次走 2 步, 观察这 2 个指针有无可能 相等(指向同一个内存地址)
         */
        boolean flag = false;
        Node p1 = head;
        Node p2 = head;
        while (p1 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    /**
     * 判断是否有环, 若有环则返回环的起始点, 若无环则返回null
     */
    private static Node getFirstNodeInCycleHashMap(Node head) {
        /**
         *  已知一个单链表中存在环，求进入环中的第一个节点,利用 hashmap
         */
        Node target = null;
        Node h = head;
        HashMap<Node, Boolean> map = new HashMap<Node, Boolean>();
        while (h != null) {
            if (map.containsKey(h)) {
                target = h;
                break;
            } else {
                map.put(h, true);
            }
            h = h.next;
        }

        return target;
    }
    private static Node getFirstNodeInCycle(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || slow == null) {//判断是否包含环
            return null;
        }

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }//同步走

        return slow;
    }

    //========================判断2个链表是否相交========================
    /**
     * 单纯的判断是否相交，只需要看最后一个指针是否相等
     */
    public static boolean isIntersect_CheckLastNode(Node head1, Node head2) {
        Node head1End = head1;
        Node head2End = head2;
        while (head1End.next != null) {
            head1End = head1End.next;
        }
        while (head2End.next != null) {
            head2End = head2End.next;
        }

        return (head1End==head2End);
    }
    /**
     *  判断两个单链表是否相交, 如果相交返回第一个节点，否则返回null
     */
    private static Node isIntersect_ReturnFirstNode(Node head1, Node head2) {
        Node target = null;
        if (head1 == null || head2 == null) {
            return target;
        }
        int len1 = getListLength(head1);
        int len2 = getListLength(head2);
        if (len1 >= len2) {
            for (int i = 0; i < len1-len2; i++) {
                head1 = head1.next;
            }
        } else {
            for (int i = 0; i < len2-len1; i++) {
                head2 = head2.next;
            }
        }

        while (head1 != null && head2 != null) {
            if (head1 == head2) {
                target = head1;
                break;
            } else {
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        return target;
    }
    /**
     * 如果链表有环, 怎么办? 分情况讨论:(现在不知道链表有没有环、不知道2个链表是不是相交了)
     * 1. 如果两个链表都没有环，那么同原算法;
     * 2. 如果两个链表一个有环，一个没环，那么必然不相交;
     * 3. 如果两个链表都有环，判断一个链表环上的任一点是否在另一个链表上，如果是，则必相交，反之不相交;
     */
    public static boolean isIntersect_hasCycle(Node head1, Node head2) {
        if (hasCycle(head1)&&!hasCycle(head2) || !hasCycle(head1)&&hasCycle(head2)) {
            return false;
        }
        if (!hasCycle(head1) && !hasCycle(head2)) {
            return isIntersect_ReturnFirstNode(head1, head2)==null;
        }
        // 接下来就是2个链表都存在环了!相交部分肯定在环中!
        // 先把head1的环中所有节点都放入Set中
        Node head1Cycle_FirstNode = getFirstNodeInCycle(head1);
        HashSet<Node> hashSet = new HashSet<>();
        while (true) {
            if (hashSet.contains(head1Cycle_FirstNode)) {
                break;
            }
            hashSet.add(head1Cycle_FirstNode);
            head1Cycle_FirstNode = head1Cycle_FirstNode.next;
        }
        // 对head2中的环进行进行遍历
        boolean find = false;
        Node head2Cycle_FirstNode = getFirstNodeInCycle(head2);
        Node end = head2Cycle_FirstNode;
        while (true) {
            if (hashSet.contains(head2Cycle_FirstNode)) {
                find = true;
                break;
            }
            head2Cycle_FirstNode = head2Cycle_FirstNode.next;
            if (head2Cycle_FirstNode == end) {
                break;
            }
        }
        return find;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     */
    public static Node FindKthToTail(Node head, int k) {
        if (k <= 0) {
            return null;
        }
        Node p = head;
        Node q = head;
        boolean k_is_out_of_bound = false;
        for (int i = 0; i < k; i++) {
            if (q == null) {
                k_is_out_of_bound = true;
                break;
            }
            q = q.next;
        }
        if (k_is_out_of_bound) {
            return null;
        }
        while (q != null) {
            p = p.next;
            q = q.next;
        }
        return p;
    }
}
