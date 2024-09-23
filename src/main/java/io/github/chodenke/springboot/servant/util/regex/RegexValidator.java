package io.github.chodenke.springboot.servant.util.regex;

import java.util.regex.Pattern;

/**
 * <h2>正则表达式校验工具</h2>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */

public class RegexValidator {

    /** 单个中文汉字 */
    private final static Pattern SINGLE_CHINESE = Pattern.compile(RegexPool.SINGLE_CHINESE);

    /** 中文汉字 */
    private final static Pattern CHINESE = Pattern.compile(RegexPool.CHINESE);

    /** 大小写英文字母 */
    private final static Pattern ENGLISH_ALPHABET = Pattern.compile(RegexPool.ENGLISH_ALPHABET);

    /** 内地手机号，不包含 86 或 包含86 */
    private final static Pattern CHINESE_PHONE = Pattern.compile(RegexPool.CHINESE_PHONE);

    /** 电子邮箱地址 */
    private final static Pattern EMAIL = Pattern.compile(RegexPool.EMAIL);

    /** IP v4 */
    private final static Pattern IPV4 = Pattern.compile(RegexPool.IPV4);

    /** IP v6 */
    private final static Pattern IPV6 = Pattern.compile(RegexPool.IPV6);

    /** URL */
    private final static Pattern URL = Pattern.compile(RegexPool.URL);

    /** 中国公民身份证号 */
    private final static Pattern CHINESE_IDCARD_NO = Pattern.compile(RegexPool.CHINESE_IDCARD_NO);

    /** 邮编 */
    private final static Pattern CHINESE_ZIP_CODE = Pattern.compile(RegexPool.CHINESE_ZIP_CODE);

    /** 中国车牌号码（兼容新能源车牌） */
    private final static Pattern CHINESE_PLATE_NUMBER = Pattern.compile(RegexPool.CHINESE_PLATE_NUMBER);

    /** 统一社会信用代码 */
    private final static Pattern CHINESE_USC_CODE = Pattern.compile(RegexPool.CHINESE_USC_CODE);

    /** 中国行政区划代码 */
    private final static Pattern CHINESE_DISTRICT_CODE = Pattern.compile(RegexPool.CHINESE_DISTRICT_CODE);

    /** 银行卡号 */
    private final static Pattern CHINESE_BANK_ACCOUNT = Pattern.compile(RegexPool.CHINESE_BANK_ACCOUNT);

    /** 车架号 */
    private final static Pattern VEHICLE_VIN = Pattern.compile(RegexPool.VEHICLE_VIN);

    /** 中国民族 */
    private final static Pattern CHINESE_NATION = Pattern.compile(RegexPool.CHINESE_NATION);

    /** 正整数，不含0 */
    private final static Pattern POSITIVE = Pattern.compile(RegexPool.POSITIVE);

    /** 金额，允许如下数字 非负整数、非负1到2位小数 */
    private final static Pattern AMOUNT = Pattern.compile(RegexPool.AMOUNT);

    /** 常见图片文件后缀名 */
    private final static Pattern IMAGE_FILE_SUFFIX = Pattern.compile(RegexPool.IMAGE_FILE_SUFFIX);

    /** 安全的文本，文本中禁用下列符号 &lt;&gt;(){}%$;:&#39;&quot; */
    private final static Pattern SECURE_TEXT = Pattern.compile(RegexPool.SECURE_TEXT);

    /** MD5算法计算得到的32位hash值 */
    private final static Pattern MD5_HASH = Pattern.compile(RegexPool.MD5_HASH);

    /** 经过 bcrypt 加密的密码哈希值 */
    private final static Pattern BCRYPT_HASH = Pattern.compile(RegexPool.BCRYPT_HASH);

    /** 21位NanoId */
    private final static Pattern NANO_ID = Pattern.compile(RegexPool.NANO_ID);

    /** 6位数字验证码 */
    private final static Pattern DIGITAL_CAPTCHA = Pattern.compile(RegexPool.DIGITAL_CAPTCHA);

    /**
     * 验证是否为单个中文汉字
     *
     * @param value 值
     * @return 是否为单个中文汉字
     */
    public static boolean isSingleChinese(CharSequence value) {
        return isMatchRegex(SINGLE_CHINESE, value);
    }

    /**
     * 验证是否为一个及一个以上中文字符
     *
     * @param value 值
     * @return 是否为一个及一个以上中文字符
     */
    public static boolean isChinese(CharSequence value) {
        return isMatchRegex(CHINESE, value);
    }

    /**
     * 验证是否为一个及一个以上大小写英文字母
     *
     * @param value 值
     * @return 是否为一个及一个以上大小写英文字母
     */
    public static boolean isEnglishAlphabet(CharSequence value) {
        return isMatchRegex(ENGLISH_ALPHABET, value);
    }

    /**
     * 验证是否为内地手机号
     *
     * @param value 值
     * @return 是否为一个内地手机号
     */
    public static boolean isChinesePhone(CharSequence value) {
        return isMatchRegex(CHINESE_PHONE, value);
    }

    /**
     * 验证是否为电子邮箱地址
     *
     * @param value 值
     * @return 是否为电子邮箱地址
     */
    public static boolean isEmail(CharSequence value) {
        return isMatchRegex(EMAIL, value);
    }

    /**
     * 验证是否为IP v4
     *
     * @param value 值
     * @return 是否为IP v4
     */
    public static boolean isIPv4(CharSequence value) {
        return isMatchRegex(IPV4, value);
    }

    /**
     * 验证是否为IP v6
     *
     * @param value 值
     * @return 是否为IP v6
     */
    public static boolean isIPv6(CharSequence value) {
        return isMatchRegex(IPV6, value);
    }

    /**
     * 验证是否为URL
     *
     * @param value 值
     * @return 是否为URL
     */
    public static boolean isUrl(CharSequence value) {
        return isMatchRegex(URL, value);
    }

    /**
     * 验证是否为中国公民身份证号
     *
     * @param value 值
     * @return 是否为中国公民身份证号
     */
    public static boolean isChineseIdcardNo(CharSequence value) {
        return isMatchRegex(CHINESE_IDCARD_NO, value);
    }

    /**
     * 验证是否为邮编
     *
     * @param value 值
     * @return 是否为邮编
     */
    public static boolean isChineseZipCode(CharSequence value) {
        return isMatchRegex(CHINESE_ZIP_CODE, value);
    }

    /**
     * 验证是否为中国车牌号码
     *
     * @param value 值
     * @return 是否为中国车牌号码
     */
    public static boolean isChinesePlateNumber(CharSequence value) {
        return isMatchRegex(CHINESE_PLATE_NUMBER, value);
    }

    /**
     * 验证是否为统一社会信用代码
     *
     * @param value 值
     * @return 是否为统一社会信用代码
     */
    public static boolean isChineseUscCode(CharSequence value) {
        return isMatchRegex(CHINESE_USC_CODE, value);
    }

    /**
     * 验证是否为中国行政区划代码
     *
     * @param value 值
     * @return 是否为中国行政区划代码
     */
    public static boolean isChineseDistrictCode(CharSequence value) {
        return isMatchRegex(CHINESE_DISTRICT_CODE, value);
    }

    /**
     * 验证是否为银行卡号
     *
     * @param value 值
     * @return 是否为银行卡号
     */
    public static boolean isChineseBankAccount(CharSequence value) {
        return isMatchRegex(CHINESE_BANK_ACCOUNT, value);
    }

    /**
     * 验证是否为车架号
     *
     * @param value 值
     * @return 是否为车架号
     */
    public static boolean isVehicleVin(CharSequence value) {
        return isMatchRegex(VEHICLE_VIN, value);
    }

    /**
     * 验证是否为中国民族
     *
     * @param value 值
     * @return 是否为中国民族
     */
    public static boolean isChineseNation(CharSequence value) {
        return isMatchRegex(CHINESE_NATION, value);
    }

    /**
     * 验证是否为正整数(不含0)
     *
     * @param value 值
     * @return 是否为正整数
     */
    public static boolean isPositive(CharSequence value) {
        return isMatchRegex(POSITIVE, value);
    }

    /**
     * 验证是否为金额(非负整数、非负1到2位小数)
     *
     * @param value 值
     * @return 是否为金额
     */
    public static boolean isAmount(CharSequence value) {
        return isMatchRegex(AMOUNT, value);
    }

    /**
     * 验证是否为常见图片文件后缀名
     *
     * @param value 值
     * @return 是否为常见图片文件后缀名
     */
    public static boolean isImageFileSuffix(CharSequence value) {
        return isMatchRegex(IMAGE_FILE_SUFFIX, value);
    }

    /**
     * 验证是否为安全的文本(文本中禁用下列符号 &lt;&gt;(){}%$;:&#39;&quot;)
     *
     * @param value 值
     * @return 是否为安全的文本
     */
    public static boolean isSecureText(CharSequence value) {
        return isMatchRegex(SECURE_TEXT, value);
    }

    /**
     * 验证是否为MD5算法计算得到的32位hash值
     *
     * @param value 值
     * @return 是否为MD5算法计算得到的32位hash值
     */
    public static boolean isMd5Hash(CharSequence value) {
        return isMatchRegex(MD5_HASH, value);
    }

    /**
     * 验证是否为经过 bcrypt 加密的密码哈希值
     *
     * @param value 值
     * @return 是否为经过 bcrypt 加密的密码哈希值
     */
    public static boolean isBcryptHash(CharSequence value) {
        return isMatchRegex(BCRYPT_HASH, value);
    }

    /**
     * 验证是否为21位NanoId
     *
     * @param value 值
     * @return 是否为21位NanoId
     */
    public static boolean isNanoId(CharSequence value) {
        return isMatchRegex(NANO_ID, value);
    }

    /**
     * 验证是否为6位数字验证码
     *
     * @param value 值
     * @return 是否为6位数字验证码
     */
    public static boolean isDigitalCaptcha(CharSequence value) {
        return isMatchRegex(DIGITAL_CAPTCHA, value);
    }

    /**
     * 通过正则表达式验证
     *
     * @param pattern 正则模式
     * @param value   值
     * @return 是否匹配正则
     */
    private static boolean isMatchRegex(Pattern pattern, CharSequence value) {
        if (value == null || pattern == null) {
            // 提供null的字符串为不匹配
            return false;
        }
        return pattern.matcher(value).matches();
    }
}
