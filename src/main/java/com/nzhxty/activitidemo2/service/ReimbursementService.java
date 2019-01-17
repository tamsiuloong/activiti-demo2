package com.nzhxty.activitidemo2.service;

import com.nzhxty.activitidemo2.entity.Reimbursement;
import com.nzhxty.activitidemo2.entity.User;

/**
 * 报销service
 */
public interface ReimbursementService {

    void baoxiao(Reimbursement reimbursement, User user);

    Reimbursement findByProcessInstanceId(String processInstanceId);
}
