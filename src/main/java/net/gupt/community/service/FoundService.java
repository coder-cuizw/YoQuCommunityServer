package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Found;

/**
 * <h3>gupt-community</h3>
 * <p>失物找回业务接口</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:02
 **/
public interface FoundService {
    /**
     * 获取所有的失物信息
     *
     * @param pageNum      <br/>
     * @param pageSize     <br/>
     * @param isTop        <br/>
     * @param id           <br/>
     * @param articleState <br/>
     * @param uid          <br/>
     * @return PageInfo
     */
    PageInfo<Found> getFounds(Integer pageNum, Integer pageSize, Integer id, Boolean articleState, Boolean isTop, Integer uid);

    /**
     * Description 发表失物接口 <br/>
     *
     * @param found <br/>
     * @return int
     * @author YG <br/>
     * @date 2019/8/5 8:54<br/>
     */
    int postFound(Found found);

    /**
     * 更新失物状态
     *
     * @param found 失物对象
     * @return int
     */
    int updateFoundStatus(Found found);

    /**
     * 删除有关失物的所有信息
     *
     * @param id <br/>
     * @return int
     */
    int deleteFoundInfo(Integer id);

}
