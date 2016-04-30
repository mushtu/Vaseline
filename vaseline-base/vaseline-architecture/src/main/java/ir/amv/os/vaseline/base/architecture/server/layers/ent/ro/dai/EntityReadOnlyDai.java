package ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.dai;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.dai.DataAccessInterface;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;

/**
 * Created by AMV on 2/9/2016.
 */
public interface EntityReadOnlyDai<E extends Identifiable<?>> extends DataAccessInterface {

    void postGet(E entity) throws BaseVaselineServerException;

    Class<E> getEntityClass();
}
