package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.CommonService;
import net.gupt.community.service.ImgService;
import net.gupt.community.util.QiniuUtil;
import net.gupt.community.vo.CommonVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <h3>gupt-community</h3>
 * <p>通用帖子web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 16:53
 **/
@Slf4j
@AuthToken
@RestController
@RequestMapping(value = "/common", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommonController {

    private final CommonService commonService;
    private final ImgService imgService;
    private final Qiniu qiniu;
    private final HttpServletRequest request;
    private final String stu = "Student";


    public CommonController(CommonService commonService, ImgService imgService, Qiniu qiniu, HttpServletRequest request) {
        this.commonService = commonService;
        this.imgService = imgService;
        this.qiniu = qiniu;
        this.request = request;
    }

    /**
     * 获取帖子数据
     *
     * @param postType      帖子类型
     * @param pageNum       页数
     * @param isSearch      是否搜索
     * @param searchContent 是否搜索
     * @param isTop         是否置顶
     * @param id            文章ID
     * @param pageSize      每页条数
     * @return 结果集输出信息
     */
    @RequestMapping(value = "/getArticles", method = RequestMethod.GET)
    public Result getArticles(@RequestParam(value = "postType") Byte postType,
                              @RequestParam(value = "pageNum") Integer pageNum,
                              @RequestParam(value = "pageSize") Integer pageSize,
                              @RequestParam(value = "isTop", required = false) Boolean isTop,
                              @RequestParam(value = "id", required = false) Integer id,
                              @RequestParam(value = "isSearch", required = false) Boolean isSearch,
                              @RequestParam(value = "searchContent", required = false) String searchContent) {
        PageInfo<CommonVo> articles = commonService.getArticles(postType, pageNum, pageSize, null, id, isTop, isSearch, searchContent);
        return articles == null ? Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(articles));
    }

    /**
     * Description 整合发送图片 <br/>
     *
     * @param commonVo <br/>
     * @return Result
     * @author YG <br/>
     * @date 2019/10/8 12:34<br/>
     */
    @LimitFrequency(count = 3)
    @RequestMapping(value = "/postArticle", method = RequestMethod.POST)
    public Result postArticle(@RequestBody CommonVo commonVo) {
        Student student = (Student) request.getAttribute(stu);
        commonVo.setUid(student.getUid());
        int result = commonService.postArticle(commonVo);
        if (result > 0) {
            List<Img> imgList = commonVo.getImg();
            if (imgList != null && !imgList.isEmpty()) {
                Integer id = commonVo.getId();
                Byte postType = commonVo.getPostType();
                imgList.stream().filter(img -> !img.getImgUrl().trim().isEmpty()).forEach(img -> {
                    img.setArticleId(id).setArticleType(postType);
                    imgService.postImg(img);
                });
            }
            return Result.success(CodeMsg.SUCCESS, commonVo.getId());
        }
        return Result.error(CodeMsg.FAILED);
    }

    /**
     * 设置置顶
     *
     * @param common <br/>
     * @return Result
     */
    @PostMapping("/setTop")
    public Result setTop(@RequestBody Common common) {
        int result = commonService.setTop(common);
        return result == 0 ? Result.error(CodeMsg.FAILED) : Result.success(CodeMsg.SUCCESS);
    }

    /**
     * 删除帖子及相关数据
     *
     * @param id          帖子Id
     * @param uid         帖子的uid
     * @param articleType 帖子类型
     * @return 结果集输出信息
     */
    @DeleteMapping("/deleteArticle")
    public Result deleteArticle(@RequestParam("articleType") Byte articleType,
                                @RequestParam("id") Integer id,
                                @RequestParam("uid") Integer uid) {
        Student student = (Student) request.getAttribute(stu);
        boolean isMe = uid.equals(student.getUid());
        boolean permission = student.getPermission();
        List<Img> imgs = imgService.getImgs(id, articleType);
        if (isMe || permission) {
            int result = commonService.deleteArticle(articleType, id);
            boolean delResult = QiniuUtil.delete(qiniu.getAccessKey(), qiniu.getSecretKey(), qiniu.getBucket(), result, imgs);
            if (result > 0 || delResult) {
                return Result.success(CodeMsg.SUCCESS);
            }
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }

}