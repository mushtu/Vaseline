package ir.amv.os.vaseline.security.authorization.api.shared.base.dao.ro;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.ReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.security.authorization.api.shared.criteria.ISecurityCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IBaseSecuredReadOnlyDao<E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable, SecurityCriteria extends ISecurityCriteria>
        extends ReadOnlyDao<E, D, Id> {

    E getById(Id id, SecurityCriteria securityCriteria);

    E getByIdDetached(Id id, SecurityCriteria securityCriteria);

    Long countAll(SecurityCriteria securityCriteria);

    List<E> getAll(SecurityCriteria securityCriteria);

    List<E> getAll(PagingDto pagingDto, SecurityCriteria securityCriteria);

    Long countByExample(D example, SecurityCriteria securityCriteria);

    List<E> searchByExample(D example, SecurityCriteria securityCriteria);

    List<E> searchByExample(D example, PagingDto pagingDto, SecurityCriteria securityCriteria);

}
