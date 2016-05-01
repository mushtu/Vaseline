package ir.amv.os.vaseline.base.architecture.server.layers.base.crud.service;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.service.ReadOnlyDataService;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface CrudDataService<D extends IBaseDto<Id>, Id extends Serializable>
        extends ReadOnlyDataService<D, Id> {

    Id save(D t) throws BaseVaselineClientException;

    void update(D t) throws BaseVaselineClientException;

    void delete(D id) throws BaseVaselineClientException;

    void deleteById(Id id) throws BaseVaselineClientException;
}
