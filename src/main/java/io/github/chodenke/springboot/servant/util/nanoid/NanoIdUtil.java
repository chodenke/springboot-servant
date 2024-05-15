package io.github.chodenke.springboot.servant.util.nanoid;

import java.security.SecureRandom;

/**
 * <h2>NanoId 生成工具类</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>该类简化自根据MIT许可发布的com.aventrix.jnanoid:jnanoid库，原作者David Klebanoff。(<a href="https://github.com/aventrix/jnanoid">链接</a>)</li>
 * </ul>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author codeke
 * @since JDK 17
 */
public final class NanoIdUtil {

    /** SecureRandom 实例 */
    private static final SecureRandom RANDOM = new SecureRandom();

    /** 可选字符 （字符数量必须在1到255之间）*/
    private static final char[] ALPHABETS =
            {'_', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /** 长度 */
    private static final int SIZE = 21;

    /** 私有化的构造方法 */
    private NanoIdUtil() {}

    /** 生成随机NanoId的方法 */
    public static String randomNanoId() {
        final int mask = (2 << (int) Math.floor(Math.log(ALPHABETS.length - 1) / Math.log(2))) - 1;
        final int step = (int) Math.ceil(1.6 * mask * SIZE / ALPHABETS.length);
        final StringBuilder idBuilder = new StringBuilder();
        while (true) {
            final byte[] bytes = new byte[step];
            RANDOM.nextBytes(bytes);
            for (int i = 0; i < step; i++) {
                final int alphabetIndex = bytes[i] & mask;
                if (alphabetIndex < ALPHABETS.length) {
                    idBuilder.append(ALPHABETS[alphabetIndex]);
                    if (idBuilder.length() == SIZE) {
                        return idBuilder.toString();
                    }
                }
            }
        }
    }
}
