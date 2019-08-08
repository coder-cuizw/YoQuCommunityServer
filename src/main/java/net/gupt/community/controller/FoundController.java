package net.gupt.community.controller;

import com.github.pagehelper.PageInfo;
import net.gupt.community.vo.FoundQueryVo;
import net.gupt.community.annotation.AuthToken;
import net.gupt.community.entity.*;
import net.gupt.community.service.FoundService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <h3>gupt-community</h3>
 * <p>失物找回web控制层</p>
 *
 * @author : Cui
 * @date : 2019-07-30 21:01
 **/
@AuthToken
@RestController
@RequestMapping(value = "/found", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FoundController {

    private final FoundService foundService;
    private static Found found;
    private static Student student;

    public FoundController(FoundService foundService) {
        this.foundService = foundService;
        found = new Found();
        student = new Student();
    }

    /**
     * Description 查询所有失物信息<br/>
     *
     * @param pageNum      查询的页数
     * @param pageSize     页面数据多少
     * @param articleState 失物状态
     * @param query        查询条件的对象
     * @param openId       微信用户唯一标识
     * @return Result
     * @author YG<br />
     * @date 2019/8/8 9:58<br/>
     */
    @RequestMapping(value = "/getFounds", method = RequestMethod.GET)
    public Result getFounds(@RequestParam(value = "pageNum") Integer pageNum,
                            @RequestParam(value = "pageSize") Integer pageSize,
                            @RequestParam(value = "articleState", required = false) Boolean articleState,
                            @RequestParam(value = "openId", required = false) String openId,
                            @RequestParam(value = "id", required = false) Integer id , FoundQueryVo query) {
        found.setArticleState(articleState);
        found.setId(id);
        student.setOpenId(openId);
        query.setFound(found);
        query.setStudent(student);
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
    @RequestMapping(value = "/postFound", method = RequestMethod.POST, consumes = "application/json")
    public Result postFound(@RequestBody Found found) {
        int rows = foundService.postFound(found);
        if (rows > 0) {
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
    @RequestMapping(value = "updateFoundStatus", method = RequestMethod.POST, consumes = "application/json")
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
    @RequestMapping(value = "deleteFoundInfo", method = RequestMethod.GET)
    public Result deleteFoundInfo(@RequestParam(value = "id") Integer id) {
        int rows = foundService.deleteFoundInfo(id);
        if (rows > 0) {
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        return Result.error(CodeMsg.DELETE_FAILED);
    }
}
