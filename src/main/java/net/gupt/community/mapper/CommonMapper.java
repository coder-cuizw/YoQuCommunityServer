package net.gupt.community.mapper;

import net.gupt.community.entity.Common;
import net.gupt.community.entity.Img;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Cui
 */
@Component
public interface CommonMapper {

    /**
     * 插入帖子信息
     *
     * @param record 收录帖子数据
     * @return 执行结果
     */
    int insert(Common record);

    /**
     * 获取所有的通用帖子
     *
     * @param postType 传入类型
     * @param uid 学号
     * @return 通用帖子列表
     */
    List<Common> findAllCommons(Byte postType, Integer uid,Integer id,Boolean isTop);

    /**
     * 删除帖子和相关数据
     *
     * @param articleType 帖子类型
     * @param id 帖子Id
     * @return
     */
    int deleteArticleByIdAndType(Integer articleType, Integer id);

    /**
     * 插入图片
     * @param record
     * @return
     */
    int insertImg(Img record);

    /**
     * 设置置顶
     * @param common
     * @return
     */
    int setTop(Common common);

}