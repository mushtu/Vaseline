package ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.dai;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.ReadOnlyDao;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.dai.EntityReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseReadOnlyDaiHelper {

    private BaseReadOnlyDaiHelper() {
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            E getById(
            EntityReadOnlyDai<E> api,
            ReadOnlyDao<E, D, Id> dao,
            Id id)
            throws BaseVaselineServerException {
        E findById = dao.getById(id);
        api.postGet(findById);
        return findById;
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            Long countAll(
            EntityReadOnlyDai<E> api,
            ReadOnlyDao<E, D, Id> dao)
            throws BaseVaselineServerException {
        Long countAll = dao.countAll();
        return countAll;
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            List<E> getAll(
            EntityReadOnlyDai<E> api,
            ReadOnlyDao<E, D, Id> dao)
            throws BaseVaselineServerException {
        List<E> all = dao.getAll();
        if (all != null) {
            for (E entity : all) {
                api.postGet(entity);
            }
        }
        return all;
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            List<E> getAll(
            EntityReadOnlyDai<E> api,
            ReadOnlyDao<E, D, Id> dao,
            PagingDto pagingDto)
            throws BaseVaselineServerException {
        List<E> all = dao.getAll(pagingDto);
        if (all != null) {
            for (E entity : all) {
                api.postGet(entity);
            }
        }
        return all;
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            Long countByExample(
            EntityReadOnlyDai<E> api,
            ReadOnlyDao<E, D, Id> dao,
            D example)
            throws BaseVaselineServerException {
        Long countByExample = dao.countByExample(example);
        return countByExample;
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            List<E> searchByExample(
            EntityReadOnlyDai<E> api,
            ReadOnlyDao<E, D, Id> dao,
            D example)
            throws BaseVaselineServerException {
        List<E> searchByExample = dao.searchByExample(example);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                api.postGet(entity);
            }
        }
        return searchByExample;
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
            List<E> searchByExample(
            EntityReadOnlyDai<E> api,
            ReadOnlyDao<E, D, Id> dao,
            D example,
            PagingDto pagingDto)
            throws BaseVaselineServerException {
        List<E> searchByExample = dao.searchByExample(example, pagingDto);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                api.postGet(entity);
            }
        }
        return searchByExample;
    }

}
