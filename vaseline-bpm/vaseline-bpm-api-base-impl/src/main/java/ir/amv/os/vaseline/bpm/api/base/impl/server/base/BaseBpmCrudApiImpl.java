package ir.amv.os.vaseline.bpm.api.base.impl.server.base;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.dai.BaseCrudDai;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.CrudDao;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.bpm.api.server.api.IVaselineBpmApi;
import ir.amv.os.vaseline.bpm.api.server.base.api.crud.IBaseBpmCrudApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by AMV on 3/2/2016.
 */
public class BaseBpmCrudApiImpl<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends CrudDao<E, D, Id>>
        extends BaseCrudDai<E, D, Id, DAO>
        implements IBaseBpmCrudApi<E, D, Id> {

    protected IVaselineBpmApi bpmApi;

    @Override
    public Id saveBpm(E entity, String taskId) throws BaseVaselineServerException {
        return BaseBpmCrudApiImplHelper.saveBpm(this, bpmApi, dao, entity, taskId);
    }

    @Override
    public String getIdVariableName() {
        return BaseBpmCrudApiImplHelper.getIdVariableName(this);
    }

    @Autowired
    public void setBpmApi(IVaselineBpmApi bpmApi) {
        this.bpmApi = bpmApi;
    }
}
