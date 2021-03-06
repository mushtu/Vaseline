package ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.ReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

/**
 * Created by AMV on 2/7/2016.
 */
public interface CrudDao<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends ReadOnlyDao<E, D, Id> {

    // CUD Operations
    Id save(E entity);

    void update(E entity);

    void delete(E entity);

}
