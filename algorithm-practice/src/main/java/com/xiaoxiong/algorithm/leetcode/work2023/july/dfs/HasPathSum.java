package com.xiaoxiong.algorithm.leetcode.work2023.july.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <a href="https://leetcode.cn/problems/path-sum/"></a>
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2023/7/17  21:59
 */
public class HasPathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }

    public boolean bfs(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode pop = deque.pop();
            if (pop.left == null && pop.right == null && pop.val == targetSum) {
                return true;
            }
            if (pop.left != null) {
                pop.left.val = pop.val + pop.left.val;
                deque.add(pop.left);
            }
            if (pop.right != null) {
                pop.right.val = pop.val + pop.right.val;
                deque.add(pop.right);
            }
        }
        return false;
    }

}

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
