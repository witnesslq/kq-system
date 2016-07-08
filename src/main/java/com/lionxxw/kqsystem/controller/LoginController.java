package com.lionxxw.kqsystem.controller;

import com.lionxxw.kqsystem.code.constants.DataStatus;
import com.lionxxw.kqsystem.code.model.Response;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.ResponseUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.dto.UserDto;
import com.lionxxw.kqsystem.mode.LoginUser;
import com.lionxxw.kqsystem.service.UserService;
import com.lionxxw.kqsystem.utils.WebServletUtils;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


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
                                LoginUser loginUser = BeanUtils.createBeanByTarget(user, LoginUser.class);
                                sessionProvider.setAttribute(request, DataStatus.SESSION_USER, loginUser);
                                updateLoginInfo(user, request);
                                if (StringUtils.isTrimEmpty(returnUrl)){
                                    // 首页
                                    mv.setViewName("redirect:/kqs/main.do");
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
     * 更新用户的登陆ip和登陆时间
     * @param user
     * @param request
     */
    private void updateLoginInfo(UserDto user, HttpServletRequest request) {
        try {
            user.setLastLoginIp(WebServletUtils.getIpAddr(request));
            user.setLastLoginTime(new Date());
            userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 登出.
     *
     * @param request   the request
     * @param response  the response
     * @return the string
     * @throws Exception the exception
     * @author wangjian @baofoo.com
     * @date 2016 -07-07 19:01:22
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
        sessionProvider.logout(request, response);
        return "redirect:/login.do";
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
     * 2.加密用户密码
     * 3.提示用户注册成功
     *
     * @param user the user
     * @return the string
     * @author wangjian @baofoo.com
     * @date 2016 -07-07 19:01:22
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public void register(UserDto user, HttpServletResponse response){
        Response<String> res = new Response<String>();
        try {
            checkAccountException(user);
            encryptPwd(user);
            UserDto save = userService.save(user);
            res.setMessage("恭喜您注册成功!");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(DataStatus.HTTP_FAILE);
            res.setMessage(e.getMessage());
        }
        ResponseUtils.renderJson(response, res);
    }

    /**
     * 密码加密
     * @param user
     */
    private void encryptPwd(UserDto user) {
        user.setPassword(encodeMd5.encode(user.getPassword()));
    }

    /**
     * 校验注册信息是否合法
     * @param user
     */
    private void checkAccountException(UserDto user) throws Exception {
        if (ObjectUtils.isNull(user)){
            throw new RuntimeException("注册用户对象为空!");
        }
        if (StringUtils.isTrimEmpty((user.getAccount()))){
            throw new RuntimeException("注册账号不能为空!");
        }
        if (checkAccount(user.getAccount()) > 0){
            throw new RuntimeException("该账号已经被注册了!");
        }
        if (StringUtils.isTrimEmpty(user.getCname())){
            throw new RuntimeException("中文名不能为空!");
        }
        if (StringUtils.isTrimEmpty(user.getEname())){
            throw new RuntimeException("花名不能为空!");
        }
        if (StringUtils.isTrimEmpty(user.getEmail())){
            throw new RuntimeException("邮箱不能为空!");
        }
        if (StringUtils.isTrimEmpty(user.getMobile())){
            throw new RuntimeException("手机号码不能为空!");
        }
        if (null == user.getSex()){
            throw new RuntimeException("请选择性别!");
        }
        if (StringUtils.isTrimEmpty(user.getPassword())){
            throw new RuntimeException("密码不能为空!");
        }
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
        ResponseUtils.renderText(response,  checkAccount(account)+"");
    }

    private int checkAccount(String account) throws Exception{
        UserDto user = userService.getUserByAccount(account);
        int i = 0;
        if (ObjectUtils.notNull(user)){
            ++i;
        }
        return i;
    }
}