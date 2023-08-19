package com.xiaoxiong.algorithm.leetcode.work2023.august.medium;

import com.xiaoxiong.algorithm.leetcode.work2023.july.ReverseWords;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 小熊不会写代码
 * @date 2023/8/19
 */
public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        //
        return null;
    }

    public ListNode fastAndSlowPoint(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }

    public ListNode hashMap(ListNode head) {
        ListNode pos = head;
        Set<ListNode> hash = new HashSet<>();
        while (pos != null) {
            if (hash.contains(pos)) {
                return pos;
            } else {
                hash.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }
}



class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

