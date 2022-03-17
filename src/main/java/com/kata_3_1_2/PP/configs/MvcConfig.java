package com.kata_3_1_2.PP.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}


    //от Марата для теста:
// Для инициализации тестовых юзеров создай отдельный бин,
//   а в нём - один @PostConstruct-метод, где будешь на Role-сервисе создавать и лить в БД все нужные роли,
//   а на User-сервисе создавать юзеров, сетить им имеющиеся роли и лить юзеров в БД