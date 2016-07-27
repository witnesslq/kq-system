package com.lionxxw.kqsystem.dao;

import com.lionxxw.kqsystem.code.base.MyBatisBaseDao;
import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.dto.OptionTemplateDto;
import com.lionxxw.kqsystem.entity.OptionTemplate;
import com.lionxxw.kqsystem.entity.OptionTemplateExample;
import com.lionxxw.kqsystem.mapper.OptionTemplateMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The type Order dinner dao.
 * Created by wangjian@baofoo.com on 2016-07-27 14:48:24
 */
@Repository
public class OptionTemplateDao extends MyBatisBaseDao<OptionTemplateDto> {

    @Autowired
    @Getter
    private OptionTemplateMapper mapper;

    public List<OptionTemplate> queryByParam(OptionTemplateDto obj, PageQuery query) throws Exception{
        OptionTemplateExample example = new OptionTemplateExample();
        OptionTemplateExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("order_date desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("order_date desc");
        }
        List<OptionTemplate> results = mapper.selectByExample(example);
        return results;
    }

    public int countByParam(OptionTemplateDto obj) throws Exception{
        OptionTemplateExample example = new OptionTemplateExample();
        OptionTemplateExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        return mapper.countByExample(example);
    }

    private void assemblyParams(OptionTemplateDto params, OptionTemplateExample.Criteria criteria) {
        if (null != params) {
            if (ObjectUtils.notNull(params.getId())){
                criteria.andIdEqualTo(params.getId());
            }
            if (ObjectUtils.notNull(params.getCreateUserId())){
                criteria.andCreateUserIdEqualTo(params.getCreateUserId());
            }
        }
    }
}
