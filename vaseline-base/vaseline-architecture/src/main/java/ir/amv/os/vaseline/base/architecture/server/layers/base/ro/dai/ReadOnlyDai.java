package ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dai;

import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.dai.EntityReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface ReadOnlyDai<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends EntityReadOnlyDai<E> {

    E getById(Id id) throws BaseVaselineServerException;

    Long countAll() throws BaseVaselineServerException;
    List<E> getAll() throws BaseVaselineServerException;
    List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException;

    Long countByExample(D example) throws BaseVaselineServerException;
    List<E> searchByExample(D example) throws BaseVaselineServerException;
    List<E> searchByExample(D example, PagingDto pagingDto)
            throws BaseVaselineServerException;

}
