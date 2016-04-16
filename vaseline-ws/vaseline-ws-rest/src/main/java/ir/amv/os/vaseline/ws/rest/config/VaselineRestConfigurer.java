package ir.amv.os.vaseline.ws.rest.config;

import ir.amv.os.vaseline.base.core.config.VaselineConfigurer;

/**
 * Created by mushtu on 4/14/16.
 */
public interface VaselineRestConfigurer extends VaselineConfigurer {

    void configureRestService(VaselineRestConfiguration configuration);

}
