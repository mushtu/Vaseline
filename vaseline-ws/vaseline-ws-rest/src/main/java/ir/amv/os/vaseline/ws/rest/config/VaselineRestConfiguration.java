package ir.amv.os.vaseline.ws.rest.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mushtu on 4/14/16.
 */
public class VaselineRestConfiguration {

    private String baseAddress;
    private List<Object> providers = new ArrayList<Object>();

    public String getBaseAddress() {
        return baseAddress;
    }

    public void setBaseAddress(String baseAddress) {
        this.baseAddress = baseAddress;
    }

    public List<Object> getProviders() {
        return providers;
    }

    public void addProvider(Object provider)
    {
        providers.add(provider);
    }


}
