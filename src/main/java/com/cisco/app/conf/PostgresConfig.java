package com.cisco.app.conf;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@ConditionalOnProperty(value = "database.type", havingValue = "postgresql")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresqlEntityManagerFactory",
        transactionManagerRef = "postgresqlTransactionManager",
        basePackages = {
                "com.cisco.app.entity",
                "com.cisco.app.respository"
        }
)
public class PostgresConfig {

    @Value("${database.postgresql.hibernate.hbm2ddl.auto}")
    private String autoUpdateProperty;

    @Primary
    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix = "database.postgresql")
    public DataSource postgresqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    postgresqlEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresqlDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages(
                        "com.cisco.app.entity",
                        "com.cisco.app.respository")
                .persistenceUnit("default")
                .properties(jpaProperties())
                .build();
    }

    @Primary
    @Bean(name = "postgresqlTransactionManager")
    public PlatformTransactionManager postgresqlTransactionManager(
            @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory
                    postgresqlEntityManagerFactory
    ) {
        return new JpaTransactionManager(postgresqlEntityManagerFactory);
    }

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        props.put("hibernate.hbm2ddl.auto", autoUpdateProperty);
        return props;
    }
}
