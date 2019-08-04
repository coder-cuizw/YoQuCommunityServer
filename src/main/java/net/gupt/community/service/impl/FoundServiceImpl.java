package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Found;
import net.gupt.community.mapper.FoundMapper;
import net.gupt.community.service.FoundService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public PageInfo<Found> getFounds(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(foundMapper.findAllFound());
    }
}
