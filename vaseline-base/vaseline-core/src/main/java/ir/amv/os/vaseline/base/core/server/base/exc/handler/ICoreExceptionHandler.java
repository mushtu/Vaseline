package ir.amv.os.vaseline.base.core.server.base.exc.handler;

import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;

public interface ICoreExceptionHandler {

    BaseVaselineClientException convertException(Exception exception);
}
