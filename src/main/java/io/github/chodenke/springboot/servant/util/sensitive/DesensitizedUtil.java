package io.github.chodenke.springboot.servant.util.sensitive;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * <h2>脱敏工具类</h2>
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
public class DesensitizedUtil {

    private static final char DEFAULT_REPLACEMENT = '*';


    /**
     * 对字符串进行脱敏处理
     *
     * @param text         要脱敏的字符串
     * @param prefixRetain 前置保留原字符串内容的位数
     * @param suffixRetain 后置保留原字符串内容的位数
     * @return 脱敏后的字符串
     */
    public static String desensitize(String text, int prefixRetain, int suffixRetain) {
        Assert.hasLength(text, "text must have length");
        return desensitize(text, prefixRetain, suffixRetain, DEFAULT_REPLACEMENT);
    }

    /**
     * 对字符串进行脱敏处理
     *
     * @param text              要脱敏的字符串
     * @param prefixPersistChar 前置保留到哪个字符（从该字符后开始脱敏）
     * @param suffixRetain      后置保留原字符串内容的位数
     * @return 脱敏后的字符串
     */
    public static String desensitize(String text, PersistChar prefixPersistChar, int suffixRetain) {
        Assert.hasLength(text, "text must have length");
        return desensitize(text, prefixPersistChar.getIndex(text) + 1, suffixRetain, DEFAULT_REPLACEMENT);
    }

    /**
     * 对字符串进行脱敏处理
     *
     * @param text              要脱敏的字符串
     * @param prefixRetain      前置保留原字符串内容的位数
     * @param suffixPersistChar 后置从哪个字符开始保留（该字符后之前的字符会脱敏）
     * @return 脱敏后的字符串
     */
    public static String desensitize(String text, int prefixRetain, PersistChar suffixPersistChar) {
        Assert.hasLength(text, "text must have length");
        return desensitize(text, prefixRetain, text.length() - suffixPersistChar.getLastIndex(text), DEFAULT_REPLACEMENT);
    }

    /**
     * 对字符串进行脱敏处理
     *
     * @param text              要脱敏的字符串
     * @param prefixPersistChar 前置保留到哪个字符（从该字符后开始脱敏）
     * @param suffixPersistChar 后置从哪个字符开始保留（该字符后之前的字符会脱敏）
     * @return 脱敏后的字符串
     */
    public static String desensitize(String text, PersistChar prefixPersistChar, PersistChar suffixPersistChar) {
        Assert.hasLength(text, "text must have length");
        return desensitize(text, prefixPersistChar.getIndex(text) + 1, text.length() - suffixPersistChar.getLastIndex(text), DEFAULT_REPLACEMENT);
    }

    /**
     * 对字符串进行脱敏处理
     *
     * @param text         要脱敏的字符串
     * @param prefixRetain 前置保留原字符串内容的位数
     * @param suffixRetain 后置保留原字符串内容的位数
     * @param replacement  脱敏时用来替换原字符串中内容的字符
     * @return 脱敏后的字符串
     */
    public static String desensitize(String text, int prefixRetain, int suffixRetain, char replacement) {
        Assert.hasLength(text, "text must have length");
        Assert.hasLength(String.valueOf(replacement), "replacement must have length");
        if (prefixRetain < 0 || suffixRetain < 0 || prefixRetain + suffixRetain == 0) {
            throw new UnsupportedOperationException("meaningless operation, please check the values of prefixRetain and suffixRetain");
        }
        final char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i >= prefixRetain && i < text.length() - suffixRetain) {
                chars[i] = replacement;
            }
        }
        return String.valueOf(chars);
    }

    /**
     * 保留字符
     */
    public static class PersistChar {

        /**
         * 要保留在原位置的字符
         */
        private final char persistChar;

        /**
         * 私有化的构造方法
         */
        private PersistChar(char persistChar) {
            this.persistChar = persistChar;
        }

        /**
         * 由一个 字符 获取对应的 PersistChar 实例
         *
         * @param persistChar 保留字符
         * @return PersistChar 实例
         */
        public static PersistChar of(char persistChar) {
            return new PersistChar(persistChar);
        }

        /**
         * 获取 当前实例的保留字符在 参数字符串中首次出现的索引位置
         *
         * @param text 字符串
         * @return 首次出现的索引位置，不存在返回 -1
         */
        private int getIndex(String text) {
            if (!StringUtils.hasLength(text)) {
                return -1;
            }
            return text.indexOf(this.persistChar);
        }

        /**
         * 获取 当前实例的保留字符在 参数字符串中最后出现的索引位置
         *
         * @param text 字符串
         * @return 最后出现的索引位置，不存在返回 -1
         */
        private int getLastIndex(String text) {
            if (!StringUtils.hasLength(text)) {
                return -1;
            }
            return text.lastIndexOf(this.persistChar);
        }
    }
}
