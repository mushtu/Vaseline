package ir.amv.os.vaseline.security.authentication.api.shared.api;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.dai.DataAccessInterface;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IAuthenticationApi extends DataAccessInterface {

    String getCurrentUsername() throws BaseVaselineServerException;
}
