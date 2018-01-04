package com.openretails.swagger;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openretails.common.constant.ApplicationConstants;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String MODEL_REF = "ExceptionMessage";

	@Value("${profile.release.version}")
	private String releaseVersion;
	@Value("${profile.swagger.basepackage}")
	private String basePackage;
	@Value("${profile.swagger.title}")
	private String title;
	@Value("${profile.swagger.description}")
	private String description;
	@Value("${profile.swagger.termsofserviceurl}")
	private String termsOfServiceUrl;
	@Value("${profile.swagger.licence}")
	private String licence;
	@Value("${profile.swagger.licenceurl}")
	private String licenceUrl;
	@Value("${profile.swagger.contact.name}")
	private String name;
	@Value("${profile.swagger.contact.email}")
	private String email;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(basePackage))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, newArrayList(
						new ResponseMessageBuilder().code(500).message(ApplicationConstants.INTERNAL_SERVER_ERROR)
								.responseModel(new ModelRef(MODEL_REF)).build(),
						new ResponseMessageBuilder().code(403).message(ApplicationConstants.FORBIDDEN).build()));
	}

	private ApiInfo apiInfo() {
		final Contact contact = new Contact(name, licenceUrl, email);
		return new ApiInfo(title, description, releaseVersion, termsOfServiceUrl, contact, licence, licenceUrl,
				new ArrayList<>());
	}
}