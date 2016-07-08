package com.lionxxw.kqsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class WorkingLogDto implements Serializable {

    private Long id;

    private Long userId;

    private Date workDate;

    private Date createTime;

    private Date lastUpdateTime;

    private Boolean isDel;

    private String note;

    private String workDateS; // 格式 yyyy-MM-dd

    private String workDateE;
}