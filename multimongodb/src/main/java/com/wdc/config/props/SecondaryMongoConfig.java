package com.wdc.config.props;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by wdc on 2021/3/29 14:11
 */
@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.wdc.repository.secondary",mongoTemplateRef = "secondaryMongoTemplate")
public class SecondaryMongoConfig {
}
