package com.quick.cloud.enums;

import cn.hutool.core.util.ArrayUtil;
import com.quick.cloud.core.ArrayValualb;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Author zhangjiahao
 * @Description //全局用户类型枚举
 * @Date 15:34 2025/5/6
 * @Param
 * @return
 **/
@AllArgsConstructor
@Getter
public enum UserTypeEnum implements ArrayValualb<Integer> {

    MEMBER(1, "会员"), // 面向 c 端，普通用户
    ADMIN(2, "管理员"); // 面向 b 端，管理后台

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(UserTypeEnum::getValue).toArray(Integer[]::new);

    /**
     * 类型
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    public static UserTypeEnum valueOf(Integer value) {
        return ArrayUtil.firstMatch(userType -> userType.getValue().equals(value), UserTypeEnum.values());
    }

    @Override
    public Integer[] array() {
        return ARRAYS;
    }
}