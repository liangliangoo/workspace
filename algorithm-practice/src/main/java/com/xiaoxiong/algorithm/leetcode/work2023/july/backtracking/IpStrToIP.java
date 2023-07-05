package com.xiaoxiong.algorithm.leetcode.work2023.july.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://www.nowcoder.com/questionTerminal/ce73540d47374dbe85b3125f57727e1e"></a>
 * @author xiongliang
 * @version 1.0
 * @description
 * @since 2023/7/3  22:07
 */
public class IpStrToIP {

    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<>();
        ArrayList<String> ip = new ArrayList<>();  //存放中间结果
        dfs(s, res, ip, 0);
        return res;
    }

    private void dfs(String s, ArrayList<String> res, ArrayList<String> ip, int start){
        if(ip.size() == 4 && start == s.length()){  //找到一个合法解
            res.add(ip.get(0) + '.' + ip.get(1) + '.' + ip.get(2) + '.' + ip.get(3));
            return;
        }
        if(s.length() - start > 3 * (4 - ip.size()))  // 剪枝 后面一个字段的最大长度就是 3 * （4-ip-size)
            return;
        if(s.length() - start < (4 - ip.size()))  //剪枝 这里判断的是最短长度
            return;
        int num = 0;
        for(int i = start; i < start + 3 && i < s.length(); i++){
            num = num * 10 + (s.charAt(i) - '0');
            if(num < 0 || num > 255)  //剪枝
                continue;
            ip.add(s.substring(start, i + 1));
            dfs(s, res, ip, i + 1);
            ip.remove(ip.size() - 1);
            if(num == 0)  //不允许前缀0
                break;
        }
    }


    public List<String> ipList(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        List<String> tmp = new ArrayList<>();
        List<String> result = new ArrayList<>();

        return result;
    }

    public void dfsMethod(String s,int startIdx, List<String> tmp, List<String> result) {
        if (tmp.size() == 4 && startIdx == s.length()) {
            result.add(tmp.get(0) + "." + tmp.get(1) + tmp.get(2) + "." + tmp.get(3));
            return;
        }

        if (s.length() - startIdx > 3 * (4 - tmp.size())) {
            return;
        }
        if (s.length() - startIdx < (4 - tmp.size())) {
            return;
        }
        int num = 0;
        for (int i = startIdx; i < s.length() && i < startIdx + 3; i++) {
            num = 10 * num + (s.charAt(i) - '0');
            if (num < 0 || num > 255) {
                continue;
            }
            tmp.add(s.substring(startIdx, i + 1));
            dfsMethod(s, i + 1, tmp, result);
            tmp.remove(tmp.size() - 1);
            if(num == 0)  //不允许前缀0
                break;
        }
    }

















}
