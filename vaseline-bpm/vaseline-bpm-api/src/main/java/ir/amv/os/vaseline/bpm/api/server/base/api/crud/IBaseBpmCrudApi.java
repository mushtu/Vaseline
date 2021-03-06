package ir.amv.os.vaseline.bpm.api.server.base.api.crud;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dai.CrudDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

/**
 * Created by AMV on 11/9/2015.
 */
public interface IBaseBpmCrudApi<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends CrudDai<E, D, Id> {

    Id saveBpm(E entity, String taskId) throws BaseVaselineServerException;

    String getIdVariableName();
}
