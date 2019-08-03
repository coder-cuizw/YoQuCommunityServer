package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Common;
import net.gupt.community.mapper.CommonMapper;
import net.gupt.community.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <h3>gupt-community</h3>
 * <p>通用帖子业务层实现类</p>
 *
 * @author : Cui
 * @date : 2019-07-30 16:50
 **/
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public PageInfo<Common> getArticles(Integer postType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(commonMapper.findAllCommons(postType));
    }
}
