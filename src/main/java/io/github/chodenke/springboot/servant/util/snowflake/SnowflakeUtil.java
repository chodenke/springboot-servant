/*
 * Copyright @ 2024 甘肃诚诚网络技术有限公司 All rights reserved.
 * 甘肃诚诚网络技术有限公司 专有/保密源代码，未经许可禁止任何人通过任何渠道使用、修改源代码。
 */
package io.github.chodenke.springboot.servant.util.snowflake;

/**
 * <h2>雪花算法 工具类</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>暂无</li>
 * </ul>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author codeke
 * @since JDK 17
 */
public class SnowflakeUtil {

    /** 雪花生成器实例 */
    private static Snowflake snowflake;

    /** 初始化 */
    public static void init(long datacenterId, long workerId) {
        synchronized (SnowflakeUtil.class) {
            if (SnowflakeUtil.snowflake == null) {
                synchronized (SnowflakeUtil.class) {
                    SnowflakeUtil.snowflake = new Snowflake(datacenterId, workerId);
                }
            } else {
                throw new UnsupportedOperationException("Snowflake has already been initialized, there is no need to initialize again");
            }
        }
    }

    /** 校验 snowflake 是否被初始化了，没有初始化抛异常 */
    private static void checkInitialized() {
        if (SnowflakeUtil.snowflake == null) {
            throw new UnsupportedOperationException("Snowflake has not been initialized yet");
        }
    }

    /** 生成一个唯一的递增id */
    public static synchronized long nextId() {
        checkInitialized();
        return snowflake.nextId();
    }

    /** get snowflake's epoch */
    public static long getEpoch() {
        checkInitialized();
        return snowflake.getEpoch();
    }

    /** 从给定的id中提取时间戳、datacenterId、workerId和序列号信息 */
    public static long[] parseId(long id) {
        checkInitialized();
        return snowflake.parseId(id);
    }

    /** 以人性化格式从给定的id中提取并显示时间戳、数据中心id、工人id和序列号信息 */
    public static String formatId(long id) {
        checkInitialized();
        return snowflake.formatId(id);
    }

}
