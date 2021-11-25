package com.xl.designPattern.responsibilityChain;

/**
 * @author liangliang
 * @date 2021/2/21 16:27
 * @e-mail 1640432919@qq.com
 */
public abstract class Approver {

    Approver approver; //下一个处理者
    String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    //处理审批
    public abstract void proccessRequest(PurchaseRequest request);
}
