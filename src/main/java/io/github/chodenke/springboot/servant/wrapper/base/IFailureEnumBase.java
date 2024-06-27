package io.github.chodenke.springboot.servant.wrapper.base;

import io.github.chodenke.springboot.servant.wrapper.exception.BizException;

/**
 * <h2>失败返回结果枚举的基础接口</h2>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public interface IFailureEnumBase extends IResultEnumBase {

    /**
     * 获取完整的失败返回结果编码
     * note: 这里进行了默认实现，构成为 "SUC:" + 异常分段名称 + ":返回结果枚举名称"
     *
     * @return 完整的成功返回结果编码
     */
    default String getIntegratedCode() {
        return "ERR" + ":" + getExSegmentCode() + ":" + name();
    }

    /**
     * 由当前 IFailureEnumBase 接口实现类的实例 构造一个 业务异常并返回
     *
     * @return 由当前 IFailureEnumBase 接口实现类的实例 构造的 业务异常
     */
    default BizException toEx() {
        return new BizException(this);
    }

}
