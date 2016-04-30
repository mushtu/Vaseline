package ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.crud.dai;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.crud.dai.BaseEntityCrudDaiHelper;
import ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.ro.dai.BaseMultiDaoReadOnlyDai;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.CrudDao;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.crud.MultiDaoCrudDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public abstract class BaseMultiDaoCrudDai<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends CrudDao<E, D, Id>>
        extends BaseMultiDaoReadOnlyDai<E, D, Id, DAO>
        implements MultiDaoCrudDai<E, D, Id> {

    @Override
    @Transactional
    public Id save(String coreId, E entity) throws BaseVaselineServerException {
        return BaseMultiDaoCrudDaiHelper.save(this, coreId, entity);
    }

    @Override
    @Transactional
    public List<Id> saveBatch(String coreId, List<E> entities) throws BaseVaselineServerException {
        return BaseMultiDaoCrudDaiHelper.saveBatch(this, coreId, entities);
    }

    @Override
    @Transactional
    public void update(String coreId, E entity) throws BaseVaselineServerException {
        BaseMultiDaoCrudDaiHelper.update(this, coreId, entity);
    }

    @Override
    @Transactional
    public void updateBatch(String coreId, List<E> entities) throws BaseVaselineServerException {
        BaseMultiDaoCrudDaiHelper.updateBatch(this, coreId, entities);
    }

    @Override
    @Transactional
    public void delete(String coreId, E entity) throws BaseVaselineServerException {
        BaseMultiDaoCrudDaiHelper.delete(this, coreId, entity);
    }

    @Override
    @Transactional
    public void deleteBatch(String coreId, List<E> entities) throws BaseVaselineServerException {
        BaseMultiDaoCrudDaiHelper.deleteBatch(this, coreId, entities);
    }

    @Override
    @Transactional
    public void delete(String coreId, Id id) throws BaseVaselineServerException {
        BaseMultiDaoCrudDaiHelper.delete(this, coreId, id);
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
