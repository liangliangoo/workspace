package com.xiaoxiong.algorithm.leetcode.work2023.july;


import cn.hutool.core.lang.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/maximum-width-of-binary-tree/">二叉树的最大宽度</a>
 *
 * @Author 六月
 * @Date 2023/7/25 13:26
 * @Version 1.0
 */
public class WidthOfBinaryTree {

    Map<Integer, Integer> levelMin = new HashMap<>();

    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 1, 1);
    }


    private int dfs(TreeNode root, int depth, int index) {
        if (root == null) {
            return 0;
        }
        levelMin.putIfAbsent(depth, index);
        return Math.max(index - levelMin.get(depth) + 1,
                Math.max(dfs(root.left, depth + 1, index * 2),
                        dfs(root.right, depth + 1, index * 2 + 1)));
    }

    private int bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 1;
        List<Pair<TreeNode, Integer>> arr = new ArrayList<>();
        arr.add(new Pair<>(root, 1));
        while (!arr.isEmpty()) {
            List<Pair<TreeNode, Integer>> tmp = new ArrayList<>();
            for (Pair<TreeNode, Integer> pair : arr) {
                TreeNode node = pair.getKey();
                int index = pair.getValue();
                if (node.left != null) {
                    tmp.add(new Pair<>(node.left, index * 2));
                }
                if (node.right != null) {
                    tmp.add(new Pair<>(node.right, index * 2 + 1));
                }
            }
            res = Math.max(res, arr.get(arr.size() - 1).getValue() - arr.get(0).getValue() + 1);
            arr = tmp;
        }
        return res;
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
