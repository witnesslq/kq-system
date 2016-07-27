package com.lionxxw.kqsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class OrderDinnerDto implements Serializable {
    private Long id;

    private Date orderDate;

    private String note;

    private Long publishUserId;

    private Date publishTime;

    private Date createTime;

    private Long createUserId;

    private Integer state;

    private Date lastUpdateTime;

    private Date endTime;

    public enum OrderState{
        INIT(0,"未发布"),PUBLISH(1, "已发布"),CANCEL(-1, "已作废"),FINISH(2, "已完成");
        private int state;
        private String value;

        OrderState(int state, String value) {
            this.state = state;
            this.value = value;
        }

        public static String getValue(int state){
            OrderState[] values = OrderState.values();
            for (OrderState v : values){
                if (v.state == state){
                    return v.value;
                }
            }
            return "";
        }
    }
}