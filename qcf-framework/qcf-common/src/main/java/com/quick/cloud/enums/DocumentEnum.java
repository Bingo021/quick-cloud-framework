package com.quick.cloud.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author zhangjiahao
 * @Description //文件枚举值
 * @Date 15:35 2025/5/6
 * @Param
 * @return
 **/
@Getter
@AllArgsConstructor
public enum DocumentEnum {

    REDIS_INSTALL("https://gitee.com/zhijiantianya/ruoyi-vue-pro/issues/I4VCSJ", "Redis 安装文档"),
    TENANT("https://doc.iocoder.cn", "SaaS 多租户文档");

    private final String url;
    private final String memo;

}