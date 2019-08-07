package net.gupt.community.service;

import com.github.pagehelper.PageInfo;
import net.gupt.community.vo.FoundQueryVo;
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
     * @param pageNum  <br/>
     * @param pageSize  <br/>
     * @param query 查询条件的对象<br/>
     * @return PageInfo
     */
    PageInfo<Found> getFounds(Integer pageNum, Integer pageSize, FoundQueryVo query);

    /**
     * Description 发表失物接口 <br/>
     * @author  YG <br/>
     * @date   2019/8/5 8:54<br/>
     * @param  found <br/>
     * @return int
     */
    int postFound(Found found);

    /**
     * 更新失物状态
     * @param found
     * @return int
     */
    int updateFoundStatus(Found found);
}
