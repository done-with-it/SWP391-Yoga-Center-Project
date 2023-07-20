package com.fptyoga.yogacenter.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyTotal {
    private Integer month;
    private Long totalAmount;

    public MonthlyTotal(Integer month, Long totalAmount) {
        this.month = month;
        this.totalAmount = totalAmount;
    }
}
