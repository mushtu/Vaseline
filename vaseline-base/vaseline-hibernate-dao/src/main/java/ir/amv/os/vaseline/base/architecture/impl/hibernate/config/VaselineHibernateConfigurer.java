package ir.amv.os.vaseline.base.architecture.impl.hibernate.config;

import ir.amv.os.vaseline.base.core.config.VaselineConfigurer;
import org.springframework.core.io.Resource;

import java.util.List;


/**
 * @author mushtu
 * @since 4/13/16.
 */
public interface VaselineHibernateConfigurer extends VaselineConfigurer {

    //void configureSessionFactory(SessionFactoryBean sessionFactory);
    void configurePackagesToScan(List<String> packages);

    void configureResourceLocations(List<Resource> resources);
}
