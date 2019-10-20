package net.gupt.community.mapper;

import net.gupt.community.entity.Img;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description ImgMapper<br/>
 *
 * @author YG  <br/>
 * @date 2019/10/20 13:47<br/>
 */
@Component
public interface ImgMapper {


    /**
     * Description 插入图片ID<br/>
     *
     * @param record 图片对象 <br/>
     * @return int
     * @author YG <br/>
     * @date 2019/10/8 16:24<br/>
     */
    int insert(Img record);


    /**
     * Description通过文章ID和文章类型查询图片ID <br/>
     *
     * @param articleId   文章ID <br/>
     * @param articleType 文章类型 <br/>
     * @return List<Img>
     * @author YG <br/>
     * @date 2019/10/8 16:22<br/>
     */
    List<Img> findImgsByArticleId(Integer articleId, Byte articleType);

}