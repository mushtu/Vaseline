package ir.amv.os.vaseline.ws.rest.server.base.crud.impl;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.service.CrudDataService;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.rest.server.base.crud.CrudResource;
import ir.amv.os.vaseline.ws.rest.server.base.ro.impl.ReadOnlyResourceImpl;

import java.io.Serializable;

/**
 * Created by AMV on 2/13/2016.
 */
public class CrudResourceImpl<D extends IBaseDto<Id>, Id extends Serializable, S extends CrudDataService<D, Id>>
        extends ReadOnlyResourceImpl<D, Id, S>
        implements CrudResource<D, Id> {

    @Override
    public Id save(D t) throws BaseVaselineClientException {
        return service.save(t);
    }

    @Override
    public void update(D t) throws BaseVaselineClientException {
        service.update(t);
    }

    @Override
    public void delete(D id) throws BaseVaselineClientException {
        service.delete(id);
    }

    @Override
    public void deleteById(Id id) throws BaseVaselineClientException {
        service.deleteById(id);
    }
}
