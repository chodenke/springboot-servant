package io.github.chodenke.springboot.servant.wrapper.response;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <h2>返回结果包装抽象基类</h2>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public abstract sealed class AbstractResponseWrapper permits FailedResponseWrapper, SucceededResponseWrapper {

    /** 是否成功，成功为 true */
    private final boolean success;

    /** 返回结果编码 */
    private final String code;

    /** 返回结果描述信息 */
    private final String msg;

    /**
     * 构造方法
     *
     * @param success 是否成功
     * @param code    返回结果编码
     * @param msg 返回结果描述信息
     */
    protected AbstractResponseWrapper(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 将当前 返回结果包装实例 转成一个 Map 实例并返回
     *
     * @return Map 实例
     */
    public Map<String, Object> toMap() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("success", this.success);
        map.put("code", this.code);
        map.put("msg", this.msg);
        return map;
    }

    /**
     * 重写的 toString() 方法
     *
     * @return 当前 AbstractResponseWrapper 实例的字符串描述
     */
    @Override
    public String toString() {
        return "AbstractResponseWrapper{" + "success=" + success + ", code='" + code + '\'' + ", msg='" + msg + '\'' + '}';
    }

    // ========================= getter begin =========================
    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    // ========================== getter end ==========================
}
