package ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dai;

import ir.amv.os.vaseline.base.architecture.server.layers.ent.crud.dai.EntityCrudDai;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dai.ReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface CrudDai<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends ReadOnlyDai<E, D, Id>, EntityCrudDai<E> {

    Id save(E entity) throws BaseVaselineServerException;
    List<Id> saveBatch(List<E> entities) throws  BaseVaselineServerException;

    void update(E entity) throws BaseVaselineServerException;
    void updateBatch(List<E> entities) throws  BaseVaselineServerException;

    void delete(E entity) throws BaseVaselineServerException;
    void deleteBatch(List<E> entities) throws  BaseVaselineServerException;
    void delete(Id id) throws BaseVaselineServerException;

}
