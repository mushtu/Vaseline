package ir.amv.os.vaseline.security.authorization.api.shared.base.api.crud;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dai.CrudDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.security.authorization.api.shared.base.api.ro.IBaseSecuredReadOnlyApi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IBaseSecuredCrudApi<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends CrudDai<E, D, Id>, IBaseSecuredReadOnlyApi<E, D, Id> {

    String BASE_OP_CREATE = "create";
    String BASE_OP_UPDATE = "update";
    String BASE_OP_DELETE = "delete";

    Id saveNotSecured(E entity) throws BaseVaselineServerException;

    List<Id> saveBatchNotSecured(List<E> entities) throws BaseVaselineServerException;

    void updateNotSecured(E entity) throws BaseVaselineServerException;

    void updateBatchNotSecured(List<E> entities) throws BaseVaselineServerException;

    void deleteNotSecured(E entity) throws BaseVaselineServerException;

    void deleteBatchNotSecured(List<E> entities) throws BaseVaselineServerException;

    void deleteNotSecured(Id id) throws BaseVaselineServerException;

    String getCreateOperationTreeName();

    String getUpdateOperationTreeName();

    String getDeleteOperationTreeName();
}
