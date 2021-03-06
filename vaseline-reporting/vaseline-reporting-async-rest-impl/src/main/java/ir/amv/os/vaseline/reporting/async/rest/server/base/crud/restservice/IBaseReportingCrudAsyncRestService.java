package ir.amv.os.vaseline.reporting.async.rest.server.base.crud.restservice;

import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.async.rest.server.base.ro.restservice.IBaseReportingReadOnlyAsyncRestService;
import ir.amv.os.vaseline.ws.rest.server.base.crud.CrudResource;

import java.io.Serializable;

/**
 * Created by AMV on 2/14/2016.
 */
public interface IBaseReportingCrudAsyncRestService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReportingReadOnlyAsyncRestService<D, Id>, CrudResource<D, Id> {
}
