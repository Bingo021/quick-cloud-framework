package com.quick.cloud.pojo;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quick.cloud.exception.ServerException;
import com.quick.cloud.exception.ServiceException;
import com.quick.cloud.exception.enums.GlobalErrorCodeConstants;
import com.quick.cloud.exception.ErrorCode;
import com.quick.cloud.exception.util.ServiceExceptionUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName CommonResult
 * @Description //返回通用泛型类
 * @Author zhangjiahao
 * @Date 2025/5/6 15:36
 * @Version
 **/
@Data
public class CommonResult<T> implements Serializable {
    /**
     * 全局错误码
     *
     * @see ErrorCode#getCode()
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String msg;
    /**
     * 返回数据
     * @see ErrorCode#getMsg()
     */
    private T data;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.msg = "";
        return result;
    }

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     *
     * 因为 A 方法返回的 CommonResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T> 返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> CommonResult<T> error(CommonResult<?> result){
        return error(result.getCode(), result.getMsg());
    }

    public static <T> CommonResult<T> error(Integer code, String msg) {
        Assert.notEquals(GlobalErrorCodeConstants.SUCCESS.getCode(),code,"必须是错误的 code");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <T> CommonResult<T> error(ErrorCode errorCode, Object... params) {
        Assert.notEquals(GlobalErrorCodeConstants.SUCCESS.getCode(),errorCode.getCode(),"必须是错误的 code");
        CommonResult<T> result = new CommonResult<>();
        result.code = errorCode.getCode();
        result.msg = ServiceExceptionUtil.doFormat(errorCode.getCode(), errorCode.getMsg(), params);
        return result;
    }

    public static <T> CommonResult<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static boolean isSuccess(Integer code) {
        return Objects.equals(code, GlobalErrorCodeConstants.SUCCESS.getCode());
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isSuccess(){
        return isSuccess(code);
    }

    @JsonIgnore // 避免 jackson 序列化
    public boolean isError(){
        return !isSuccess();
    }

    // === 异常相关方法 ====
    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     */
    public void checkError() throws ServerException{
        if (isSuccess()) {
            return;
        }
        // 直接抛出业务异常
        throw new ServerException(code, msg);
    }

    /**
     * 判断是否有异常。如果有，则抛出 {@link ServiceException} 异常
     * 如果没有，则返回 {@link #data} 数据
     */
    @JsonIgnore // 避免 jackson 序列化
    public T getCheckedData() {
        checkError();
        return data;
    }

    public static <T> CommonResult<T> error(ServiceException serviceException) {
        return error(serviceException.getCode(), serviceException.getMessage());
    }

}
