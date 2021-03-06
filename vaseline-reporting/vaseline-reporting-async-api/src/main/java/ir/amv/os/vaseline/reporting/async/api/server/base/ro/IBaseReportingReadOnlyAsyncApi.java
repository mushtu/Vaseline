package ir.amv.os.vaseline.reporting.async.api.server.base.ro;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dai.ReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.async.api.server.base.parent.IBaseReportingAsyncApi;

import java.io.Serializable;

/**
 * Created by AMV on 2/13/2016.
 */
public interface IBaseReportingReadOnlyAsyncApi<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends ReadOnlyDai<E, D, Id>, IBaseReportingAsyncApi<E> {

    Long reportByExample(CreateReportRequestServer request, D example) throws BaseVaselineServerException;

}
