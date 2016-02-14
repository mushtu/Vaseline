package ir.amv.os.vaseline.reporting.async.rest.server.base.ro.service.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.service.BaseReadOnlyServiceImpl;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequest;
import ir.amv.os.vaseline.reporting.async.api.server.base.ro.IBaseReportingReadOnlyAsyncApi;
import ir.amv.os.vaseline.reporting.async.rest.server.base.ro.service.IBaseReportingReadOnlyAsyncService;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingReadOnlyAsyncServiceImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, API extends IBaseReportingReadOnlyAsyncApi<E, D, Id>>
        extends BaseReadOnlyServiceImpl<E, D, Id, API>
        implements IBaseReportingReadOnlyAsyncService<D, Id>{

    @Override
    public Long reportByExample(CreateReportRequest request, D example) throws BaseVaselineServerException {
        Future<Long> longFuture = api.reportByExample(request, example);
        try {
            return longFuture.get();
        } catch (InterruptedException e) {
            throw new BaseVaselineServerException(e);
        } catch (ExecutionException e) {
            throw new BaseVaselineServerException(e);
        }
    }
}