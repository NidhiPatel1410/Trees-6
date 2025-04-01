// For serialize, just taking the queue, adding the root, perform BFS and build the string as you keep polling the element from queue.

// For deserialize, first converting the string to string array by splitting by comma. Using a queue, we know that first value in str 
// is the root node. So adding that root node to the queue. Now keeping an index i=1 on string array. While q is not empty, poll the
// curr node from the queue, now at index i we have the left child of the curr, so if it not null, create a node and add it to the 
// left of curr. Do i++ for the right child. Repeat same for right and also add both left and right to queue if they are not null.
// Repeat same till q is empty. Return root at last.

// Time Complexity : O(n)
// Space Complexity : O(n)  
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class SerializeDeSerializeBT {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // Base case
        if (root == null) {
            return "";
        }
        // Queue for the bfs
        Queue<TreeNode> q = new LinkedList<>();
        // Add the root
        q.add(root);
        // Stringbuilder for our ans
        StringBuilder sb = new StringBuilder();
        // Start the bfs
        while (!q.isEmpty()) {
            // Poll the current node
            TreeNode curr = q.poll();
            // If it is not null
            if (curr != null) {
                // Then append it the string and add it's left and right child to the queue
                sb.append(curr.val);
                q.add(curr.left);
                q.add(curr.right);
            } else {
                // Else append null to the string
                sb.append("null");
            }
            // Then append comma
            sb.append(",");
        }
        // return ToString
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // Base case
        if (data == null || data.length() == 0) {
            return null;
        }
        // Convert the string to array, so we get indexes
        String[] strArray = data.split(",");
        // Queue
        Queue<TreeNode> q = new LinkedList<>();
        // Create and Add the root node to the queue
        TreeNode root = new TreeNode(Integer.parseInt(strArray[0]));
        q.add(root);
        // Take i for index on the strArray
        int i = 1;
        // Now while q is not empty
        while (!q.isEmpty()) {
            // Poll the curr
            TreeNode curr = q.poll();
            // Check if the value at i is not null
            if (!strArray[i].equals("null")) {
                // Add that as the left child of curr
                curr.left = new TreeNode(Integer.parseInt(strArray[i]));
                // Add that to queue
                q.add(curr.left);
            }
            // increment i for the right child
            i++;
            // If not null
            if (!strArray[i].equals("null")) {
                // Add that as the right child of the curr
                curr.right = new TreeNode(Integer.parseInt(strArray[i]));
                // Add that to the queue
                q.add(curr.right);
            }
            // increment
            i++;
        }
        // Return root
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));