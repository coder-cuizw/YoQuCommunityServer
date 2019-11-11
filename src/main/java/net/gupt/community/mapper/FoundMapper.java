package net.gupt.community.mapper;

import net.gupt.community.entity.Found;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Description 失物找回Mapper <br/>
 *
 * @author YG <br/>
 * @date 2019/10/8 16:41<br/>
 */
@Component
public interface FoundMapper {
    /**
     * 通过主键删除所有有关失物的信息
     *
     * @param id  <br/>
     * @param uid 学号 <br/>
     * @return int
     */
    int deleteByPrimaryKey(Integer id, Integer uid);

    /**
     * 发表文章，检验空值
     *
     * @param record <br/>
     * @return int
     */
    int insertSelective(Found record);

    /**
     * 动态查询所有
     *
     * @param id            <br/>
     * @param articleState  <br/>
     * @param isTop         <br/>
     * @param searchContent <br/>
     * @param isSearch      <br/>
     * @param uid           <br/>
     * @return List
     */
    List<Found> findAllFound(Integer id, Boolean articleState, Boolean isTop, Integer uid, String searchContent, Boolean isSearch);

    /**
     * 更新失物状态
     *
     * @param record <br/>
     * @return int
     */
    int updateFoundStatusById(Found record);


    /**
     * 通过ID统计失物
     *
     * @param articleId 文章id
     * @return int
     */
    int findFoundArticleById(Integer articleId);
}