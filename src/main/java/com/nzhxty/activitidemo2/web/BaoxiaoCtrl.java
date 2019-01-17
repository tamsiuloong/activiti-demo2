package com.nzhxty.activitidemo2.web;

import com.nzhxty.activitidemo2.entity.Reimbursement;
import com.nzhxty.activitidemo2.entity.User;
import com.nzhxty.activitidemo2.service.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 报销控制器
 */
@RestController
public class BaoxiaoCtrl {
    @Autowired
    private ReimbursementService reimbursementService;
    @PostMapping("/baoxiao")
    public String baoxiao(@RequestBody Reimbursement reimbursement, HttpSession session)
    {
        User user = (User)session.getAttribute("user") ;
        reimbursementService.baoxiao(reimbursement,user);
        return "ok";
    }
}
