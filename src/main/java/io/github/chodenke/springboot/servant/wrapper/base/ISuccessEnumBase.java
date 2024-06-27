package io.github.chodenke.springboot.servant.wrapper.base;

/**
 * <h2>成功返回结果枚举的基础接口</h2>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public interface ISuccessEnumBase extends IResultEnumBase {

    /**
     * 获取完整的成功返回结果编码
     * note: 这里进行了默认实现，构成为 "SUC:" + 模块名称 + ":返回结果枚举名称"
     *
     * @return 完整的成功返回结果编码
     */
    default String getIntegratedCode() {
        return "SUC" + ":" + getResponseSegmentCode() + ":" + name();
    }
}
