package ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.crud.dai;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.dai.BaseEntityReadOnlyDai;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.crud.dai.EntityCrudDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;

import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseEntityCrudDai<E extends Identifiable<Id>, Id extends Serializable>
        extends BaseEntityReadOnlyDai<E, Id>
        implements EntityCrudDai<E> {

    @Override
    public void preSave(E entity) throws BaseVaselineServerException {
        BaseEntityCrudDaiHelper.preSave(entity);
    }

    @Override
    public void postSave(E entity) throws BaseVaselineServerException {
        BaseEntityCrudDaiHelper.postSave(entity);
    }

    @Override
    public void preUpdate(E entity) throws BaseVaselineServerException {
        BaseEntityCrudDaiHelper.preUpdate(entity);
    }

    @Override
    public void postUpdate(E entity) throws BaseVaselineServerException {
        BaseEntityCrudDaiHelper.postUpdate(entity);
    }

    @Override
    public void preDelete(E entity) throws BaseVaselineServerException {
        BaseEntityCrudDaiHelper.preDelete(entity);
    }

    @Override
    public void postDelete(E entity) throws BaseVaselineServerException {
        BaseEntityCrudDaiHelper.postDelete(entity);
    }
}
