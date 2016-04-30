package ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.dai;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.dai.DataAccessInterface;

public class BaseDai implements DataAccessInterface {

    private Object proxy;

    @Override
    public <Proxy> Proxy getProxy(Class<Proxy> proxyClass) {
        return (Proxy) proxy;
    }

    @Override
    public <Proxy> void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

}
