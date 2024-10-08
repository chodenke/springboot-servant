package io.github.chodenke.springboot.servant.wrapper.response;

import io.github.chodenke.springboot.servant.wrapper.base.ISuccessEnumBase;

/**
 * <h2>成功返回结果包装</h2>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public sealed class SucceededResponseWrapper<T> extends R<T> permits SR {

    /** 默认的成功返回结果描述信息 */
    protected static final String DEFAULT_SUCCESS_MSG = "execution succeeded";

    /**
     * 返回数据
     */
    private final T data;

    /**
     * 构造方法，由 返回数据、成功返回结果编码 和 成功返回结果描述信息 构造一个成功返回结果的包装
     *
     * @param data    返回数据
     * @param code    成功返回结果编码
     * @param msg 成功返回结果描述信息
     */
    public SucceededResponseWrapper(T data, String code, String msg) {
        super(true, code, msg);
        this.data = data;
    }

    /**
     * 构造方法，由 返回数据 和 成功返回结果编码 构造一个成功返回结果的包装，使用默认的成功返回结果描述信息
     *
     * @param data 返回数据
     * @param code 成功返回结果编码
     */
    public SucceededResponseWrapper(T data, String code) {
        this(data, code, DEFAULT_SUCCESS_MSG);
    }

    /**
     * 构造方法，由 返回数据、成功返回结果枚举 构造一个成功返回结果的包装
     *
     * @param data        返回数据
     * @param successEnum 成功返回结果枚举
     */
    public SucceededResponseWrapper(T data, ISuccessEnumBase successEnum) {
        this(data, successEnum.getIntegratedCode(), successEnum.getDesc());
    }

    /** data属性 getter */
    public T getData() {
        return data;
    }
}
