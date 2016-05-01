package ir.amv.os.vaseline.base.architecture.server.layers.multidao.crud;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.CrudDao;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.crud.dai.EntityCrudDai;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.ro.MultiDaoReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface MultiDaoCrudDai<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends MultiDaoReadOnlyDai<E, D, Id>, EntityCrudDai<E> {

    Id save(String coreId, E entity) throws BaseVaselineServerException;

    List<Id> saveBatch(String coreId, List<E> entities) throws BaseVaselineServerException;

    void update(String coreId, E entity) throws BaseVaselineServerException;

    void updateBatch(String coreId, List<E> entities) throws BaseVaselineServerException;

    void delete(String coreId, E entity) throws BaseVaselineServerException;

    void deleteBatch(String coreId, List<E> entities) throws BaseVaselineServerException;

    void delete(String coreId, Id id) throws BaseVaselineServerException;

    @Override
    CrudDao getDaoFor(String coreId) throws BaseVaselineServerException;
}
