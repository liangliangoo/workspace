package com.xl.designPattern.responsibilityChain;

/**
 * @author liangliang
 * @date 2021/2/21 16:39
 * @e-mail 1640432919@qq.com
 */
public class Client {

    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 800000, 10);
        DepartmentApprover departmentApprover = new DepartmentApprover("谭主任");
        CollegeApprover collegeApprover = new CollegeApprover("王院长");
        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("熊校长");
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(schoolMasterApprover);
        schoolMasterApprover.setApprover(departmentApprover);
        departmentApprover.proccessRequest(purchaseRequest);
    }

}
