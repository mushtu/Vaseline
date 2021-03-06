package ir.amv.os.vaseline.bpm.api.base.impl.server.base;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.dai.BaseCrudDaiHelper;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.CrudDao;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.bpm.api.server.api.IVaselineBpmApi;
import ir.amv.os.vaseline.bpm.api.server.base.api.crud.IBaseBpmCrudApi;

import java.io.Serializable;

/**
 * Created by AMV on 3/2/2016.
 */
public class BaseBpmCrudApiImplHelper {

    private BaseBpmCrudApiImplHelper() {
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> Id saveBpm(
            IBaseBpmCrudApi<E, D, Id> api,
            IVaselineBpmApi bpmApi,
            CrudDao<E, D, Id> dao,
            E entity, String taskId)
            throws BaseVaselineServerException {
        Id id = BaseCrudDaiHelper.save(api, dao, entity);
        String idVariableName = api.getIdVariableName();
        bpmApi.setVariable(taskId, idVariableName, id);
        return id;
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> String getIdVariableName(
            IBaseBpmCrudApi<E, D, Id> api) {
        Class<E> entityClass = api.getEntityClass();
        String simpleName = entityClass.getSimpleName();
        return simpleName + "Id";
    }
}
