package com.xiaoxiong.algorithm.leetcode.work2023.july.recursion;

/**
 * <a href="https://leetcode.cn/problems/validate-binary-search-tree/"></a>
 *
 * @author xiongliang
 * @version 1.0
 * @description 判断是否是平衡二叉树
 * @since 2023/7/1  21:52
 */
public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, int left, int right) {
        if (root == null) {
            return true;
        }
        if (root.val <= left || root.val >= right) {
            return false;
        }
        return isValidBST(root.left, left, root.val) && isValidBST(root.right, root.val, right);
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

}



