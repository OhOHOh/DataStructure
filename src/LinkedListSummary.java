import java.util.HashMap;
import java.util.Scanner;
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
 * 9. 判断两个单链表是否相交: isIntersect
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
        int[] arr = {2,10,4,9,6,4,1};
        quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
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
    public static int getListLength(Node head) {
        /**
         *  求单链表中结点的个数: getListLength
         */
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
    public static Node reverseList_1(Node head) {
        /**
         *  将单链表反转, 用 3 个指针, 先进行初始化
         *  单链表逆序
         */
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
    public static Node reverseList_2(Node head) {
        /**
         *  将单链表反转
         *  对于一条链表，从第2个节点到第N个节点，依次逐节点插入到第1个节点(head节点)之后，(N-1)次这样的操作结束之后将第1个节点挪到新表的表尾即可
         *  https://blog.csdn.net/feliciafay/article/details/6841115
         */
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
    private static Node reverseList_3(Node head) {
        /**
         *  递归来求解
         */
        if (head == null || head.next == null) {
            return head;
        }
        Node reHead = reverseList_3(head.next);
        head.next.next = head;
        head.next = null;
        return reHead;
    }
    private static Node reGetKthNode(Node head, int k) {
        /**
         *  获取倒数第 k 个节点
         */
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
    private static Node getMiddleNode(Node head) {
        /**
         *  获取单链表的中间节点, 设置 2 个指针, target 每次跳 1 步, temp 每次跳 2 步, 当 temp 满足条件的时候, target 就指向了中间节点
         */
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
    private static void reversePrintListRec(Node head) {
        /**
         *  从尾到头打印单链表, 用递归的方式
         */
        if (head == null) {
            return;
        } else {
            reversePrintListRec(head.next);
            System.out.println(head.value);
        }
    }
    private static void reversePrintListStack(Node head) {
        /**
         *  从尾到头打印单链表, 用 栈 的方式
         */
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
    private static Node mergeSortedList(Node head1, Node head2) {
        /**
         *  合并两个有序的单链表head1和head2
         */
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
    private static Node mergeSortedListRec(Node head1, Node head2) {
        /**
         *  合并两个有序的单链表head1和head2, 递归
         */
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
    private static Node listSort(Node head) {
        /**
         * 对单链表进行排序  , 归并排序
         * 转化为 合并两个有序链表, 不建议递归  !!!
         */
        Node nex = null;
        if (head == null || head.next == null) {
            return head;
        } else if (head.next.next == null) {
            nex = head.next;
            head.next = null;
        } else {
            Node mid = getMiddleNode(head);
            nex = mid.next;
            mid.next = null;
        }

        return mergeSortedList(listSort(head), listSort(nex));
    }
    private static Node insertionSortList(Node head) {
        /**
         * 插入排序
         * unsorted 指向未排序的部分
         * head 指向拍好序的部分, 最后返回 head !!!
         */
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
    private static void quickSort(Node begin, Node end) {
        /**
         *  快排的思想
         */
        if (begin == null || end == null || begin == end) {
            return;
        }
        Node p = begin;
        Node q = p.next;

        int nMidValue = p.value;
        while (q != null && q != end.next) {
            if (q.value < nMidValue) {
                p = p.next;
                int temp = p.value;
                p.value = q.value;
                q.value = temp;
            }
            q = q.next;
        }
        quickSort(begin, p);
        quickSort(p.next, end);
    }
    private static Node isIntersect(Node head1, Node head2) {
        /**
         *  判断两个单链表是否相交,如果相交返回第一个节点，否则返回null
         *  如果单纯的判断是否相交，只需要看最后一个指针是否相等
         */
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
            h =h.next;
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


    //========================Java 数组实现快排========================
    private static void quickSort(int[] arr) {
        qsort(arr, 0, arr.length-1);
    }
    private static void qsort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            qsort(arr, low, pivot-1);
            qsort(arr, pivot+1, high);
        }
    }
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];

        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }

        arr[low] = pivot;
        return low;
    }

    //========================Java 数组实现归并排序========================
    private static void merge_sort(int[] arr, int low, int high) {
        /**
         * 归并排序, 应该是先分解, 然后再归并(调用 merge)
         */
        int mid = (low+high) / 2;
        if (low < high) {
            merge_sort(arr, low, mid);
            merge_sort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }
    private static void merge(int[] a, int low, int mid, int high) {
        /**
         * 将数组 a[low, ..., mid] 和数组 a[mid+1, ..., high] 进行合并
         */
        int[] temp = new int[high-low+1];
        int i=low, j=mid+1;
        int k = 0;
        // 将较小的数字先移到新数组中, 从小到大排序
        while (i<=mid && j<=high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }// while
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 将新数组覆盖 a 中 low-high 的部分
        for (int x = 0; x <= temp.length; x++) {
            a[x+low] = temp[x];
        }
    }
}
