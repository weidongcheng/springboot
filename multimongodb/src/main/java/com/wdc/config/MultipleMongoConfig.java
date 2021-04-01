package com.wdc.config;

import com.wdc.config.props.MultipleMongoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by wdc on 2021/3/29 13:25
 */
@Configuration
public class MultipleMongoConfig {

    @Autowired
    private MultipleMongoProperties mongoProperties;

    @Primary
    @Bean(name = "primaryMongoTemplate")
    public MongoTemplate primaryMongoTemplate() throws Exception{
        return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
    }

    @Bean
    @Qualifier("secondaryMongoTemplate")
    public MongoTemplate secondaryMongoTemplat() throws Exception{
        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
    }

    private MongoDatabaseFactory secondaryFactory(MongoProperties secondary) {
        return null;
    }

    @Primary
    private MongoDbFactory primaryFactory(MongoProperties primary) {
        return null;
    }

}
