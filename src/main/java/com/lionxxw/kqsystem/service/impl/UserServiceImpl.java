package com.lionxxw.kqsystem.service.impl;

import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.model.PageResult;
import com.lionxxw.kqsystem.code.utils.BeanUtils;
import com.lionxxw.kqsystem.code.utils.ExceptionUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.dao.UserDao;
import com.lionxxw.kqsystem.dto.UserDto;
import com.lionxxw.kqsystem.entity.User;
import com.lionxxw.kqsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserDto save(UserDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        User User = BeanUtils.createBeanByTarget(obj, User.class);
        userDao.insertSelective(User);
        obj.setId(User.getId());
        return obj;
    }

    public boolean delById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, User.class, "delById");
        int i = userDao.deleteByPrimaryKey(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    public void update(UserDto obj) throws Exception {
        ExceptionUtils.checkObjIsNull(obj);
        ExceptionUtils.checkIdIsNull(obj.getId(), User.class, "update");
        User User = BeanUtils.createBeanByTarget(obj, User.class);
        userDao.updateByPrimaryKeySelective(User);
    }

    public UserDto getById(Integer id) throws Exception {
        ExceptionUtils.checkIdIsNull(id, User.class, "getById");
        User User = userDao.selectByPrimaryKey(id);
        UserDto dto = BeanUtils.createBeanByTarget(User, UserDto.class);
        return dto;
    }

    public List<UserDto> queryByParam(UserDto obj) throws Exception {
        List<User> Users = userDao.queryByParam(obj, null);
        if (null != Users && Users.size() > 0){
            List<UserDto> list = BeanUtils.createBeanListByTarget(Users, UserDto.class);
            return list;
        }
        return null;
    }

    public PageResult<UserDto> queryByPage(UserDto obj, PageQuery query) throws Exception {
        int total = userDao.countByParam(obj);
        if (total > 0){
            query.setTotal(total);
            List<User> Users = userDao.queryByParam(obj, query);
            List<UserDto> list = BeanUtils.createBeanListByTarget(Users, UserDto.class);
            return new PageResult<UserDto>(query, list);
        }
        return null;
    }

    public UserDto getUserByAccount(String account) throws Exception {
        UserDto user = new UserDto();
        user.setAccount(account);
        List<UserDto> users = queryByParam(user);
        if (ObjectUtils.notEmpty(users)){
            return users.get(0);
        }
        return null;
    }
}