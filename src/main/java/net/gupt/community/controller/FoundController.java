package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.annotation.LimitFrequency;
import net.gupt.community.entity.*;
import net.gupt.community.service.FoundService;
import net.gupt.community.service.ImgService;
import net.gupt.community.vo.FoundQueryVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    private final Found found;
    private final ImgService imgService;
    private final String studentObject = "Student";
    public FoundController(FoundService foundService, Found found, ImgService imgService) {
        this.foundService = foundService;
        this.found = found;
        this.imgService = imgService;
    }

    /**
     * Description 查询所有失物信息<br/>
     *
     * @param pageNum      查询的页数
     * @param pageSize     页面数据多少
     * @param articleState 失物状态
     * @param query        查询条件的对象
     * @return Result
     * @author YG<br />
     * @date 2019/8/8 9:58<br/>
     */
    @GetMapping(value = "/getFounds")
    public Result getFounds(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            @RequestParam(value = "articleState", required = false) Boolean articleState,
                            @RequestParam(value = "isTop",required = false)Boolean isTop,
                            @RequestParam(value = "id", required = false) Integer id,
                            FoundQueryVo query) {
        found.setArticleState(articleState);
        found.setId(id);
        query.setFound(found);
        PageInfo<Found> foundPageInfo = foundService.getFounds(pageNum, pageSize, query);
        if (foundPageInfo == null) {
            return Result.error(CodeMsg.FAILED);
        }
        return Result.success(CodeMsg.SUCCESS, new PageInfoBean<>(foundPageInfo));
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
    @PostMapping(value = "/postFound", consumes = "application/json")
    public Result postFound(@RequestBody Found found,Img imgObject) {
        int rows = foundService.postFound(found);
        if (rows > 0) {
            if(found.getImg() !=null ) {
                //获取失物文章id和文章类型
                Integer articleId = found.getId();
                List<Img> imgList = found.getImg();
                for (Img img : imgList
                ) {
                    img.setArticleId(articleId);
                    img.setArticleType((byte) 2);
                    imgObject = img;
                }
                if(!imgObject.getImgUrl().trim().equals("")) {
                    imgService.postImg(imgObject);
                }
            }
            return Result.success(CodeMsg.POST_SUCCESS, found.getId());
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
    @PostMapping(value = "updateFoundStatus", consumes = "application/json")
    public Result updateFoundStatus(@RequestBody Found found) {
        int rows = foundService.updateFoundStatus(found);
        if (rows > 0) {
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        return Result.error(CodeMsg.UPDATE_FAILED);
    }

    /**
     * Description 删除失物信息 br/>
     *
     * @param id <br/>
     * @return Result
     * @author YG <br/>
     * @date 2019/8/8 10:01<br/>
     */
    @GetMapping(value = "deleteFoundInfo")
    public Result deleteFoundInfo(@RequestParam(value = "id") Integer id) {
        int rows = foundService.deleteFoundInfo(id);
        if (rows > 0) {
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
