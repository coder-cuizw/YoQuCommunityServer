package net.gupt.community.mapper;

import net.gupt.community.entity.Common;
import net.gupt.community.vo.CommonVo;
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
     * 删除帖子和相关数据
     *
     * @param articleType 帖子类型
     * @param id          帖子Id
     * @return int
     */
    int deleteArticleByIdAndType(Integer articleType, Integer id);


    /**
     * 设置置顶
     *
     * @param common 通用对象
     * @return int
     */
    int setTop(Common common);

    /**
     * 查询所有帖子的所对应所有信息
     * 搜索功能
     *
     * @param id            文章ID
     * @param isTop         是否置顶
     * @param isSearch      是否为搜索接口
     * @param searchContent 搜索类容
     * @param postType      传入类型
     * @param uid           学号
     * @return list<CommonVo></>
     */
    List<CommonVo> findAllCommonsWithVO(Byte postType, Integer uid, Integer id, Boolean isTop, Boolean isSearch, String searchContent);
}