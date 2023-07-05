package com.xiaoxiong.algorithm.leetcode.work2023.july.dfs;

import java.util.*;

/**
 * <a href= "https://leetcode.cn/problems/binary-tree-level-order-traversal/"></a>
 * @author xiongliang
 * @version 1.0
 * @description 二叉树的层序遍历
 * @since 2023/7/5  9:24
 */
public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {


        return null;
    }

    public List<List<Integer>> bfs(TreeNode root) {
        if (Objects.isNull(root)) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

}


// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

