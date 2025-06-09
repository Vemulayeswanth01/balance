import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class Main {
    public static TreeNode buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode current = q.poll();
            if (arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                q.offer(current.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                q.offer(current.right);
            }
            i++;
        }
        return root;
    }
    public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
    public static TreeNode buildBalancedBST(List<Integer> list, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = buildBalancedBST(list, left, mid - 1);
        node.right = buildBalancedBST(list, mid + 1, right);
        return node;
    }

    public static List<String> serialize(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.add("null");
                continue;
            }
            res.add(String.valueOf(node.val));
            q.offer(node.left);
            q.offer(node.right);
        }        int i = res.size() - 1;
        while (i >= 0 && res.get(i).equals("null")) {
            res.remove(i);
            i--;
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] input1 = {1, null, 2, null, 3, null, 4, null, null};
        TreeNode root1 = buildTree(input1);

        List<Integer> inorderList1 = new ArrayList<>();
        inorder(root1, inorderList1);

        TreeNode balanced1 = buildBalancedBST(inorderList1, 0, inorderList1.size() - 1);
        System.out.println("Input: " + Arrays.toString(input1));
        System.out.println("Output: " + serialize(balanced1));
        Integer[] input2 = {2, 1, 3};
        TreeNode root2 = buildTree(input2);

        List<Integer> inorderList2 = new ArrayList<>();
        inorder(root2, inorderList2);

        TreeNode balanced2 = buildBalancedBST(inorderList2, 0, inorderList2.size() - 1);
        System.out.println("Input: " + Arrays.toString(input2));
        System.out.println("Output: " + serialize(balanced2));
    }
}
