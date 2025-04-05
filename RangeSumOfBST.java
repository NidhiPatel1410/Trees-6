/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

// Time Complexity : O(n) // going through all nodes
// Space Complexity : O(n) recursive stack and exclusive stack
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// In this approach, we are simply doing dfs traversal (Inorder or preorder or
// postorder - any will work), and checking if the current
// node value is greater than or equal to low and less than or equal to high,
// then adding that value to the sum. For optimization purpose, if we find that
// a node is having value less than low, then we dont call
// dfs from its left, we only call dfs on left child if the node value is
// greater than the low. Because in BST, left<root<right. So same we follow for
// the right child.
class Solution {
    int sum;

    public int rangeSumBST(TreeNode root, int low, int high) {
        // Base case
        if (root == null) {
            return 0;
        }
        // Initialize sum
        sum = 0;
        // Call dfs and pass root, left and right
        dfs(root, low, high);
        return sum;
    }

    private void dfs(TreeNode root, int low, int high) {
        // Base
        if (root == null) {
            return;
        }
        // Process here
        // if (root.val >= low && root.val <= high) {
        // sum = sum + root.val;
        // }
        // Check if root value is greater than low, only then call dfs on it's left
        // child
        if (root.val > low) {
            dfs(root.left, low, high);
        }
        // Or process here
        // Check if current node val is in range than add to sum
        if (root.val >= low && root.val <= high) {
            sum = sum + root.val;
        }

        // Check if root value is less than high, only then call dfs on it's right child
        if (root.val < high) {
            dfs(root.right, low, high);
        }
        // Or process here
        // if (root.val >= low && root.val <= high) {
        // sum = sum + root.val;
        // }
    }
}

// Iterative:
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

// We can do it iteratively also, we take an exclusive stack and we keep on
// pushing the root and go left till the root is null. Then we
// pop the last node value in root. Check if the current node is in range, add
// it to sum. Then make right recursive call.
class Solution {
    int sum;

    public int rangeSumBST(TreeNode root, int low, int high) {
        // Base case
        if (root == null) {
            return 0;
        }
        sum = 0;
        // Stack
        Stack<TreeNode> s = new Stack<>();
        // loop
        while (root != null || !s.isEmpty()) {
            // Then till root is not null, go left
            while (root != null) {
                // Push in the stack
                s.push(root);
                // Only make the left recursive call if the current val is greater than low
                if (root.val > low) {
                    root = root.left;
                } else {
                    // Else break
                    break;
                }

            }
            // pop
            root = s.pop();
            // process
            if (root.val >= low && root.val <= high) {
                sum = sum + root.val;
            }
            // right recursive call
            root = root.right;
        }
        return sum;
    }
}

// Another way of writing iterative code
class Solution {
    int sum;

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        sum = 0;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (root != null || !s.isEmpty()) {
            root = s.pop();
            // left
            if (root != null && root.val >= low) {
                s.push(root.left);
            }
            // process
            if (root != null && root.val >= low && root.val <= high) {
                sum = sum + root.val;
            }
            // right
            if (root != null && root.val <= high) {
                s.push(root.right);
            }
        }
        return sum;
    }
}