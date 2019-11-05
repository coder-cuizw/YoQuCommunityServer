package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.FoundService;
import net.gupt.community.service.ImgService;
import net.gupt.community.util.QiniuUtil;
import net.gupt.community.vo.FoundVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <h3>gupt-community</h3>
 * <p>失物找回web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:01
 **/
@Slf4j
@AuthToken
@RestController
@RequestMapping(value = "/found", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FoundController {

    private final FoundService foundService;
    private final ImgService imgService;
    private final Qiniu qiniu;
    private final HttpServletRequest request;
    private final String stu = "Student";

    public FoundController(FoundService foundService, ImgService imgService, Qiniu qiniu, HttpServletRequest request) {
        this.foundService = foundService;
        this.imgService = imgService;
        this.qiniu = qiniu;
        this.request = request;
    }

    /**
     * Description 查询所有失物信息<br/>
     *
     * @param pageNum      查询的页数
     * @param pageSize     页面数据多少
     * @param articleState 失物状态
     * @return Result
     * @author YG<br />
     * @date 2019/8/8 9:58<br/>
     */
    @GetMapping(value = "/getArticles")
    public Result getFounds(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            @RequestParam(value = "articleState", required = false) Boolean articleState,
                            @RequestParam(value = "isTop", required = false) Boolean isTop,
                            @RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "isSearch", required = false) Boolean isSearch,
                            @RequestParam(value = "searchContent", required = false) String searchContent) {

        PageInfo<Found> foundPageInfo = foundService.getFounds(pageNum, pageSize, id, articleState, isTop, null, isSearch, searchContent);
        return foundPageInfo == null ?
                Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(foundPageInfo));
    }

    /**
     * Description 发送失物信息 <br/>
     *
     * @param found <br/>
     * @return Result
     * @author YG <br/>
     * @date 2019/8/8 10:00<br/>
     */
    @LimitFrequency(count = 3)
    @PostMapping(value = "/postArticle")
    public Result postFound(@RequestBody FoundVo found) {
        Student student = (Student) request.getAttribute(stu);
        found.setUid(student.getUid());
        int rows = foundService.postFound(found);
        if (rows > 0) {
            List<Img> imgList = found.getImg();
            if (imgList != null && !imgList.isEmpty()) {
                Integer articleId = found.getId();
                imgList.stream().filter(img -> !img.getImgUrl().trim().isEmpty()).forEach(img -> {
                    img.setArticleId(articleId).setArticleType((byte) 2);
                    imgService.postImg(img);
                });
            }
            return Result.success(CodeMsg.SUCCESS, found.getId());
        }
        return Result.error(CodeMsg.POST_FAILED);
    }

    /**
     * Description 更新失物信息<br/>
     *
     * @param found <br/>
     * @return Result
     * @author YG <br/>
     * @date 2019/8/8 10:00<br/>
     */
    @LimitFrequency(count = 5)
    @PostMapping(value = "/updateFoundStatus")
    public Result updateFoundStatus(@RequestBody Found found) {
        int rows = foundService.updateFoundStatus(found);
        return rows > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.UPDATE_FAILED);
    }

    /**
     * Description 删除失物信息 br/>
     *
     * @param id <br/>
     * @return Result
     * @author YG <br/>
     * @date 2019/8/8 10:01<br/>
     */
    @DeleteMapping("/deleteArticle")
    public Result deleteFoundInfo(@RequestParam(value = "id") Integer id,
                                  @RequestParam(value = "uid") Integer uid) {
        Student student = (Student) request.getAttribute(stu);
        boolean isMe = uid.equals(student.getUid());
        boolean permission = student.getPermission();
        List<Img> imgList = imgService.getImgs(id, (byte) 2);
        if (isMe || permission) {
            int rows = foundService.deleteFoundInfo(id);
            boolean delResult = QiniuUtil.delete
                    (qiniu.getAccessKey(), qiniu.getSecretKey(), qiniu.getBucket(), rows, imgList);
            if (rows > 0 || delResult) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
