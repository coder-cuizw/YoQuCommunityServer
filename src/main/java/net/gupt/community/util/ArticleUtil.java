package net.gupt.community.util;

import net.gupt.community.service.CommonService;
import net.gupt.community.service.FoundService;
import org.springframework.stereotype.Controller;


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
    public static boolean isExist(Integer articleId, byte articleType, CommonService commonService, FoundService foundService) {
        final byte commonArticle = 0;
        final byte treeHoleArticle = 1;
        final byte foundArticle = 2;
        if (articleType == commonArticle || articleType == treeHoleArticle) {
            int result = commonService.findCommonArticleById(articleId);
            return result > 0;
        }
        if (articleType == foundArticle) {
            int result = foundService.findFoundArticleById(articleId);
            return result > 0;
        }
        return false;
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
