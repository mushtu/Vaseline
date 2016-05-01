package ir.amv.os.vaseline.base.architecture.impl.hibernate.config;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao.DefaultPagingResultCreator;
import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao.IPagingResultCreator;
import ir.amv.os.vaseline.base.caching.config.VaselineCachingConfig;
import ir.amv.os.vaseline.base.jdbc.config.VaselineJdbcConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("ir.amv.os.vaseline.base.architecture.impl.hibernate.server")
@Import({
        VaselineJdbcConfig.class,
        VaselineCachingConfig.class
})
public class VaselineHibernateConfig implements InitializingBean {

    @Autowired(required = false)
    List<VaselineHibernateConfigurer> configurers = Collections.emptyList();

    @Autowired
    Environment environment;

    private VaselineHibernateConfigurerDelegate configurerDelegate;

    @Override
    public void afterPropertiesSet() throws Exception {
        configurerDelegate = new VaselineHibernateConfigurerDelegate(configurers);
    }


    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {

        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource);
        sf.setHibernateProperties(additionalProperties());
        List<String> packages = new ArrayList<String>();
        configurerDelegate.configurePackagesToScan(packages);
        List<Resource> rscList = new ArrayList<Resource>();
        configurerDelegate.configureResourceLocations(rscList);
        Resource[] rscArr = new Resource[rscList.size()];
        sf.setMappingLocations(rscList.toArray(rscArr));
        packages.add("ir.amv"); //todo remove hard coded package name!!
        String[] arr = new String[packages.size()];
        sf.setPackagesToScan(packages.toArray(arr));

        try {
            sf.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sf.getObject();
    }

    @Bean
    @Autowired
    @Order(1)
    public PlatformTransactionManager transactionManager(DataSource dataSource,
                                                         SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setDataSource(dataSource);
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    @Autowired
    public TransactionTemplate transactionTemplate(
            PlatformTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager);
        return transactionTemplate;
    }


    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.validator.apply_to_ddl", "false");
        properties.put("hibernate.validator.autoregister_listeners", "false");
        properties.put("hibernate.dialect",
                environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",
                environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.generate_statistics", "true");
        properties.put("hibernate.use_sql_comments", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.connection.autocommit", "false");
        if (environment.acceptsProfiles("initDB")) {
            properties.put("hibernate.hbm2ddl.auto", "create-drop");
        } else {
            properties.put("hibernate.hbm2ddl.auto", "update");
        }

        properties.put("hibernate.cache.use_second_level_cache", "true");
        properties.put("hibernate.cache.use_query_cache", "true");
        properties.put("hibernate.cache.region.factory_class",
                "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        return properties;
    }

    @Bean
    public IPagingResultCreator pagingResultCreator() {
        return new DefaultPagingResultCreator();
    }


}
