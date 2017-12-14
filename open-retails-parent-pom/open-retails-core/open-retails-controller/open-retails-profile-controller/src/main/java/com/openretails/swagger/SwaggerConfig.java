package com.openretails.swagger;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${profile.release.version}")
	private String releaseVersion;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.openretails.web.api"))
.paths(PathSelectors.any())
				.build().apiInfo(apiInfo()).useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,
						newArrayList(
								new ResponseMessageBuilder().code(500).message("500 message")
										.responseModel(new ModelRef("Error")).build(),
								new ResponseMessageBuilder().code(403).message("Forbidden!!!!!").build()));
	}

	private ApiInfo apiInfo() {
		final String title = "PROFILE MANAGEMENT FOR OPEN RETAILS";
		final String description = "This is profile management services where profile will be fully managed";
		final String termsOfServiceUrl = "http://www.openretails.in/v2/api-docs";
		final Contact contact = new Contact("Shobhanath Sharma", "http://www.openretails.in",
				"shobhanath.sharma@openretails.in");
		final String license = "openretails";
		final String licenseUrl = "http://www.openretails.in";
		@SuppressWarnings("rawtypes")
		final Collection<VendorExtension> vendorExtensions = new ArrayList<>();
		@SuppressWarnings("rawtypes")
		final VendorExtension vendorExtension = new VendorExtension() {
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object getValue() {
				// TODO Auto-generated method stub
				return null;
			}

		};

		vendorExtensions.add(vendorExtension);

		final ApiInfo apiInfo = new ApiInfo(title, description, releaseVersion, termsOfServiceUrl, contact, license,
				licenseUrl, vendorExtensions);
		return apiInfo;
	}
}