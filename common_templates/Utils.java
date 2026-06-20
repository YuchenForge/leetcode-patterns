import java.util.*;

public class Utils {
    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(List<?> list) {
        System.out.println(list);
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}

