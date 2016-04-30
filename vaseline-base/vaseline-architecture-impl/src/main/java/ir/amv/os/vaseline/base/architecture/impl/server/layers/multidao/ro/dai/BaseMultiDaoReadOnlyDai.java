package ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.ro.dai;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.dai.BaseEntityReadOnlyDai;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.ReadOnlyDao;
import ir.amv.os.vaseline.base.architecture.server.layers.multidao.ro.MultiDaoReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public abstract class BaseMultiDaoReadOnlyDai<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends ReadOnlyDao<E, D, Id>>
        extends BaseEntityReadOnlyDai<E, Id>
        implements MultiDaoReadOnlyDai<E, D, Id> {

    protected List<DAO> daoList;

    @Override
    @Transactional(readOnly =  true)
    public E getById(String coreId, Id id) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyDaiHelper.getById(this, coreId, id);
    }

    @Override
    @Transactional(readOnly =  true)
    public Long countAll(String coreId) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyDaiHelper.countAll(this, coreId);
    }

    @Override
    @Transactional(readOnly =  true)
    public List<E> getAll(String coreId) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyDaiHelper.getAll(this, coreId);
    }

    @Override
    @Transactional(readOnly =  true)
    public List<E> getAll(String coreId, PagingDto pagingDto) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyDaiHelper.getAll(this, coreId, pagingDto);
    }

    @Override
    @Transactional(readOnly =  true)
    public Long countByExample(String coreId, D example) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyDaiHelper.countByExample(this, coreId, example);
    }

    @Override
    @Transactional(readOnly =  true)
    public List<E> searchByExample(String coreId, D example) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyDaiHelper.searchByExample(this, coreId, example);
    }

    @Override
    @Transactional(readOnly =  true)
    public List<E> searchByExample(String coreId, D example, PagingDto pagingDto) throws BaseVaselineServerException {
        return BaseMultiDaoReadOnlyDaiHelper.searchByExample(this, coreId, example, pagingDto);
    }

    @Override
    public abstract DAO getDaoFor(String coreId) throws BaseVaselineServerException;

    @Autowired
    public void setDaoList(List<DAO> daoList) {
        this.daoList = daoList;
    }
}
