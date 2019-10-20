package net.gupt.community.entity;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * <h3>gupt-community</h3>
 * <p>分页</p>
 *
 * @author : Cui
 * @date : 2019-07-30 00:48
 **/
@Data
public class PageInfoBean<T> {

    /**
     * 当前页
     */
    private int pageNum;
    /**
     * 每页的数量
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 结果集
     */
    private List<T> list;

    public PageInfoBean(PageInfo<T> pageInfo) {
        this.pageNum = pageInfo.getPageNum();
        this.pageSize = pageInfo.getPageSize();
        this.pages = pageInfo.getPages();
        this.total = pageInfo.getTotal();
        this.list = pageInfo.getList();
    }
}
