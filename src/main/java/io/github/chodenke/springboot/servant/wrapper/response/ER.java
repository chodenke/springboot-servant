package io.github.chodenke.springboot.servant.wrapper.response;

import io.github.chodenke.springboot.servant.wrapper.base.IFailureEnumBase;
import io.github.chodenke.springboot.servant.wrapper.exception.BizException;

/**
 * <h2>失败返回结果包装</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>暂无</li>
 * </ul>
 * <p>datetime: 2024/10/8 10:24</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public final class ER<T> extends FailedResponseWrapper<T> {

    /**
     * 构造方法，由 失败返回结果编码 和 失败返回结果描述信息 构造一个失败返回结果的包装
     *
     * @param code 败返回结果编码
     * @param msg  失败返回结果描述信息
     */
    public ER(String code, String msg) {
        super(code, msg);
    }

    /**
     * 构造方法，由 失败返回结果编码 构造一个失败返回结果的包装，使用默认的失败返回结果描述信息
     *
     * @param code 失败返回结果编码
     */
    public ER(String code) {
        super(code);
    }

    /**
     * 构造方法，由 失败返回结果枚举 构造一个失败返回结果的包装
     *
     * @param failureEnumBase 失败返回结果枚举
     */
    public ER(IFailureEnumBase failureEnumBase) {
        super(failureEnumBase);
    }

    /**
     * 构造方法，由 自定义业务异常 构造一个失败返回结果的包装
     *
     * @param bizeException 业务异常
     */
    public ER(BizException bizeException) {
        super(bizeException);
    }

}
