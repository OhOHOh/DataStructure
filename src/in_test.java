import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class in_test {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // 输入不定长的数组, 使用 数组来实现
        List<Integer> list = new ArrayList<Integer>();
        String s = in.nextLine();
        String[] str = s.split(" ");
        int[] a = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            a[i] = Integer.parseInt(str[i]);
        }
        for (int e: a) {
            System.out.print(e + " ");
        }

//        // 输入定长的数组, 先输入数组长度 n
//        int n = in.nextInt();
//        int[] array1 = new int[n];
//        for (int i = 0; i < n; i++) {
//            array1[i] = in.nextInt();
//        }
//        for (int i = 0; i < n; i++) {
//            System.out.print(array1[i] + " ");
//        }
    }
}
