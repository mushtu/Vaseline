package ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.dai;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.dai.BaseDai;
import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.dai.EntityReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.util.reflection.ReflectionUtil;

import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseEntityReadOnlyDai<E extends Identifiable<Id>, Id extends Serializable>
        extends BaseDai
        implements EntityReadOnlyDai<E> {

    private Class<E> entityClass;

    public BaseEntityReadOnlyDai() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            entityClass = (Class<E>) genericArgumentClasses[0];
        }
    }

    @Override
    public void postGet(E entity) throws BaseVaselineServerException {
        BaseEntityReadOnlyDaiHelper.postGet(entity);
    }

    @Override
    public Class<E> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }
}
