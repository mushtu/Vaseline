package ir.amv.os.vaseline.ws.rest.config;

import ir.amv.os.vaseline.base.core.shared.util.reflection.ReflectionUtil;
import ir.amv.os.vaseline.base.json.config.VaselineJsonConfig;
import ir.amv.os.vaseline.ws.common.config.VaselineWebServiceCommonConfig;
import ir.amv.os.vaseline.ws.rest.config.application.JaxRestApplication;
import ir.amv.os.vaseline.ws.rest.config.exception.DefaultCxfExceptionMapper;
import ir.amv.os.vaseline.ws.rest.config.exclude.annot.ExcludeRestService;
import ir.amv.os.vaseline.ws.rest.config.exclude.impl.BaseExcludeRestServiceFilter;
import ir.amv.os.vaseline.ws.rest.config.gsonhandler.GsonMessageBodyHandler;
import ir.amv.os.vaseline.ws.rest.server.base.parent.Resource;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.RuntimeDelegate;
import java.util.*;

/**
 * Created by AMV on 2/13/2016.
 */
@Configuration
@Import({
        VaselineWebServiceCommonConfig.class,
        VaselineJsonConfig.class
})
public class VaselineWebServiceRestConfig implements ApplicationContextAware,InitializingBean {

    private ApplicationContext applicationContext;
    private ArrayList<Object> restBeansList;

    @Autowired(required = false)
    List<VaselineRestConfigurer> configurers = Collections.emptyList();
    @Autowired(required = false)
    List<ExceptionMapper> exceptionMappers = Collections.emptyList();

    private VaselineRestConfigurerDelegate configurerDelegate;

    @Bean
    public GsonMessageBodyHandler gsonMessageBodyHandler() {
        return new GsonMessageBodyHandler();
    }

    @Bean
    public MessageBodyWriter<Object> jsonWriter(GsonMessageBodyHandler handler) {
        return handler;
    }

    @Bean
    public MessageBodyReader<Object> jsonReader(GsonMessageBodyHandler handler) {
        return handler;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        configurerDelegate = new VaselineRestConfigurerDelegate(configurers);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    @DependsOn({ "cxf" })
    public Server jaxRsServer(
            @Qualifier("jsonWriter") MessageBodyWriter<Object> bodyWriter,
            BaseExcludeRestServiceFilter excludeRestServiceFilter, DefaultCxfExceptionMapper defaultCxfExceptionMapper) {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance()
                .createEndpoint(jaxRsApiApplication(),
                        JAXRSServerFactoryBean.class);
        restBeansList = new ArrayList<Object>();

        Map<String, Resource> beansOfType = applicationContext.getBeansOfType(Resource.class);
        List<String> excludedBeanNames = excludeRestServiceFilter.excludedBeanNames();
        List<Class<?>> excludedBeanClasses = excludeRestServiceFilter.excludedBeanClasses();
        for (String beanName : beansOfType.keySet()) {
            if (excludedBeanNames != null && excludedBeanNames.contains(beanName)) {
                continue;
            }
            Resource restService = beansOfType.get(beanName);
            Class<? extends Resource> restServiceClass = restService.getClass();
            if (excludedBeanClasses != null) {
                boolean found = false;
                for (Class<?> excludedClass : excludedBeanClasses) {
                    if (excludedClass.isAssignableFrom(restServiceClass)) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    continue;
                }
            }
            if (ReflectionUtil.getAnnotationInHierarchy(restServiceClass, ExcludeRestService.class) != null) {
                continue;
            }
            restBeansList.add(restService);
        }
        factory.setServiceBeans(restBeansList);
        VaselineRestConfiguration config = vaselineRestConfiguration();
        List<Object> providers = config.getProviders();
        providers.add(bodyWriter);
        providers.add(defaultCxfExceptionMapper); // add default exception mapper
        factory.setProviders(providers);    // add custom exception mappers from configuration
        factory.setProviders(exceptionMappers); // add registered exception mappers beans
        factory.setAddress(config.getBaseAddress());
        return factory.create();
    }


    @Bean
    public VaselineRestConfiguration vaselineRestConfiguration()
    {
        VaselineRestConfiguration config = new VaselineRestConfiguration();
        config.setBaseAddress("/rest");
        configurerDelegate.configureRestService(config);
        return config;
    }

    @Bean
    DefaultCxfExceptionMapper exceptionMapper() {
        return new DefaultCxfExceptionMapper();
    }

    @Bean
    public JaxRestApplication jaxRsApiApplication() {
        return new JaxRestApplication();
    }

    @Bean
    public BaseExcludeRestServiceFilter excludeRestServiceFilter() {
        return new BaseExcludeRestServiceFilter() {
        };
    }

    public ArrayList<Object> getRestBeansList() {
        return restBeansList;
    }

}
