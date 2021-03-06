package ir.amv.os.vaseline.ws.rest.server.base.crud;

import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.rest.server.base.ro.ReadOnlyResource;

import java.io.Serializable;

/**
 * Created by AMV on 2/13/2016.
 */
public interface CrudResource<D extends IBaseDto<Id>, Id extends Serializable>
        extends ReadOnlyResource<D, Id> {

    Id save(D t) throws BaseVaselineClientException;

    void update(D t) throws BaseVaselineClientException;

    void delete(D id) throws BaseVaselineClientException;

    void deleteById(Id id) throws BaseVaselineClientException;
}
