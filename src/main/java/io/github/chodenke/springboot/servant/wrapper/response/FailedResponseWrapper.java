package io.github.chodenke.springboot.servant.wrapper.response;

import io.github.chodenke.springboot.servant.wrapper.base.IFailureEnumBase;
import io.github.chodenke.springboot.servant.wrapper.exception.BizException;

/**
 * <h2>失败返回结果包装</h2>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public final class FailedResponseWrapper extends AbstractResponseWrapper {

    /** 默认的失败返回结果描述信息 */
    private static final String DEFAULT_FAILURE_MSG = "execution failed";

    /**
     * 构造方法，由 失败返回结果编码 和 失败返回结果描述信息 构造一个失败返回结果的包装
     *
     * @param code 败返回结果编码
     * @param msg  失败返回结果描述信息
     */
    public FailedResponseWrapper(String code, String msg) {
        super(false, code, msg);
    }

    /**
     * 构造方法，由 失败返回结果编码 构造一个失败返回结果的包装，使用默认的失败返回结果描述信息
     *
     * @param code 失败返回结果编码
     */
    public FailedResponseWrapper(String code) {
        this(code, DEFAULT_FAILURE_MSG);
    }

    /**
     * 构造方法，由 失败返回结果枚举 构造一个失败返回结果的包装
     *
     * @param failureEnumBase 失败返回结果枚举
     */
    public FailedResponseWrapper(IFailureEnumBase failureEnumBase) {
        this(failureEnumBase.getIntegratedCode(), failureEnumBase.getDesc());
    }

    /**
     * 构造方法，由 自定义业务异常 构造一个失败返回结果的包装
     *
     * @param bizeException 业务异常
     */
    public FailedResponseWrapper(BizException bizeException) {
        this(bizeException.getCode(), bizeException.getMessage());
    }
}
