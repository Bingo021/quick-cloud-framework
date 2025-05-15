package com.quick.cloud.enums;

import com.quick.cloud.core.ArrayValualb;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * @Author zhangjiahao
 * @Description //终端类型枚举
 * @Date 15:35 2025/5/6
 * @Param
 * @return
 **/
@RequiredArgsConstructor
@Getter
public enum TerminalEnum implements ArrayValualb<Integer> {

    UNKNOWN(0, "未知"), // 目的：在无法解析到 terminal 时，使用它
    WECHAT_MINI_PROGRAM(10, "微信小程序"),
    WECHAT_WAP(11, "微信公众号"),
    H5(20, "H5 网页"),
    APP(31, "手机 App"),
    ;

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(TerminalEnum::getTerminal).toArray(Integer[]::new);

    /**
     * 终端
     */
    private final Integer terminal;
    /**
     * 终端名
     */
    private final String name;

    @Override
    public Integer[] array() {
        return ARRAYS;
    }
}