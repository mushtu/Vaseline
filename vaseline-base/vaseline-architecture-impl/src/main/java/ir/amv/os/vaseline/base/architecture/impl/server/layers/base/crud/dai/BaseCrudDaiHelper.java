package ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.dai;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.dai.BaseReadOnlyDaiHelper;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.CrudDao;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.crud.dai.EntityCrudDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseCrudDaiHelper {

    private BaseCrudDaiHelper() {
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> Id save(
            EntityCrudDai<E> api,
            CrudDao<E, D, Id> dao,
            E entity)
            throws BaseVaselineServerException {
        api.preSave(entity);
        Id id = dao.save(entity);
        api.postSave(entity);
        return id;
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> List<Id> saveBatch(
            EntityCrudDai<E> api,
            CrudDao<E, D, Id> dao,
            List<E> entities)
            throws BaseVaselineServerException {
        List<Id> result = new ArrayList<Id>();
        if (entities != null) {
            for (E entity : entities) {
                Id id = dao.save(entity);
                result.add(id);
            }
        }
        return result;
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void update(
            EntityCrudDai<E> api,
            CrudDao<E, D, Id> dao,
            E entity)
            throws BaseVaselineServerException {
        api.preUpdate(entity);
        dao.update(entity);
        api.postUpdate(entity);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void updateBatch(
            EntityCrudDai<E> api,
            CrudDao<E, D, Id> dao,
            List<E> entities)
            throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                dao.update(entity);
            }
        }
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void delete(
            EntityCrudDai<E> api,
            CrudDao<E, D, Id> dao,
            E entity)
            throws BaseVaselineServerException {
        api.preDelete(entity);
        dao.delete(entity);
        api.postDelete(entity);
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void deleteBatch(
            EntityCrudDai<E> api,
            CrudDao<E, D, Id> dao,
            List<E> entities)
            throws BaseVaselineServerException {
        if (entities != null) {
            for (E entity : entities) {
                delete(api, dao, entity);
            }
        }
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            void delete(
            EntityCrudDai<E> api,
            CrudDao<E, D, Id> dao,
            Id id)
            throws BaseVaselineServerException {
        E byId = BaseReadOnlyDaiHelper.getById(api, dao, id);
        delete(api, dao, byId);
    }

}
