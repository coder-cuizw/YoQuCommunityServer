package net.gupt.community.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * ClassName  UrlUtil <br/>
 * Description  Url工具类<br/>
 *
 * @author Administrator
 * @version 1.0
 * @date 2019/10/1313:22<br/>
 * @since JDK 1.8
 */
@Slf4j
public class UrlUtil {
    public static String deCodeWithRegexp(String str, String charset, String expression) {
        StringBuilder builder = new StringBuilder();
        if (!str.trim().isEmpty()) {
            try {
                String content = URLDecoder.decode(str, charset);
                char[] chars = content.toCharArray();

                for (char character : chars
                ) {
                    builder.append(character).append(expression);
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return builder.substring(0, builder.lastIndexOf(expression));
    }
}
