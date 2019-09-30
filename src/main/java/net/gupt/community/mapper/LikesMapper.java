package net.gupt.community.mapper;

import net.gupt.community.entity.Likes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LikesMapper {

    /**
     * 删除点赞
     *
     * @param articleType
     * @param articleId   帖子Id
     * @return 执行结果
     */
    int deleteLikes(Integer articleId, Byte articleType, Integer uid);

    /**
     * 发表点赞
     *
     * @param likes
     */
    int insertLikes(Likes likes);

    /**
     * Description 获取点赞数和浏览量<br/>
     *
     * @param articleId   <br/>
     * @param articleType <br/>
     * @return
     * @author YG <br/>
     * @date 2019/9/20 16:00<br/>
     */
    Likes findLikes(Integer articleId, Byte articleType);

    /**
     * 检验是否有点赞记录
     *
     * @param articleId
     * @param articleType
     * @param uid
     * @return
     */

    Likes findIsLikes(Integer articleId, Byte articleType, Integer uid);

    /**
     * 检验是否有浏览记录
     *
     * @param articleId
     * @param articleType
     * @param uid
     * @param info
     * @return
     */
    Likes findIsViews(Integer articleId, Byte articleType, Integer uid,String info);


    /**
     * 通过articleId获得点赞列表
     *
     * @param articleId 帖子Id
     * @return 点赞表
     */
    List<Likes> findLikesByArticleId(Integer articleId);

    int insertSelective(Likes record);

    Likes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);


    Likes findView(Integer articleId, Byte articleType);


}