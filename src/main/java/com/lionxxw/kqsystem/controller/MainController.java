package com.lionxxw.kqsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主面板控制层
 *
 * Created by wangjian@baofoo.com on 2016/7/8.
 */
@Controller
public class MainController extends KqsController {

    @RequestMapping(value = "main")
    public String index(){

        return "kqs/main";
    }
}
