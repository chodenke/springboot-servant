package io.github.chodenke.springboot.servant.util.regex;

/**
 * <h2>正则表达式规范池</h2>
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
public final class RegexPool {

    /** 单个中文汉字   note: 参照(<a href="https://zh.wikipedia.org/wiki/%E6%B1%89%E5%AD%97">维基百科汉字Unicode范围</a> 页面右侧) */
    public static final String SINGLE_CHINESE = "[\u2E80-\u2EFF\u2F00-\u2FDF\u31C0-\u31EF\u3400-\u4DBF\u4E00-\u9FFF\uF900-\uFAFF\uD840\uDC00-\uD869\uDEDF\uD869\uDF00-\uD86D\uDF3F\uD86D\uDF40-\uD86E\uDC1F\uD86E\uDC20-\uD873\uDEAF\uD87E\uDC00-\uD87E\uDE1F]";

    /** 中文汉字，一个及一个以上中文字符 */
    public static final String CHINESE = SINGLE_CHINESE + "+";

    /** 大小写英文字母 */
    public static final String ENGLISH_ALPHABET = "[a-zA-Z]+";

    /** 内地手机号，不包含 86 或 包含86 */
    public static final String CHINESE_PHONE = "^(86)*1[0-9]{10}$";

    /** 电子邮箱地址 */
    public static final String EMAIL = "[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+";

    /** IP v4 */
    public static final String IPV4 = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)$";

    /** IP v6 */
    public static final String IPV6 = "(([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]+|::(ffff(:0{1,4})?:)?((25[0-5]|(2[0-4]|1?[0-9])?[0-9])\\.){3}(25[0-5]|(2[0-4]|1?[0-9])?[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1?[0-9])?[0-9])\\.){3}(25[0-5]|(2[0-4]|1?[0-9])?[0-9]))";

    /** URL */
    public static final String URL = "(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\&%\\+\\$#_=]*)?";

    /** 中国公民身份证号 */
    public static final String CHINESE_IDCARD_NO = "[1-9]\\d{5}[1-2]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}(\\d|X|x)";

    /** 邮编 */
    public static final String CHINESE_ZIP_CODE = "^(0[1-7]|1[0-356]|2[0-7]|3[0-6]|4[0-7]|5[0-7]|6[0-7]|7[0-5]|8[0-9]|9[0-8])\\d{4}|99907[78]$";

    /** 中国车牌号码（兼容新能源车牌） */
    public static final String CHINESE_PLATE_NUMBER =
            //https://gitee.com/dromara/hutool/issues/I1B77H?from=project-issue
            "^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z](([0-9]{5}[ABCDEFGHJK])|([ABCDEFGHJK]([A-HJ-NP-Z0-9])[0-9]{4})))|" +
                    //https://gitee.com/dromara/hutool/issues/I1BJHE?from=project-issue
                    "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]\\d{3}\\d{1,3}[领])|" +
                    "([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z][A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳使领]))$";

    /**
     * 统一社会信用代码，同时支持18位和15位社会信用代码
     * note: 由18位数字或者大写字母组成，但是字母不包括 I、O、Z、S、V
     * 目前还有老的工商注册代码，也就是15位的社会信用代码
     */
    public static final String CHINESE_USC_CODE = "^([0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}|[1-9]\\d{14})$";

    /** 中国行政区划代码 */
    public static final String CHINESE_DISTRICT_CODE = "^[0-9]{6}$";

    /** 银行卡号（10到30位数字, 覆盖对公/私账户, 参考微信支付https://pay.weixin.qq.com/wiki/doc/api/xiaowei.php?chapter=22_1） */
    public static final String CHINESE_BANK_ACCOUNT = "^[1-9]\\d{9,29}$";

    /** 车架号 */
    public static final String VEHICLE_VIN = "^[A-HJ-NPR-Z0-9]{8}[X0-9]([A-HJ-NPR-Z0-9]{3}\\d{5}|[A-HJ-NPR-Z0-9]{5}\\d{3})$";

    /** 中国民族 */
    public static final String CHINESE_NATION = "^[(汉)|(蒙古)|(回)|(藏)|(维吾尔)|(苗)|(彝)|(壮)|(布依)|(朝鲜)|(满)|(侗)|(瑶)|(白)|(土家)|(哈尼)|(哈萨克)|(傣)|(黎)|(傈僳)|(佤)|(畲)|(高山)|(拉祜)|(水)|(东乡)|(纳西)|(景颇)|(柯尔克孜)|(土)|(达斡尔)|(仫佬)|(羌)|(布朗)|(撒拉)|(毛南)|(仡佬)|(锡伯)|(阿昌)|(普米)|(塔吉克)|(怒)|(乌孜别克)|(俄罗斯)|(鄂温克)|(德昂)|(保安)|(裕固)|(京)|(塔塔尔)|(独龙)|(鄂伦春)|(赫哲)|(门巴)|(珞巴)|(基诺)]族$";

    /** 正整数，不含0 */
    public static final String POSITIVE = "^([1-9][0-9]*)$";

    /** 金额，允许如下数字 非负整数、非负1到2位小数 */
    public static final String AMOUNT = "^(([1-9]{1}\\d*)|(0{1}))(\\.\\d{1,2})?$";

    /** 常见图片文件后缀名 */
    public static final String IMAGE_FILE_SUFFIX = "(jpeg)|(jpg)|(png)|(gif)";

    /** 安全的文本，文本中禁用下列符号 &lt;&gt;(){}%$;:&#39;&quot; */
    public static final String SECURE_TEXT = "^[^<>(){}%$;:'\"]*$";

    /** MD5算法计算得到的32位hash值 */
    public static final String MD5_HASH = "[0-9a-f]{32}";

    /** 21位NanoId */
    public static final String NANO_ID = "[0-9a-zA-Z_-]{21}";

    /** 6位数字验证码 */
    public static final String DIGITAL_CAPTCHA = "^[0-9]{6}$";

    /** 将构造方法私有化了，禁止实例化该类 */
    private RegexPool() {
    }

}
