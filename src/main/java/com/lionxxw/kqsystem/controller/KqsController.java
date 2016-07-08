package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.mode.LoginUser;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by wangjian@baofoo.com on 2016/7/8.
 */
@RequestMapping(value = "kqs")
public class KqsController extends BaseController {

    protected LoginUser getLoginUser(HttpServletRequest request){
        LoginUser user = (LoginUser) sessionProvider.getAttribute(request, DataStatus.SESSION_USER);
        return user;
    }
}
