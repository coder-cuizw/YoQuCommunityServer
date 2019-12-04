package net.gupt.community.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * ClassName  News <br/>
 * Description 轮播图实体类 <br/>
 *
 * @author YG
 * @version 1.0
 * @date 2019/12/4 13:38<br/>
 * @since JDK 1.8
 */
@Getter
@Setter
public class News {
    private Integer id;
    private String title;
    private String coverImg;
    private String url;
}
