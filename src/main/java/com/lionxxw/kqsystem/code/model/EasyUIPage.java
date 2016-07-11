package com.lionxxw.kqsystem.code.model;

import com.lionxxw.kqsystem.code.utils.ObjectUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * EasyUI 分页数据模板
 *
 * Created by wangjian@baofoo.com on 2016/7/11.
 */
@NoArgsConstructor
public class EasyUIPage<T> implements Serializable{

    @Getter
    @Setter
    private long total;

    @Getter
    @Setter
    private List<T> rows;


    /**
     * 将PageResult数据实例化一个easyUIpage数据
     *
     * @param page the page
     * @author wangjian @baofoo.com
     * @date 2016 -07-11 10:35:48
     */
    public EasyUIPage(PageResult<T> page){
        if (ObjectUtils.notNull(page) && ObjectUtils.notEmpty(page.getResults())){
            this.total = page.getTotal();
            rows = page.getResults();
        }
    }
}
