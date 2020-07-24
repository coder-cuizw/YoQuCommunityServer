package net.gupt.community.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.CodeMsg;
import net.gupt.community.entity.News;
import net.gupt.community.entity.Result;
import net.gupt.community.entity.Student;
import net.gupt.community.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
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
    public Result<?> getNews() {
        List<News> newsList = newsService.getNews();
        return newsList.size() > 0 ? Result.success(CodeMsg.SUCCESS, newsList) : Result.error(CodeMsg.FAILED);
    }

    @PostMapping("/postNews")
    public Result<?> postNews(@RequestBody News news, HttpServletRequest request) {
        student = (Student) request.getAttribute("Student");
        try {
            // 获取Document文档对象
            Document document = Jsoup.connect(news.getUrl()).get();
            Element element = document.getElementsByTag("script").attr("nonce", "1322496451").get(18);
            // 进行数据处理
            String[] data = element.data().replace(";", ",").trim().split("var");
            String str = Arrays.toString(data).replace(",", "");
            // 过滤数据
            int titleIndex = str.indexOf("msg_title = ");
            int urlIndex = str.indexOf("cdn_url_1_1  = ");
            String rawData = str.substring(titleIndex, urlIndex);
            String subData = rawData.replace("\n", ",");
            String srtData = Arrays.toString(subData.split("=", 4)).replace("[", "").replace("]", "");
            // 获取指定数据
            String[] split = srtData.split(",");
            // 格式化数据
            String title = split[1].replace("\"", "").trim();
            String coverImg = split[5].replace("\"", "").trim();
            news.setTitle(title);
            news.setCoverImg(coverImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsService.insertNews(news, student);
    }
}
