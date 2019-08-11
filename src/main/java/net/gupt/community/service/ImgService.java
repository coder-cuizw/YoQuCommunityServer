package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Img;

import java.util.List;

public interface ImgService {

    PageInfo<Img> getImgs(Integer articleId, Integer pageNum, Integer pageSize);

    /**
     * Description 将七牛的图片id存到数据库 <br/>
     *
     * @param  img <br/>
     * @return
     * @author YG <br/>
     * @date 2019/8/11 11:40<br/>
     * @return int
     */
    int postImg(Img img);
}
