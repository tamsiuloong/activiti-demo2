package com.nzhxty.activitidemo2.dao;

import com.nzhxty.activitidemo2.entity.Reimbursement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author coach tam
 * @date 2018/1/6
 */
@Repository
public interface ReimbusementDao extends PagingAndSortingRepository<Reimbursement, Integer> {

    Reimbursement findByProcessInstanceId(String processInstanceId);
}
