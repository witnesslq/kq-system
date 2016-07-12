package com.lionxxw.kqsystem.service.impl;

import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.ExceptionUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.dao.WorkingLogDao;
import com.lionxxw.kqsystem.dto.WorkingLogDto;
import com.lionxxw.kqsystem.entity.WorkingLog;
import com.lionxxw.kqsystem.service.WorkingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingLogServiceImpl implements WorkingLogService {

    @Autowired
    private WorkingLogDao workingLogDao;

    public WorkingLogDto save(WorkingLogDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        WorkingLog workingLog = BeanUtils.createBeanByTarget(obj, WorkingLog.class);
        workingLogDao.insertSelective(workingLog);
        obj.setId(workingLog.getId());
        return obj;
    }

    public boolean delById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, WorkingLog.class, "delById");
        int i = workingLogDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(WorkingLogDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), WorkingLog.class, "update");
        WorkingLog workingLog = BeanUtils.createBeanByTarget(obj, WorkingLog.class);
        workingLogDao.updateByPrimaryKeySelective(workingLog);
    }

    public WorkingLogDto getById(Long id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, WorkingLog.class, "getById");
        WorkingLog workingLog = workingLogDao.selectByPrimaryKey(id);
        WorkingLogDto dto = BeanUtils.createBeanByTarget(workingLog, WorkingLogDto.class);
        return dto;
    }

    public List<WorkingLogDto> queryByParam(WorkingLogDto obj) throws Exception {
        List<WorkingLog> workingLogs = workingLogDao.queryByParam(obj, null);
        if (ObjectUtils.notEmpty(workingLogs)){
            List<WorkingLogDto> list = BeanUtils.createBeanListByTarget(workingLogs, WorkingLogDto.class);
            return list;
        }
        return null;
    }

    public PageResult<WorkingLogDto> queryByPage(WorkingLogDto obj, PageQuery query) throws Exception {
        int total = workingLogDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<WorkingLog> workingLogs = workingLogDao.queryByParam(obj, query);
            List<WorkingLogDto> list = BeanUtils.createBeanListByTarget(workingLogs, WorkingLogDto.class);
            return new PageResult<WorkingLogDto>(query, list);
        }
        return null;
    }

    public void batchDelWorkingLog(Long[] ids) throws Exception {
        workingLogDao.batchDelWorkingLog(ids);
    }
}