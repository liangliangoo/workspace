package com.xiaoxiong.algorithm.leetcode.work2023.august.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/merge-two-binary-trees/description/">二叉树merge</a>
 * @author 小熊不会写代码
 * @date 2023/8/14
 */
public class MergeTrees {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return dfs(root1, root2);
    }

    private TreeNode dfs(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return root1;
        }
        if (root1 == null) {
            return root2;
        }
        TreeNode merge = new TreeNode(root1.val + root2.val);
        merge.left = mergeTrees(root1.left, root2.left);
        merge.right = mergeTrees(root1.right, root2.right);
        return merge;
    }

    private TreeNode bfs(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return root1;
        }
        if (root1 == null) {
            return root2;
        }
        TreeNode merged = new TreeNode(root1.val + root2.val);
        Queue<TreeNode> resultQueue = new LinkedList<>();
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        resultQueue.offer(merged);
        queue1.offer(root1);
        queue2.offer(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node = resultQueue.poll(), node1 = queue1.poll(), node2 = queue2.poll();
            TreeNode left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
            if (left1 != null || left2 != null) {
                if (left1 != null && left2 != null) {
                    TreeNode left = new TreeNode(left1.val + left2.val);
                    node.left = left;
                    resultQueue.offer(left);
                    queue1.offer(left1);
                    queue2.offer(left2);
                } else if (left1 != null) {
                    node.left = left1;
                } else if (left2 != null) {
                    node.left = left2;
                }
            }
            if (right1 != null || right2 != null) {
                if (right1 != null && right2 != null) {
                    TreeNode right = new TreeNode(right1.val + right2.val);
                    node.right = right;
                    resultQueue.offer(right);
                    queue1.offer(right1);
                    queue2.offer(right2);
                } else if (right1 != null) {
                    node.right = right1;
                } else {
                    node.right = right2;
                }
            }
        }
        return merged;
    }

}


// Definition for a binary tree node.
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

