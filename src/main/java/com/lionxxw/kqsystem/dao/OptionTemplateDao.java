package com.lionxxw.kqsystem.dao;

import com.lionxxw.kqsystem.code.base.MyBatisBaseDao;
import com.lionxxw.kqsystem.code.model.PageQuery;
import com.lionxxw.kqsystem.code.utils.DateUtils;
import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import com.lionxxw.kqsystem.code.utils.StringUtils;
import com.lionxxw.kqsystem.dto.OptionTemplateDto;
import com.lionxxw.kqsystem.entity.OptionTemplate;
import com.lionxxw.kqsystem.entity.OptionTemplateExample;
import com.lionxxw.kqsystem.entity.WorkingLog;
import com.lionxxw.kqsystem.entity.WorkingLogExample;
import com.lionxxw.kqsystem.mapper.OptionTemplateMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


/**
 * The type Order dinner dao.
 * Created by wangjian@baofoo.com on 2016-07-27 14:48:24
 */
@Repository
public class OptionTemplateDao extends MyBatisBaseDao<OptionTemplate> {

    @Autowired
    @Getter
    private OptionTemplateMapper mapper;

    public List<OptionTemplate> queryByParam(OptionTemplateDto obj, PageQuery query) throws Exception{
        OptionTemplateExample example = new OptionTemplateExample();
        OptionTemplateExample.Criteria criteria = example.createCriteria();
        assemblyParams(obj, criteria);
        if(null != query){
            example.setOrderByClause("count desc limit "+query.getStartNum() +"," + query.getPageSize());
        }else{
            example.setOrderByClause("count desc");
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
            if (StringUtils.notTrimEmpty(params.getCreateTimeS())){
                criteria.andCreateTimeGreaterThanOrEqualTo(DateUtils.getDate(params.getCreateTimeS(), DateUtils.DATE_FROMAT_DAY));
            }
            if (StringUtils.notTrimEmpty(params.getCreateTimeE())){
                criteria.andCreateTimeLessThan(DateUtils.getDate(params.getCreateTimeE(), DateUtils.DATE_FROMAT_DAY, 1));
            }
        }
    }

    public void batchDelTemplate(Long[] ids) throws Exception {
        OptionTemplateExample example = new OptionTemplateExample();
        OptionTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        mapper.deleteByExample(example);
    }
}
