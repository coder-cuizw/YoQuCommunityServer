package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Found;
import net.gupt.community.mapper.FoundMapper;
import net.gupt.community.service.FoundService;
import org.springframework.stereotype.Service;

/**
 * <h3>gupt-community</h3>
 * <p>失物找回接口实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:03
 **/
@Service
public class FoundServiceImpl implements FoundService {

    private final FoundMapper foundMapper;

    public FoundServiceImpl(FoundMapper foundMapper) {
        this.foundMapper = foundMapper;
    }

    /**
     * 查询所有失物信息
     * @param pageNum  <br/>
     * @param pageSize  <br/>
     * @return
     */
    @Override
    public PageInfo<Found> getFounds(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(foundMapper.findAllFound());
    }

    /**
     * 发送失物接口
     * @param  found <br/>
     * @return int
     */
    @Override
    public int postFound(Found found) {
       return  foundMapper.insertSelective(found);
    }

    /**
     * 更新失物状态接口
     * @param found found对象
     * @return int
     */
    @Override
    public int updateFoundStatus(Found found) {
        return  foundMapper.updateFoundStatusById(found);
    }

}
