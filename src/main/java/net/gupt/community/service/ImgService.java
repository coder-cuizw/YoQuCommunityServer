package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Img;

import java.util.List;

public interface ImgService {

    PageInfo<Img> getImgs(Integer articleId, Integer pageNum, Integer pageSize);

}
