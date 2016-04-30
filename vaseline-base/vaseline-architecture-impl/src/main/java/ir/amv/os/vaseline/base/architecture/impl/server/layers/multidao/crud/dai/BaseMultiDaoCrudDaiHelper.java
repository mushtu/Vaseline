package ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.crud.dai;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.dai.BaseCrudDaiHelper;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.CrudDao;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.crud.MultiDaoCrudDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public abstract class BaseMultiDaoCrudDaiHelper {

    private BaseMultiDaoCrudDaiHelper() {
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> Id save(
            MultiDaoCrudDai<E, D, Id> api,
            String coreId,
            E entity)
            throws BaseVaselineServerException {
        CrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseCrudDaiHelper.save(api, dao, entity);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<Id> saveBatch(
            MultiDaoCrudDai<E, D, Id> api,
            String coreId,
            List<E> entities)
            throws BaseVaselineServerException {
        CrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseCrudDaiHelper.saveBatch(api, dao, entities);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void update(
            MultiDaoCrudDai<E, D, Id> api,
            String coreId,
            E entity)
            throws BaseVaselineServerException {
        CrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudDaiHelper.update(api, dao, entity);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void updateBatch(
            MultiDaoCrudDai<E, D, Id> api,
            String coreId,
            List<E> entities)
            throws BaseVaselineServerException {
        CrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudDaiHelper.updateBatch(api, dao, entities);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void delete(
            MultiDaoCrudDai<E, D, Id> api,
            String coreId,
            E entity)
            throws BaseVaselineServerException {
        CrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudDaiHelper.delete(api, dao, entity);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void deleteBatch(
            MultiDaoCrudDai<E, D, Id> api,
            String coreId,
            List<E> entities)
            throws BaseVaselineServerException {
        CrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudDaiHelper.deleteBatch(api, dao, entities);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void delete(
            MultiDaoCrudDai<E, D, Id> api,
            String coreId,
            Id id)
            throws BaseVaselineServerException {
        CrudDao<E, D, Id> dao = api.getDaoFor(coreId);
        BaseCrudDaiHelper.delete(api, dao, id);
    }

}
