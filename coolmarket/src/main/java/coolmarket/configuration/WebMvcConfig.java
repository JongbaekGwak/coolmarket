package coolmarket.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry regi) {
		regi.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry regi) {
//		각자 환경에 맞게 설성
		regi.addResourceHandler("/images/**").addResourceLocations("C:///Users/roo11/Desktop/coolmarket/coolmarket/images/");
		
		regi.addResourceHandler("/**").addResourceLocations("classpath:/static/").resourceChain(true)
				.addResolver(new PathResourceResolver() {
					@Override
					protected Resource getResource(String resourcePath, Resource location) throws IOException {
						Resource requestedResource = location.createRelative(resourcePath);
						return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
								: new ClassPathResource("/static/index.html");
					}
				});
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmpr = new CommonsMultipartResolver();
		cmpr.setDefaultEncoding("UTF-8");
		cmpr.setMaxUploadSizePerFile(10 * 1024 * 1024);

		return cmpr;
	}
}