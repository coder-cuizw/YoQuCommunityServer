package net.gupt.community.service;

import net.gupt.community.entity.News;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;

import java.util.List;

/**
 * ClassName  NewsService <br/>
 * Description 轮播图业务成 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/12/4 13:52<br/>
 * @since JDK 1.8
 */
public interface NewsService {

    /**
     * Description 获取轮播信息<br/>
     *
     * @return list
     * @author YG <br/>
     * @date 2019/12/4 13:55<br/>
     */
    List<News> getNews();


    /**
     * Description 添加轮播信息 <br/>
     *
     * @param news    <br/>
     * @param student <br/>
     * @return int
     * @author YG <br/>
     * @date 2019/12/4 13:58<br/>
     */
    Result insertNews(News news, Student student);
}
