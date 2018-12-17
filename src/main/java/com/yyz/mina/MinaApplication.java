package com.yyz.mina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class MinaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinaApplication.class, args);
    }
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        StringBuffer sb = new StringBuffer();
        ApiInfo apiInfo = new ApiInfo("shine test-yyz", sb.toString(), null, null, null, null, null);
        ApiSelectorBuilder asb = new Docket(DocumentationType.SWAGGER_2).select();
        asb.paths(regex("/*.*"));
        Docket docket = asb.build().apiInfo(apiInfo).useDefaultResponseMessages(false);
        return docket;
    }
}

