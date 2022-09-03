package com.xiaoxiong.common.response;

import java.util.Map;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/18 10:12
 */
public class ResponsePage<T> extends Response<T> {

    private long total;
    private long current;
    private long sum;
    private Map<String,Long> intervalCounts;

    public ResponsePage(Integer code, String msg) {
        super(code, msg);
    }

    public ResponsePage(Integer code, String msg, T data) {
        super(code, msg, data);
    }

    public ResponsePage(Integer code, String msg, T data,
                        long current,long total) {
        super(code, msg, data);
        this.current=current;
        this.total=total;
    }

    public ResponsePage(Integer code, String msg, T data,
                        long current,long total,long sum) {
        super(code, msg, data);
        this.current = current;
        this.total = total;
        this.sum = sum;
    }

    public ResponsePage(Integer code, String msg, T data,
                        long current,long total,long sum,Map<String,Long> intervalCounts) {
        super(code, msg, data);
        this.current = current;
        this.total = total;
        this.sum = sum;
        this.intervalCounts = intervalCounts;
    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public Map<String, Long> getIntervalCounts() {
        return intervalCounts;
    }

    public void setIntervalCounts(Map<String, Long> intervalCounts) {
        this.intervalCounts = intervalCounts;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    /**
     * 状态码 + 成功提示信息 + 数据
     */
    public static <T> ResponsePage<T> success(T data, long current, long total) {
        return new ResponsePage<>(SUCCESS, "server success", data,current,total);
    }

    /**
     * 状态码 + 成功提示信息 + 数据 + 满查询总量
     */
    public static <T> ResponsePage<T> success(T data, long current, long total, long sum) {
        return new ResponsePage<>(SUCCESS, "server success", data,current,total,sum);
    }

    /**
     * 状态码 + 成功提示信息 + 数据 + 满查询总量  +  间隔时间内满查询总条数
     */
    public static <T> ResponsePage<T> success(T data, long current, long total, long sum,Map<String,Long> intervalCounts) {
        return new ResponsePage<>(SUCCESS, "server success", data, current, total, sum, intervalCounts);
    }

    /**
     * 状态码 + 错误信息
     */
    public static <T> ResponsePage<T> fail(String msg) {
        return new ResponsePage<>(FAIL, msg);
    }
}
