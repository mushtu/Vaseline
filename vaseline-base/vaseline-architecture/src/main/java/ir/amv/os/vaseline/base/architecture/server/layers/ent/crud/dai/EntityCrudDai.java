package ir.amv.os.vaseline.base.architecture.server.layers.ent.crud.dai;

import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.dai.EntityReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;

/**
 * Created by AMV on 2/9/2016.
 */
public interface EntityCrudDai<E extends Identifiable<?>>
        extends EntityReadOnlyDai<E> {

    void preSave(E entity) throws BaseVaselineServerException;

    void postSave(E entity) throws BaseVaselineServerException;

    void preUpdate(E entity) throws BaseVaselineServerException;

    void postUpdate(E entity) throws BaseVaselineServerException;

    void preDelete(E entity) throws BaseVaselineServerException;

    void postDelete(E entity) throws BaseVaselineServerException;
}
