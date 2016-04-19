package ir.amv.os.vaseline.base.json.config;

import ir.amv.os.vaseline.base.core.config.VaselineConfigurer;
import ir.amv.os.vaseline.base.json.server.GraphAdapterBuilder;

/**
 * @author mushtu
 * @since 4/19/16.
 */
public interface VaselineJsonConfigurer extends VaselineConfigurer{

    GraphAdapterBuilder configureGraphAdapterBuilder(GraphAdapterBuilder graphAdapterBuilder);
}
