package com.lionxxw.kqsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class OptionTemplateDto implements Serializable {

    private Long id;

    private String name;

    private Date createTime;

    private Long createUserId;

    private Date lastUpdateTime;

    private Long lastUpdateUserId;

    private String note;
}