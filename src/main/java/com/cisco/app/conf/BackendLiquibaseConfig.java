package com.cisco.app.conf;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BackendLiquibaseConfig {
    private static final String DOMAIN_CHANGELOG_LOCATION = "/db/migration/liquibase/master.xml";

    private SpringLiquibase domainSpringLiquibase(DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        return liquibase;
    }

    @Bean
    public LiquibaseProperties domainLiquibaseProperties() {
        var properties = new LiquibaseProperties();
        properties.setChangeLog(DOMAIN_CHANGELOG_LOCATION);
        return properties;
    }

    @Bean
    public SpringLiquibase domainLiquibase(DataSource dataSource, @Qualifier("domainLiquibaseProperties") LiquibaseProperties properties) {
        return domainSpringLiquibase(dataSource, properties);
    }

}
