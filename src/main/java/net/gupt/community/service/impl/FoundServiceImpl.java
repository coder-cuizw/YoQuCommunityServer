package net.gupt.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.gupt.community.entity.Found;
import net.gupt.community.mapper.FoundMapper;
import net.gupt.community.service.FoundService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


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
     *
     * @param pageNum  <br/>
     * @param pageSize <br/>
     * @param isSearch <br/>
     * @return PageInfo<Found>
     */
    @Override
    public PageInfo<Found> getFounds(Integer pageNum, Integer pageSize, Integer id, Boolean articleState, Boolean isTop, Integer uid, Boolean isSearch, String searchContent) {
        PageHelper.startPage(pageNum, pageSize);
        if (searchContent != null) {
            String content;
            try {
                content = URLDecoder.decode(searchContent, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            return new PageInfo<>(foundMapper.findAllFound(id, articleState, isTop, uid, content, isSearch));
        }
        return new PageInfo<>(foundMapper.findAllFound(id, articleState, isTop, uid, null, isSearch));

    }

    /**
     * 发送失物接口
     *
     * @param found <br/>
     * @return int
     */
    @Override
    public int postFound(Found found) {
        return foundMapper.insertSelective(found);
    }

    /**
     * 更新失物状态接口
     *
     * @param found found对象
     * @return int
     */
    @Override
    public int updateFoundStatus(Found found) {
        return foundMapper.updateFoundStatusById(found);
    }

    /**
     * 删除失物有关的所有信息
     *
     * @param id <br/>
     * @return int
     */
    @Override
    public int deleteFoundInfo(Integer id) {
        return foundMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int findFoundArticleById(Integer articleId) {
        return foundMapper.findFoundArticleById(articleId);
    }


}
