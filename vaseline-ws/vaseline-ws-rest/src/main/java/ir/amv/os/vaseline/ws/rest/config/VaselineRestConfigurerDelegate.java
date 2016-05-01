package ir.amv.os.vaseline.ws.rest.config;

/**
 * Created by mushtu on 4/14/16.
 */
public class VaselineRestConfigurerDelegate implements VaselineRestConfigurer {

    Iterable<VaselineRestConfigurer> delegates;

    public VaselineRestConfigurerDelegate(Iterable<VaselineRestConfigurer> delegates) {
        this.delegates = delegates;
    }

    @Override
    public void configureRestService(VaselineRestConfiguration configuration) {
        for (VaselineRestConfigurer configurer : delegates)
            configurer.configureRestService(configuration);
    }
}
