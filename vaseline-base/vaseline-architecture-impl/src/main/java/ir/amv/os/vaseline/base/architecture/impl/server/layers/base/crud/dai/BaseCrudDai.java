package ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.dai;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.dai.BaseReadOnlyDai;
import ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.crud.dai.BaseEntityCrudDaiHelper;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dai.CrudDai;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.CrudDao;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseCrudDai<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends CrudDao<E, D, Id>>
        extends BaseReadOnlyDai<E, D, Id, DAO>
        implements CrudDai<E, D, Id> {

    @Override
    @Transactional
    public Id save(E entity) throws BaseVaselineServerException {
        return BaseCrudDaiHelper.save(this, getDao(), entity);
    }

    @Override
    @Transactional
    public List<Id> saveBatch(List<E> entities) throws BaseVaselineServerException {
        return BaseCrudDaiHelper.saveBatch(this, getDao(), entities);
    }

    @Override
    @Transactional
    public void update(E entity) throws BaseVaselineServerException {
        BaseCrudDaiHelper.update(this, getDao(), entity);
    }

    @Override
    @Transactional
    public void updateBatch(List<E> entities) throws BaseVaselineServerException {
        BaseCrudDaiHelper.updateBatch(this, getDao(), entities);
    }

    @Override
    @Transactional
    public void delete(E entity) throws BaseVaselineServerException {
        BaseCrudDaiHelper.delete(this, getDao(), entity);
    }

    @Override
    @Transactional
    public void deleteBatch(List<E> entities) throws BaseVaselineServerException {
        BaseCrudDaiHelper.deleteBatch(this, getDao(), entities);
    }

    @Override
    @Transactional
    public void delete(Id id) throws BaseVaselineServerException {
        BaseCrudDaiHelper.delete(this, getDao(), id);
    }

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
