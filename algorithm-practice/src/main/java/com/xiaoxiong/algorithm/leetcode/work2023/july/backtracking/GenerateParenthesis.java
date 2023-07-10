package com.xiaoxiong.algorithm.leetcode.work2023.july.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/generate-parentheses/submissions/"></a>
 * @author xiongliang
 * @version 1.0
 * @description 括号生成
 * @since 2023/7/10  22:54
 */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[n * 2], 0, combinations);
        return combinations;
    }

    private void generateAll(char[] current, int pos, List<String> combinations) {
        if (pos == current.length) {
            if (valid(current)) {
                combinations.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, combinations);
            current[pos] = ')';
            generateAll(current, pos + 1, combinations);
        }
    }

    private boolean valid(char[] current) {
        int balance = 0;
        for (char c : current) {
            if (c == '(') {
                ++balance;
            }
            if (c == ')') {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return true;
    }

    public List<String> backtrackMethod(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    public void backtrack(List<String> result, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            result.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(result, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(result, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }


}
