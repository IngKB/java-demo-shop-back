package com.pruebaclean.demoCl;


import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

@SpringBootApplication
public class DemoCleanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCleanApplication.class, args);
	}

	@Bean
	BeanFactoryPostProcessor beanFactoryPostProcessor(ApplicationContext beanRegistry) {

		return beanFactory -> {
			genericApplicationContext(
					(BeanDefinitionRegistry) beanFactory);
		};
	}

	void genericApplicationContext(BeanDefinitionRegistry beanRegistry) {
		String BASE_PACKAGE = "com.pruebaclean.demoCl";
		ClassPathBeanDefinitionScanner beanDefinitionScanner = new ClassPathBeanDefinitionScanner(beanRegistry);
		beanDefinitionScanner.addIncludeFilter(removeModelAndEntitiesFilter());
		beanDefinitionScanner.scan(BASE_PACKAGE+".auth");
		beanDefinitionScanner.scan(BASE_PACKAGE+".product");
//		beanDefinitionScanner.scan(BASE_PACKAGE+".category");

	}

	static TypeFilter removeModelAndEntitiesFilter() {
		return (MetadataReader mr, MetadataReaderFactory mrf) -> !mr.getClassMetadata()
				.getClassName()
				.endsWith("Model");
	}

}
