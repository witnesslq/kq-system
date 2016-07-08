package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;
import com.lionxxw.kqsystem.code.model.Response;
import com.lionxxw.kqsystem.code.utils.ResponseUtils;
import com.lionxxw.kqsystem.dto.OvertimeDto;
import com.lionxxw.kqsystem.entity.Overtime;
import com.lionxxw.kqsystem.mode.LoginUser;
import com.lionxxw.kqsystem.service.OvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 个人加班控制层
 * 1.分页列表查询
 * 2.新增/编辑操作
 * 3.单个删除/批量删除操作
 *
 * Created by wangjian@baofoo.com on 2016/7/8.
 */
@Controller
public class OvertimeController extends KqsController {

    @Autowired
    private OvertimeService overtimeService;

    /**
     * 列表查询
     * @return
     */
    @RequestMapping(value = "/overtime/list")
    public String overtimeList(){
        return "overtime/list";
    }

    /**
     * ajax 获取加班记录数据(分页)
     * @param request
     * @param response
     * @param param
     * @param query
     * @throws Exception
     */
    @RequestMapping(value = "/overtime/getOvertimeDatas")
    public void getOvertimeDatas(HttpServletRequest request, HttpServletResponse response, OvertimeDto param, PageQuery query){
        Response<PageResult<OvertimeDto>> res = new Response<PageResult<OvertimeDto>>();
        LoginUser loginUser = getLoginUser(request);
        param.setUserId(loginUser.getId());
        PageResult<OvertimeDto> datas = null;
        try {
            datas = overtimeService.queryByPage(param, query);
            res.setData(datas);
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }

        ResponseUtils.renderJson(response, res);
    }

    /**
     * 跳转加班新增页面
     * @return
     */
    @RequestMapping(value = "/overtime/add", method = RequestMethod.GET)
    public String add(){
        return "overtime/add";
    }

    /**
     * 新增加班记录操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/overtime/add", method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response, OvertimeDto dto){
        Response<PageResult<OvertimeDto>> res = new Response<PageResult<OvertimeDto>>();
        LoginUser loginUser = getLoginUser(request);
        dto.setUserId(loginUser.getId());
        try {
            overtimeService.save(dto);
            res.setMessage("新增加班记录成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 跳转加班记录编辑页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/overtime/edit", method = RequestMethod.GET)
    public String edit(Long id){
        return "overtime/edit";
    }

    /**
     * 修改加班记录操作
     * @param dto
     * @return
     */
    @RequestMapping(value = "/overtime/edit", method = RequestMethod.POST)
    public void edit(HttpServletRequest request, HttpServletResponse response, OvertimeDto dto){
        Response<PageResult<OvertimeDto>> res = new Response<PageResult<OvertimeDto>>();
        LoginUser loginUser = getLoginUser(request);
        dto.setUserId(loginUser.getId());
        try {
            overtimeService.update(dto);
            res.setMessage("修改加班记录成功!");
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
    public void del(HttpServletRequest request, HttpServletResponse response, Long[] ids){
        Response<PageResult<OvertimeDto>> res = new Response<PageResult<OvertimeDto>>();
        try {
            overtimeService.batchDelOvertime(ids);
            res.setMessage("删除加班记录成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }
}