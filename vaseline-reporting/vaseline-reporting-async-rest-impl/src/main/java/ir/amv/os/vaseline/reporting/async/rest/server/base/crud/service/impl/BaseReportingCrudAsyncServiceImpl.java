package ir.amv.os.vaseline.reporting.async.rest.server.base.crud.service.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.service.BaseCrudDataService;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.api.shared.model.CreateReportRequestClient;
import ir.amv.os.vaseline.reporting.async.api.server.base.crud.IBaseReportingCrudAsyncApi;
import ir.amv.os.vaseline.reporting.async.rest.server.base.crud.service.IBaseReportingCrudAsyncService;

import java.io.Serializable;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingCrudAsyncServiceImpl<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable, API extends IBaseReportingCrudAsyncApi<E, D, Id>>
        extends BaseCrudDataService<E, D, Id, API>
        implements IBaseReportingCrudAsyncService<D, Id> {

    @Override
    public Long reportByExample(CreateReportRequestClient request, D example) throws BaseVaselineServerException {
        CreateReportRequestServer requestServer = convert(request, CreateReportRequestServer.class);
        return api.reportByExample(requestServer, example);
    }
}
