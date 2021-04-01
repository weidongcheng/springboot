package com.wdc.config;

import com.wdc.config.props.MultipleMongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by wdc on 2021/3/29 13:23
 */
@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.wdc.repository.primary",mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {
}
