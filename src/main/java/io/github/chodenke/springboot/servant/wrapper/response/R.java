package io.github.chodenke.springboot.servant.wrapper.response;

/**
 * <h2>返回结果包装抽象基类</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>暂无</li>
 * </ul>
 * <p>datetime: 2024/10/8 10:17</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public abstract sealed class R<T> extends AbstractResponseWrapper<T> permits SucceededResponseWrapper, FailedResponseWrapper {

    /**
     * 构造方法
     *
     * @param success 是否成功
     * @param code    返回结果编码
     * @param msg 返回结果描述信息
     */
    public R(boolean success, String code, String msg) {
        super(success, code, msg);
    }

}
