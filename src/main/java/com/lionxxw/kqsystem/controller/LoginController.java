package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.ResponseUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.dto.UserDto;
import com.lionxxw.kqsystem.service.UserService;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登陆管理
 * The type Login controller.
 * Created by wangjian@baofoo.com on 2016-07-07 16:29:37
 */
@Controller
public class LoginController extends BaseController{
    @Autowired
    private ImageCaptchaService imageCaptchaService;
    @Autowired
    private UserService userService;

    /**
     * 登录页面
     *
     * @return the string
     * @throws Exception the exception
     * @author wangjian @baofoo.com
     * @date 2016 -07-07 19:01:22
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() throws Exception{
        return "login";
    }

    /**
     * <p>Description: 用户登录接口 </p>
     * 业务逻辑:
     * 1.验证码是否为空
     * 2.验证码是否正确
     * 3.用户名是否为空
     * 4.密码是否为空
     * 5.用户名是否正确
     * 6.密码是否正确
     * 7.放进session 跳转returnUrl
     *
     * @param request   the request
     * @param returnUrl 用户登录成功跳转页面
     * @param account   账号
     * @param password  密码
     * @param captcha   验证码
     * @return string model and view
     * @throws Exception the exception
     * @author wangxiang @baofoo.com
     * @date 16 /6/11 下午10:26
     * @version 1.0
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, String returnUrl, String account, String password, String captcha) throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        if (StringUtils.isTrimEmpty(captcha)){
            mv.addObject("error","请填写验证码");
        }else{
            if(imageCaptchaService.validateResponseForID(sessionProvider.getSessionId(request), captcha)){
                if (StringUtils.isTrimEmpty(account)){
                    mv.addObject("error","请输入账号");
                }else{
                    if (StringUtils.isTrimEmpty(password)){
                        mv.addObject("error","请输入密码");
                    }else{
                        UserDto user = userService.getUserByAccount(account);
                        if (ObjectUtils.notNull(user)){
                            if (encodeMd5.encode(password).equals(user.getPassword())){
                                sessionProvider.setAttribute(request, DataStatus.SESSION_USER, user);
                                if (StringUtils.isTrimEmpty(returnUrl)){
                                    // 首页
                                    mv.setViewName("redirect:/kqs/index.do");
                                }else{
                                    mv.setViewName("redirect:"+returnUrl);
                                }
                            }else{
                                mv.addObject("error","用户/密码不正确");
                            }
                        }else{
                            mv.addObject("error","用户不存在");
                        }
                    }
                }
            }else{
                mv.addObject("error","验证码输入有误");
            }
        }

        return mv;
    }

    /**
     * 登出.
     *
     * @param returnUrl the return url
     * @param request   the request
     * @param response  the response
     * @return the string
     * @throws Exception the exception
     * @author wangjian @baofoo.com
     * @date 2016 -07-07 19:01:22
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(String returnUrl ,HttpServletRequest request,HttpServletResponse response) throws Exception{
        sessionProvider.logout(request, response);
        return "redirect:" + returnUrl;
    }

    /**
     * 跳转注册页面
     *
     * @return the string
     * @author wangjian @baofoo.com
     * @date 2016 -07-07 19:01:22
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    /**
     * 注册操作
     * 1.校验注册信息是否合法
     * 2.注册以后,将新用户信息保存于session中,直接进入首页
     *
     * @param user the user
     * @return the string
     * @author wangjian @baofoo.com
     * @date 2016 -07-07 19:01:22
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView register(UserDto user){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/kqs/index.do");
        return mv;
    }

    /**
     * 校验该账号是否存在
     *
     * @param account  the account
     * @param response the response
     * @throws Exception the exception
     * @author wangjian @baofoo.com
     * @date 2016 -07-07 20:47:56
     */
    @RequestMapping(value = "checkAccount")
    public void checkAccount(String account, HttpServletResponse response) throws Exception{
        UserDto user = userService.getUserByAccount(account);
        int i = 0;
        if (ObjectUtils.notNull(user)){
            ++i;
        }
        ResponseUtils.renderText(response, i+"");
    }
}