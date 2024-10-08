package io.github.chodenke.springboot.servant.wrapper.response;

import io.github.chodenke.springboot.servant.wrapper.base.ISuccessEnumBase;

/**
 * <h2>成功返回结果包装</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>该类继承 SucceededResponseWrapper 类，仅仅是用了短名字方便使用，没有添加功能</li>
 * </ul>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public final class SR<T> extends SucceededResponseWrapper<T> {

    /**
     * 构造方法，由 返回数据、成功返回结果编码 和 成功返回结果描述信息 构造一个成功返回结果的包装
     *
     * @param data 返回数据
     * @param code 成功返回结果编码
     * @param msg  成功返回结果描述信息
     */
    private SR(T data, String code, String msg) {
        super(data, code, msg);
    }

    /**
     * 构造方法，由 返回数据、成功返回结果编码 和 成功返回结果描述信息 构造一个成功返回结果的包装
     *
     * @param data 返回数据
     * @param code 成功返回结果编码
     * @param msg  成功返回结果描述信息
     */
    public static <T> SR<T> ok(T data, String code, String msg) {
        return new SR<>(data, code, msg);
    }

    /**
     * 构造方法，由 返回数据 和 成功返回结果编码 构造一个成功返回结果的包装，使用默认的成功返回结果描述信息
     *
     * @param data 返回数据
     * @param code 成功返回结果编码
     */
    public static <T> SR<T> ok(T data, String code) {
        return new SR<>(data, code, DEFAULT_SUCCESS_MSG);
    }

    /**
     * 构造方法，由 返回数据、成功返回结果枚举 构造一个成功返回结果的包装
     *
     * @param data        返回数据
     * @param successEnum 成功返回结果枚举
     */
    public static <T> SR<T> ok(T data, ISuccessEnumBase successEnum) {
        return new SR<>(data, successEnum.getIntegratedCode(), successEnum.getDesc());
    }

}
