package com.springboot.exam.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration      //객체 생성하는 명령    =>Controller, Rest Service
public class WebMvcConfig implements WebMvcConfigurer {
    // 외부에 저장되어 있는 이미지에 대하여 접근을 허락하는 설정 : //http://localhost:8085/images/.....jpg 등 가능
    @Value("${downloadPath}") // uploadPath=file:///C:/Images/
            String downloadPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { // 저장된 이미지르 보기위해 접근할 수 있도록 함
        // URL에 /images가 포함되어 있으면 => downloadPath로 연결함(요청=>실제장소로 매핑)
        registry.addResourceHandler("/images/**")
                .addResourceLocations(downloadPath);
        // downloadPath 지정 방법3가지
        // 1) 파일 시스템 경로 : file:/path/files/
        // 2) 클래스패스: 클래스패스에 위치한 리소스를 지정 => classpath:/static/
        // 3) 외부 경로: 외부 경로를 지정 => file:///external/files/ => file:///C:/Images/ 와 같이 /// 3개 사용해야 함
    }
}
