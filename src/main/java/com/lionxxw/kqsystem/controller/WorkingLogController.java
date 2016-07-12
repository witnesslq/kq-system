package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.model.*;
import com.lionxxw.kqsystem.code.utils.ResponseUtils;
import com.lionxxw.kqsystem.dto.WorkingLogDto;
import com.lionxxw.kqsystem.mode.LoginUser;
import com.lionxxw.kqsystem.service.WorkingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 个人工作日志管理控制层
 * 1.分页列表查询
 * 2.新增/编辑操作
 * 3.单个删除/批量删除操作
 *
 * Created by wangjian@baofoo.com on 2016/7/12.
 */
@Controller
public class WorkingLogController extends KqsController {

    @Autowired
    private WorkingLogService workingLogService;

    /**
     * 列表查询
     * @return
     */
    @RequestMapping(value = "/workingLog/list")
    public String workingLogList(){
        return "kqs/workingLog/list";
    }

    /**
     * ajax 获取工作日志数据(分页)
     * @param request
     * @param response
     * @param param
     * @param query
     * @throws Exception
     */
    @RequestMapping(value = "/workingLog/getWorkingLogDatas")
    public void getWorkingLogDatas(HttpServletRequest request, HttpServletResponse response, WorkingLogDto param, EasyUiPageQuery query){
        LoginUser loginUser = getLoginUser(request);
        param.setUserId(loginUser.getId());
        PageResult<WorkingLogDto> datas = null;
        try {
            datas = workingLogService.queryByPage(param, new PageQuery(query));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseUtils.renderJson(response, new EasyUIPage<WorkingLogDto>(datas));
    }

    /**
     * 新增工作日志操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/workingLog/add", method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response, WorkingLogDto dto){
        Response<PageResult<WorkingLogDto>> res = new Response<PageResult<WorkingLogDto>>();
        LoginUser loginUser = getLoginUser(request);
        dto.setUserId(loginUser.getId());
        try {
            workingLogService.save(dto);
            res.setMessage("新增工作日志成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 跳转工作日志编辑页面
     *
     * @param response the response
     * @param id       the id
     * @author wangjian @baofoo.com
     * @date 2016 -07-11 10:25:23
     */
    @RequestMapping(value = "/workingLog/edit", method = RequestMethod.GET)
    public void edit(HttpServletResponse response, Long id){
        WorkingLogDto overtime = null;
        try {
            overtime = workingLogService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseUtils.renderJson(response, overtime);
    }

    /**
     * 修改工作日志操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/workingLog/edit", method = RequestMethod.POST)
    public void edit(HttpServletRequest request, HttpServletResponse response, WorkingLogDto dto){
        Response<WorkingLogDto> res = new Response<WorkingLogDto>();
        LoginUser loginUser = getLoginUser(request);
        dto.setUserId(loginUser.getId());
        try {
            dto.setLastUpdateTime(new Date());
            workingLogService.update(dto);
            res.setMessage("修改工作日志成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 单个删除/批量删除操作
     * @param request
     * @param response
     * @param ids
     */
    @RequestMapping(value = "/workingLog/del")
    public void del(HttpServletRequest request, HttpServletResponse response, Long[] ids){
        Response<PageResult<WorkingLogDto>> res = new Response<PageResult<WorkingLogDto>>();
        try {
            workingLogService.batchDelWorkingLog(ids);
            res.setMessage("删除工作日志成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }
}