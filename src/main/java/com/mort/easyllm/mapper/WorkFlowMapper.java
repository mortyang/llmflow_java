package com.mort.easyllm.mapper;

import com.mort.easyllm.mapper.po.WorkFlowPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mort
 */
@Mapper
public interface WorkFlowMapper {


    /**
     * @param id workflow的id
     * @return null
     * @Author Mort
     * @Date 2024-08-07
     */
    WorkFlowPo fetchWorkFlow(int id);

    /**
     * 增加workflow流
     *
     * @param workFlowName workflow的名称
     * @Author Mort
     * @Date 2024-08-07
     */
    void addWorkFlow(String workFlowName);

    /**
     * 删除workflow流
     *
     * @param id workflow的id
     * @Author Mort
     * @Date 2024-08-07
     */
    void deleteWorkFlow(int id);

    /**
     * demo
     *
     * @return null
     * @Author Mort
     * @Date 2024-08-07
     */
    List<WorkFlowPo> getWorkFlowList();

    /**
     * 获取对应权限的workflow
     *
     * @return null
     * @Author Mort
     * @Date 2024-08-07
     */
    List<WorkFlowPo> getWorkFlowListByPermissionGroup(String permissionGroup);

    /**
     * 获取保存的workflow流的json供回显
     *
     * @param id workflow的id
     * @return null
     * @Author Mort
     * @Date 2024-08-07
     */
    String getWorkFlowJson(int id);

    /**
     * 保存workFlow流
     *
     * @param workFlowPo 除了name其余必填
     * @Author Mort
     * @Date 2024-08-07
     */
    void uploadWorkFlow(@Param("workFlowPo") WorkFlowPo workFlowPo);


    /**
     * 获取开启的workflow流
     *
     * @Author Mort
     * @Date 2024-08-07
     */
    List<WorkFlowPo> getAllEnabledWorkFlowName();


    /**
     * 开关workflow流
     *
     * @param id      workflowid
     * @param enabled 是否启用
     * @Author Mort
     * @Date 2024-08-07
     */
    @Update("update workflow_info set enabled=#{enabled} where id=#{id}")
    void setEnabledById(int id, Boolean enabled);



}
