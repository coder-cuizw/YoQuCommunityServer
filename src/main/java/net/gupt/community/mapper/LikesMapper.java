package net.gupt.community.mapper;

import net.gupt.community.entity.Likes;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LikesMapper {

    /**
     * 删除点赞
     * @param articleType
     * @param articleId 帖子Id
     * @return 执行结果
     */
    int deleteLikes( Integer articleId, Byte articleType,String openId,Byte state);

    /**
     * 发表点赞
     * @param likes
     */
    int insertLikes(Likes likes);

    /**
     * Description 获取点赞数和浏览量<br/>
     * @author  YG <br/>
     * @date   2019/9/20 16:00<br/>
     * @param articleId <br/>
     * @param articleType <br/>
     * @return
     */
   Likes findLikesWithView(Integer articleId, Byte articleType);


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


}