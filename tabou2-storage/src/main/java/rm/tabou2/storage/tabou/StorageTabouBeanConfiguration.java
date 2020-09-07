package rm.tabou2.storage.tabou;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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


@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "tabouEntityManagerFactory",
        transactionManagerRef = "tabouTransactionManager",
        basePackages = "rm.tabou2.storage.tabou.dao"
)
@Configuration
public class StorageTabouBeanConfiguration {

    @Value("${spring.ddc.datasource.hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${spring.ddc.datasource.hibernate.format_sql}")
    private String hibernateFormatSql;

    @Value("${spring.ddc.datasource.hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddlAuto;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.tabou2.datasource")
    public DataSource tabouDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Bean(name = "tabouEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean tabouEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(tabouDataSource())
                .properties(hibernateProperties())
                .packages("rm.tabou2.storage.tabou.entity")
                .persistenceUnit("tabouPU")
                .build();
    }

    @Primary
    @Bean(name = "tabouTransactionManager")
    public PlatformTransactionManager tabouTransactionManager(@Qualifier("tabouEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map hibernateProperties() {


        HashMap<String, String> hibernateProperties = new HashMap<>();

        hibernateProperties.put("hibernate.show_sql", hibernateFormatSql);
        hibernateProperties.put("hibernate.format_sql", hibernateShowSql);
        hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);


        return hibernateProperties;
    }


}


