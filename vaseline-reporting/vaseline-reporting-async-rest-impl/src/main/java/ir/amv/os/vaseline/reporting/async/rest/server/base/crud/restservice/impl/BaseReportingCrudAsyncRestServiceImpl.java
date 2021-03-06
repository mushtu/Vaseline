package ir.amv.os.vaseline.reporting.async.rest.server.base.crud.restservice.impl;

import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.shared.model.CreateReportRequestClient;
import ir.amv.os.vaseline.reporting.async.rest.server.base.crud.restservice.IBaseReportingCrudAsyncRestService;
import ir.amv.os.vaseline.reporting.async.rest.server.base.crud.service.IBaseReportingCrudAsyncService;
import ir.amv.os.vaseline.ws.rest.server.base.crud.impl.CrudResourceImpl;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingCrudAsyncRestServiceImpl<D extends IBaseDto<Id>, Id extends Serializable, S extends IBaseReportingCrudAsyncService<D, Id>>
        extends CrudResourceImpl<D, Id, S>
        implements IBaseReportingCrudAsyncRestService<D, Id> {

    @Override
    public Long reportByExample(Map<String, Object> map) throws BaseVaselineServerException {
        return service.reportByExample((CreateReportRequestClient) map
                .get("request"), (D) map.get("example"));
    }
}
