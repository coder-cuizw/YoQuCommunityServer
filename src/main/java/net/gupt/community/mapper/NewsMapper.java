package net.gupt.community.mapper;

import net.gupt.community.entity.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName  NewsMapper <br/>
 * Description 轮播图接口 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/12/4 13:36<br/>
 * @since JDK 1.8
 */

@Mapper
@Component
public interface NewsMapper {

    /**
     * Description 查询所有图片信息<br/>
     *
     * @return java.util.List<net.gupt.community.entity.News>
     * @author YG <br/>
     * @date 2019/12/4 13:42<br/>
     */
    @Select("SELECT * FROM tbl_news ORDER BY id DESC LIMIT 3")
    @Results({
            @Result(column = "cover_img", property = "coverImg")
    })
    List<News> findNews();


    /**
     * Description 添加轮播图信息 <br/>
     *
     * @param news <br/>
     * @return int
     * @author YG <br/>
     * @date 2019/12/4 13:43<br/>
     */
    @Insert({"<script>", "INSERT INTO tbl_news (title, cover_img, url) VALUES",
            "<foreach collection='news' item='item' index='index' separator=','>",
            "(#{item.title}, #{item.coverImg}, #{item.url})",
            "</foreach>",
            "</script>"
    })
    int insertNews(@Param(value = "news") List<News> news);
}
