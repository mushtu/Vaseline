package ir.amv.os.vaseline.security.authorization.api.shared.base.dao.crud;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.security.authorization.api.shared.base.dao.ro.IBaseSecuredReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.api.shared.criteria.ISecurityCriteria;

import java.io.Serializable;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IBaseSecuredCrudDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, SecurityCriteria extends ISecurityCriteria>
        extends IBaseCrudDao<E, D, Id>, IBaseSecuredReadOnlyDao<E, D, Id, SecurityCriteria> {

}