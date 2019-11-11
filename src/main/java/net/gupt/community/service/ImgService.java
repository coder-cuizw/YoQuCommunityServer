package net.gupt.community.service;

import net.gupt.community.entity.Img;
import net.gupt.community.entity.Result;

import java.util.List;

/**
 * @author YG
 */
public interface ImgService {

    /**
     * 获取所有相关图片
     *
     * @param articleId   帖子Id
     * @param articleType 帖子类型
     * @return PageInfo<Img> 图片数据
     */
    List<Img> getImgs(Integer articleId, Byte articleType);


    /**
     * Description 将七牛的图片id存到数据库 <br/>
     *
     * @param img <br/>
     * @return int
     * @author YG <br/>
     * @date 2019/8/11 11:40<br/>
     */
    Result postImg(Img img);
}
