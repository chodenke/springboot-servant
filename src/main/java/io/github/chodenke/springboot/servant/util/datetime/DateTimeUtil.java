package io.github.chodenke.springboot.servant.util.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * <h2>日期时间工具类</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>暂无</li>
 * </ul>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
@SuppressWarnings("unused")
public class DateTimeUtil {

    /**
     * 由一个 LocalDateTime 实例 获取对应的 时间戳毫秒值
     * note: 使用的时区为 ZoneId.systemDefault() 返回的结果，即系统默认时区
     *
     * @param localDateTime LocalDateTime 实例
     * @return 时间戳毫秒值
     */
    @SuppressWarnings("unused")
    public static Long toEpochMilliWithSystemDefaultZoneId(LocalDateTime localDateTime) {
        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 由一个 时间戳毫秒值 获取对应的 LocalDateTime 实例
     * note: 使用的时区为 ZoneId.systemDefault() 返回的结果，即系统默认时区
     *
     * @param epochMilli 时间戳毫秒值
     * @return LocalDateTime 实例
     */
    @SuppressWarnings("unused")
    public static LocalDateTime ofEpochMilliWithSystemDefaultZoneId(Long epochMilli) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }
}
