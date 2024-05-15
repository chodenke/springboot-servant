package io.github.chodenke.springboot.servant.wrapper.exception;

import io.github.chodenke.springboot.servant.wrapper.base.IFailureEnumBase;

import java.io.Serial;

/**
 * <h2>业务异常类</h2>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public class BizException extends RuntimeException {

    /** 序列化id */
    @Serial
    private static final long serialVersionUID = 1L;

    /** 异常编码 */
    private final String code;

    /**
     * 由一个 失败返回结果枚举实例 构造一个 业务异常实例
     *
     * @param failureEnum 失败返回结果枚举实例
     */
    public BizException(IFailureEnumBase failureEnum) {
        super(failureEnum.getDesc());
        this.code = failureEnum.getIntegratedCode();
    }

    /** code 属性的 getter */
    public String getCode() {
        return code;
    }
}
