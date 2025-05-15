package com.quick.cloud.enums;

import cn.hutool.core.util.ArrayUtil;
import com.quick.cloud.core.ArrayValualb;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zhangjiahao
 * @Description //时间枚举值
 * @Date 14:47 2025/5/6
 * @Param
 * @return
 **/
@Getter
@AllArgsConstructor
public enum DateIntervalEnum implements ArrayValualb<Integer> {
    DAY(1,"天"),
    WEEK(2,"周"),
    MONTH(3,"月"),
    QUARTER(4,"季度"),
    YEAR(5,"年");

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(DateIntervalEnum::getInterval).toArray(Integer[]::new);
    /**
     * 类型
     **/
    private final Integer interval;
    /**
     * 名称
     **/
    private final String name;
    @Override
    public Integer[] array() {
        return ARRAYS;
    }

    public static DateIntervalEnum vauleOf(Integer interval) {
        return ArrayUtil.firstMatch(item -> item.getInterval().equals(interval),DateIntervalEnum.values());
    }
}
