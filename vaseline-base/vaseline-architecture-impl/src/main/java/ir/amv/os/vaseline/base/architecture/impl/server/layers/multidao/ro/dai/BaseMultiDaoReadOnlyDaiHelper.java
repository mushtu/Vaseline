package ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.ro.dai;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.dai.BaseReadOnlyDaiHelper;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.ReadOnlyDao;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.ro.MultiDaoReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseMultiDaoReadOnlyDaiHelper {

    private BaseMultiDaoReadOnlyDaiHelper() {
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> E getById(
            MultiDaoReadOnlyDai<E, D, Id> api,
            String coreId,
            Id id)
            throws BaseVaselineServerException {
        ReadOnlyDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyDaiHelper.getById(api, dao, id);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> Long countAll(
            MultiDaoReadOnlyDai<E, D, Id> api,
            String coreId)
            throws BaseVaselineServerException {
        ReadOnlyDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyDaiHelper.countAll(api, dao);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<E> getAll(
            MultiDaoReadOnlyDai<E, D, Id> api,
            String coreId)
            throws BaseVaselineServerException {
        ReadOnlyDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyDaiHelper.getAll(api, dao);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<E> getAll(
            MultiDaoReadOnlyDai<E, D, Id> api,
            String coreId,
            PagingDto pagingDto)
            throws BaseVaselineServerException {
        ReadOnlyDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyDaiHelper.getAll(api, dao, pagingDto);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> Long countByExample(
            MultiDaoReadOnlyDai<E, D, Id> api,
            String coreId,
            D example)
            throws BaseVaselineServerException {
        ReadOnlyDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyDaiHelper.countByExample(api, dao, example);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<E> searchByExample(
            MultiDaoReadOnlyDai<E, D, Id> api,
            String coreId,
            D example)
            throws BaseVaselineServerException {
        ReadOnlyDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyDaiHelper.searchByExample(api, dao, example);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<E> searchByExample(
            MultiDaoReadOnlyDai<E, D, Id> api,
            String coreId,
            D example,
            PagingDto pagingDto)
            throws BaseVaselineServerException {
        ReadOnlyDao<E, D, Id> dao = api.getDaoFor(coreId);
        return BaseReadOnlyDaiHelper.searchByExample(api, dao, example, pagingDto);
    }

}
