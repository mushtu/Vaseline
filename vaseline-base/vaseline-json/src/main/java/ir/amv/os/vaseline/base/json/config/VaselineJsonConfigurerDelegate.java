package ir.amv.os.vaseline.base.json.config;

import ir.amv.os.vaseline.base.json.server.GraphAdapterBuilder;

/**
 * @author mushtu
 * @since 4/19/16.
 */
public class VaselineJsonConfigurerDelegate implements VaselineJsonConfigurer {

    private final Iterable<VaselineJsonConfigurer> delegates;

    public VaselineJsonConfigurerDelegate(Iterable<VaselineJsonConfigurer> delegates) {
        this.delegates = delegates;
    }


    @Override
    public GraphAdapterBuilder configureGraphAdapterBuilder(GraphAdapterBuilder graphAdapterBuilder) {
        for (VaselineJsonConfigurer configurer : delegates)
            configurer.configureGraphAdapterBuilder(graphAdapterBuilder);
        return graphAdapterBuilder;
    }
}
