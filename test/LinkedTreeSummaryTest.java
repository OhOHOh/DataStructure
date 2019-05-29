public class LinkedTreeSummaryTest {
    public static void main(String[] args) {
        LinkedTreeSummary test = new LinkedTreeSummary();
        int n = 3;
        System.out.println(test.generateTrees(n).size());
        System.out.println(test.numBSTrees(n));
    }
}
