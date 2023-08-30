package com.xiaoxiong.algorithm.leetcode.work2023.august.easy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author 小熊不会写代码
 * @date 2023/8/30
 */
public class IsValid {

    private static final Map<Character, Character> map = new HashMap<>();

    {
        map.put('{', '}');
        map.put('(', ')');
        map.put('[', ']');
        map.put('?', '?');
    }

    public boolean isValid(String s) {

        // 这里添加一个 ？ 的主要原因 解决栈 调用迭代器的next 出现异常
        LinkedList<Character> stack = new LinkedList<Character>(){{add('?');}};
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.addLast(c);
            } else if (map.get(stack.removeLast()) != c) {
                return false;
            }
        }
        return stack.size() == 1;
    }

}
