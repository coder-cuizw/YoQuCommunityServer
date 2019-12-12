package net.gupt.community.util;

import net.gupt.community.mapper.CommonMapper;
import net.gupt.community.mapper.FoundMapper;
import org.springframework.stereotype.Controller;

import java.util.Map;


/**
 * ClassName  ArticleController <br/>
 * Description 文章控制类 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/10/30 21:20<br/>
 * @since JDK 1.8
 */
@Controller
public class ArticleUtil {

    /**
     * 判断文章是否存在
     *
     * @param articleId   文章ID
     * @param articleType 文章类型
     * @return boolean
     */
    public static boolean isExist(Integer articleId, byte articleType, Map<String, Object> map) {

        final byte foundArticle = 2;
        if (articleType != foundArticle) {
            CommonMapper commonMapper = (CommonMapper) map.get("commonMapper");
            int result = commonMapper.findCommonArticleById(articleId);
            return result > 0;
        }
        FoundMapper foundMapper = (FoundMapper) map.get("foundMapper");
        int result = foundMapper.findFoundArticleById(articleId);
        return result > 0;
    }

    /**
     * 转义搜索的正则的表达式
     *
     * @param str 原字符串
     * @return String
     */
    public static String replaceStr(String str) {
        String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
        String replaceKey = str;
        for (String key : fbsArr
        ) {
            if (str.contains(key)) {
                replaceKey = key.replace(key, "\\" + key);
            }
        }
        return replaceKey;
    }

}
