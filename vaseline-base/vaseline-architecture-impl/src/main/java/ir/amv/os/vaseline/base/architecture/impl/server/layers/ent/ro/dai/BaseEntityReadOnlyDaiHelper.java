package ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.dai;

import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseEntityReadOnlyDaiHelper {

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void postGet(
            E entity)
            throws BaseVaselineServerException {
    }
}
