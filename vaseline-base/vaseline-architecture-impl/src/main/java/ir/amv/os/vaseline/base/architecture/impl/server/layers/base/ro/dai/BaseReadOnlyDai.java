package ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.dai;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.dai.BaseEntityReadOnlyDai;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dai.ReadOnlyDai;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.ReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseReadOnlyDai<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends ReadOnlyDao<E, D, Id>>
        extends BaseEntityReadOnlyDai<E, Id>
        implements ReadOnlyDai<E, D, Id> {

    protected DAO dao;

    @Override
    @Transactional(readOnly = true)
    public E getById(Id id) throws BaseVaselineServerException {
        return BaseReadOnlyDaiHelper.getById(this, getDao(), id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAll() throws BaseVaselineServerException {
        return BaseReadOnlyDaiHelper.countAll(this, getDao());
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> getAll() throws BaseVaselineServerException {
        return BaseReadOnlyDaiHelper.getAll(this, getDao());
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        return BaseReadOnlyDaiHelper.getAll(this, getDao(), pagingDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByExample(D example) throws BaseVaselineServerException {
        return BaseReadOnlyDaiHelper.countByExample(this, getDao(), example);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> searchByExample(D example) throws BaseVaselineServerException {
        return BaseReadOnlyDaiHelper.searchByExample(this, getDao(), example);
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineServerException {
        return BaseReadOnlyDaiHelper.searchByExample(this, getDao(), example, pagingDto);
    }

    @Autowired
    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public DAO getDao() {
        return dao;
    }
}
