package ir.amv.os.vaseline.ws.rest.server.base.ro.impl;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.service.ReadOnlyDataService;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.rest.server.base.ro.ReadOnlyResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by AMV on 2/13/2016.
 */
public class ReadOnlyResourceImpl<D extends IBaseDto<Id>, Id extends Serializable, S extends ReadOnlyDataService<D, Id>>
        implements ReadOnlyResource<D, Id> {

    protected S service;

    @Override
    public D getById(Id id) throws BaseVaselineClientException {
        return service.getById(id);
    }

    @Override
    public Long countAll() throws BaseVaselineClientException {
        return service.countAll();
    }

    @Override
    public List<D> getAll() throws BaseVaselineClientException {
        return service.getAll();
    }

    @Override
    public List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException {
        return service.getAll(pagingDto);
    }

    @Override
    public Long countByExample(D example) throws BaseVaselineClientException {
        return service.countByExample(example);
    }

    @Override
    public List<D> searchByExample(D example) throws BaseVaselineClientException {
        return service.searchByExample(example);
    }

    @Override
    public List<D> searchByExample(Map<String, Object> map) throws BaseVaselineClientException {
        return service.searchByExample((D) map.get("example"), (PagingDto) map.get("pagingDto"));
    }

    @Autowired
    public void setService(S service) {
        this.service = service;
    }
}
