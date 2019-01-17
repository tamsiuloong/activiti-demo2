package com.nzhxty.activitidemo2.service;

import com.nzhxty.activitidemo2.dao.ReimbusementDao;
import com.nzhxty.activitidemo2.entity.Reimbursement;
import com.nzhxty.activitidemo2.entity.User;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ReimbursementServiceImpl implements ReimbursementService {

    @Autowired
    private ReimbusementDao reimbusementDao;

    @Autowired
    private RuntimeService runtimeService;
    @Override
    public void baoxiao(Reimbursement reimbursement, User user) {

        //1.开启申请流程
            //通过RuntimeService -->开启报销流程
            //用来封装流程所需要的变量
        Map<String,Object> valMap = new HashMap<String, Object>();
            //指定由德华发出报销请假(employee变量在画流程图中定义的,可以返回 baoxiao.bpmn中查看)
        valMap.put("manager",user.getUsername());
            //流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("baoxiao",valMap);

        //2.数据库记录报销信息
            //业务数据绑定流程id
        reimbursement.setProcessInstanceId(processInstance.getProcessInstanceId());
        reimbursement.setName(user.getUsername());
        reimbusementDao.save(reimbursement);
    }

    @Override
    public Reimbursement findByProcessInstanceId(String processInstanceId) {
        return reimbusementDao.findByProcessInstanceId(processInstanceId);
    }
}
