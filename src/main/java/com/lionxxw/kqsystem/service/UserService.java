package com.lionxxw.kqsystem.service;

import com.lionxxw.kqsystem.code.base.BaseService;
import com.lionxxw.kqsystem.dto.UserDto;


/**
 * The interface User service.
 * Created by wangjian@baofoo.com on 2016-07-07 15:57:04
 */
public interface UserService extends BaseService<UserDto> {

    /**
     * Gets user by account.
     *
     * @param account the account
     * @return the user by account
     * @throws Exception the exception
     * @author wangjian @baofoo.com
     * @date 2016 -07-07 16:34:39
     */
    UserDto getUserByAccount(String account) throws Exception;
}