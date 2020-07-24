package net.gupt.community.service.impl;

import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.News;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.mapper.NewsMapper;
import net.gupt.community.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName  NewsServiceImpl <br/>
 * Description 轮播图业务层实现类 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/12/4 13:59<br/>
 * @since JDK 1.8
 */
@Service
public class NewsServiceImpl implements NewsService {
    private final NewsMapper newsMapper;

    public NewsServiceImpl(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }

    @Override
    public List<News> getNews() {
        return newsMapper.findNews();
    }

    @Override
    public Result<?> insertNews(News news, Student student) {
        Boolean permission = student.getPermission();
        if (permission) {
            int rows = newsMapper.insertNews(news);
            return rows > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.POST_FAILED);
        }
        return Result.error(CodeMsg.POST_FAILED);
    }
}
