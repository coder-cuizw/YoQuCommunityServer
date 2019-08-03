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

    PageInfo<Found> getFounds(Integer pageNum, Integer pageSize);

}
