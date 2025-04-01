
// In this problem, we are doing BFS traversal and everytime we go left we do -1, and for right we do +1, we start distance as 0 from
// root. So we maintain a hashmap with distance as keys and the nodes having the same distance as values in one list. We also maintain
// the min and the max distance. At last we traverse the hashmap from min to max and the list in result order wise.

// Time Complexity : O(n) - no. of nodes
// Space Complexity : O(n) - queue, hashmap 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
import java.util.*;
import java.util.LinkedList;

// Treenode class for the constructing the tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class VerticalOrderTreeTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // Base case
        if (root == null) {
            return new ArrayList<>();
        }
        // Declare result list
        List<List<Integer>> result = new ArrayList<>();
        // Declare two queues, one for storing the nodes and one storing the distance
        // associated to that nodes
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> d = new LinkedList<>();
        // Hashmap for storing the distance and list of nodes with same distance as
        // values
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        // Add the root node and its distance to both queues respectively
        q.add(root);
        d.add(0);
        // Min and max for keeping track of min and max distance
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // Start BFS
        while (!q.isEmpty()) {
            // Poll the curr node and it's distance from the queue
            TreeNode curr = q.poll();
            int dist = d.poll();
            // Update the min and max as per the distance
            min = Math.min(min, dist);
            max = Math.max(max, dist);
            // Check if map contains this distance
            if (!map.containsKey(dist)) {
                // If no then put it with a new list as value
                map.put(dist, new ArrayList<>());
            }
            // Add the node to that list
            map.get(dist).add(curr.val);
            // Now check for childrens
            if (curr.left != null) {
                // If not null add to queue and for left child add the dist-1 as distance
                q.add(curr.left);
                d.add(dist - 1);
            }
            if (curr.right != null) {
                // For right dist+1 as distance
                q.add(curr.right);
                d.add(dist + 1);
            }

        }
        // At end loop from min to max and create the result list
        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }
        return result;
    }

    public static void main(String[] args) {
        VerticalOrderTreeTraversal v = new VerticalOrderTreeTraversal();
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        TreeNode root = new TreeNode(3, left, right);
        List<List<Integer>> res = v.verticalOrder(root);
        for (List<Integer> li : res) {
            for (int l : li) {
                System.out.print(l + " ");
            }
            System.out.println();
        }

    }
}