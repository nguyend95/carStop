package main.java.eu.epptec.carStop.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * This config will scan for datasource, services,...
 */
@Configuration
@PropertySource("classpath:config.properties")
public class RootConfig {
    @Autowired
    private Environment env;

    @Bean(initMethod = "migrate")
    protected Flyway flyway(){
        return Flyway.configure()
                .dataSource("/db/database",
                        env.getProperty("flyway.username"),
                        env.getProperty("flyway.password"))
                .baselineOnMigrate(true)
                .locations("db/migration")
                .schemas("app-db")
                .load();
    }
}
