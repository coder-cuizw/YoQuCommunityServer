package net.gupt.community;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <h3>GuptCommunityServer</h3>
 * <p>Swagger配置类</p>
 *
 * @author : Cui
 * @date : 2019-11-26 08:22
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger常用注解
     * <p>
     * 作用范围                      API                 使用位置
     * 协议集描述	                @Api	            用于 Controller 类上
     * 协议描述	                    @ApiOperation       用在 Controller 的方法上
     * 非对象参数集	                @ApiImplicitParams	用在 Controller 的方法上
     * 非对象参数描述	            @ApiImplicitParam	用在 @ApiImplicitParams 的方法里边
     * 响应集	                    @ApiResponses	    用在 Controller 的方法上
     * 响应信息参数	                @ApiResponse	    用在 @ApiResponses 里边
     * 描述返回对象的意义             @ApiModel	        用在返回对象类上
     * 对象属性	                    @ApiModelProperty	用在出入参数对象的字段上
     */

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("net.gupt.community.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Yo趣社区")
                .description("Yo趣社区 API 1.0 操作文档")
                //服务条款网址
                .termsOfServiceUrl("https://blog.cuizw.cn/contract.html")
                .version("1.0")
                .contact(new Contact("CuiZW", "https://blog.cuizw.cn/", "1186325237@qq.com"))
                .build();
    }

}
