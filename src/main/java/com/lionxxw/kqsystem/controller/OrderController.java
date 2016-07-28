package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.dto.OrderDinnerDto;
import com.lionxxw.kqsystem.dto.OrderDinnerOptionDto;
import com.lionxxw.kqsystem.service.OrderDinnerOptionService;
import com.lionxxw.kqsystem.service.OrderDinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订餐操作控制层
 *
 * 发布订餐 /kqs/order/publish.do
 * 订餐模板 /kqs/order/template.do
 * 今日订餐 /kqs/order/now.do
 * Created by wangjian@baofoo.com on 2016/7/27.
 */
@Controller
public class OrderController extends KqsController {
    @Autowired
    private OrderDinnerService orderDinnerService;
    @Autowired
    private OrderDinnerOptionService optionService;

    @RequestMapping(value = "/order/publish")
    public ModelAndView publish() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.addObject("order", getNowDateOrder());
        mv.setViewName("/kqs/order/publish");
        return mv;
    }

    @RequestMapping(value = "/order/template")
    public ModelAndView template() throws Exception{
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    @RequestMapping(value = "/order/now")
    public ModelAndView now() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.addObject("order", getNowDateOrder());
        mv.setViewName("/kqs/order/now");
        return mv;
    }

    private OrderDinnerDto getNowDateOrder() throws Exception {
        OrderDinnerDto nowOrder = orderDinnerService.getOrderDinnerByNow();
        if (null != nowOrder){
            List<OrderDinnerOptionDto> options = optionService.queryOptionByOrderId(nowOrder.getId());
            nowOrder.setOptions(options);
        }
        return nowOrder;
    }
}
