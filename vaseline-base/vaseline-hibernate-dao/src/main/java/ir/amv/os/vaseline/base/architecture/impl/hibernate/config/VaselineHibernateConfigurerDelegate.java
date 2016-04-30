package ir.amv.os.vaseline.base.architecture.impl.hibernate.config;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author mushtu
 * @since 4/13/16.
 */
public class VaselineHibernateConfigurerDelegate implements VaselineHibernateConfigurer {

    private final Iterable<VaselineHibernateConfigurer> delegates;

    public VaselineHibernateConfigurerDelegate(Iterable<VaselineHibernateConfigurer> delegates) {
        Assert.notNull("VaselineHibernateConfigurers must not be null!");
        this.delegates = delegates;
    }

    @Override
    public void configurePackagesToScan(List<String> packages) {
        for(VaselineHibernateConfigurer configurer:delegates)
            configurer.configurePackagesToScan(packages);
    }

    @Override
    public void configureResourceLocations(List<Resource> resources) {
        for(VaselineHibernateConfigurer configurer:delegates)
            configurer.configureResourceLocations(resources);
    }

}
