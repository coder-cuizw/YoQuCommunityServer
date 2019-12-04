package net.gupt.community.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.News;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.service.NewsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName  NewsController <br/>
 * Description 轮播图控制层 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/12/4 14:02<br/>
 * @since JDK 1.8
 */
@Slf4j
@AuthToken
@Api(value = "轮播图", protocols = "https", tags = "轮播图接口")
@RestController
@RequestMapping(value = "/news", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class NewsController {
    private final NewsService newsService;
    private Student student;


    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/getNews")
    public Result getNews() {
        List<News> newsList = newsService.getNews();
        return newsList.size() > 0 ? Result.success(CodeMsg.SUCCESS, newsList) : Result.error(CodeMsg.FAILED);
    }

    @PostMapping("/postNews")
    public Result postNews(@RequestBody List<News> news, HttpServletRequest request) {
        student = (Student) request.getAttribute("stu");
        return newsService.insertNews(news, student);
    }
}
