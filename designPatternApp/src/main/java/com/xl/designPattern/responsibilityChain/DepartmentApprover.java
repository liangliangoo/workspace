package com.xl.designPattern.responsibilityChain;

/**
 * @author liangliang
 * @date 2021/2/21 16:30
 * @e-mail 1640432919@qq.com
 */
public class DepartmentApprover extends Approver {

    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    public void proccessRequest(PurchaseRequest request) {
        if (request.getPrice() <= 5000) {
            System.out.println("请求编号id：" + request.getPrice() + "被" + this.name + "处理了");
        } else {
            this.approver.proccessRequest(request);
        }
    }
}