package com.nzhxty.activitidemo2.web;

import com.nzhxty.activitidemo2.entity.Reimbursement;
import com.nzhxty.activitidemo2.entity.User;
import com.nzhxty.activitidemo2.service.ReimbursementService;
import com.nzhxty.activitidemo2.vo.TaskVO;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class WorkflowCtrl {
    @Autowired
    private TaskService taskService;


    @Autowired
    private ReimbursementService reimbursementService;
    /**
     * 查询我的任务
     * @param session
     * @return
     */
    @GetMapping("/workflow/myTaskList")
    public List<TaskVO>  myTaskList(HttpSession session){
        User user = (User)session.getAttribute("user");


        //没有做认证提示，先去登陆
        List<Task> list = taskService.createTaskQuery().taskAssignee(user.getUsername()).orderByTaskCreateTime().desc().list();

        List<TaskVO> result = list.stream()
                //Task --> TaskVO
                .map(x -> new TaskVO(x.getId(), x.getName(), x.getDescription(), x.getAssignee(),x.getProcessInstanceId()))
                //将转好的数据封装到一个新list中
                .collect(Collectors.toList());

        return result;
    }

    /**
     * 查询请假详情
     * @param processInstanceId
     * @return
     */
    @GetMapping("/workflow/taskDetail/{processInstanceId}")
    public Reimbursement taskDetail(@PathVariable("processInstanceId") String processInstanceId){


        Reimbursement result = reimbursementService.findByProcessInstanceId(processInstanceId);


        return result;
    }


    /**
     * 完成任务
     * @param taskId
     * @return
     */
    @PutMapping("/workflow/complete/{taskId}")
    public String complete(@PathVariable("taskId") String taskId,HttpSession session){
        User user = (User)session.getAttribute("user");
        String manager = user.getManager();
        //完成该任务，并指定由他的上级来处理
        Map<String,Object> valMap = new HashMap<String, Object>();
        if(manager!=null&&!manager.isEmpty())
        {
            valMap.put("manager",manager);
        }
        taskService.complete(taskId,valMap);
        return "ok";
    }

}
