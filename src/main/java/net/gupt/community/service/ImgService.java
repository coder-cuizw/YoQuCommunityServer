package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Img;

public interface ImgService {

    /**
     * 获取所有相关图片
     *
     * @param articleId   帖子Id
     * @param articleType 帖子类型
     * @param pageNum     页面数
     * @param pageSize    每页数量
     * @return PageInfo<Img> 图片数据
     */
    PageInfo<Img> getImgs(Integer articleId, Byte articleType, Integer pageNum, Integer pageSize);


    /**
     * Description 将七牛的图片id存到数据库 <br/>
     *
     * @param img <br/>
     * @return int
     * @author YG <br/>
     * @date 2019/8/11 11:40<br/>
     */
    int postImg(Img img);
}
