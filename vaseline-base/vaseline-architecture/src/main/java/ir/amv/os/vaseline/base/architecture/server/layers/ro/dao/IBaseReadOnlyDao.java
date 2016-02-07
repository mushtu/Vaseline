package ir.amv.os.vaseline.base.architecture.server.layers.ro.dao;

import ir.amv.os.vaseline.base.architecture.server.layers.base.dao.IBaseDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseDao {

    E getById(Id id);
    E getByIdDetached(Id id);

    Long countAll();
    List<E> getAll();
    List<E> getAll(PagingDto pagingDto);

    Long countByExample(D example);
    List<E> searchByExample(D example);
    List<E> searchByExample(D example, PagingDto pagingDto);

    void setEntityClass(Class<E> entityClass);
    Class<E> getEntityClass();

}
