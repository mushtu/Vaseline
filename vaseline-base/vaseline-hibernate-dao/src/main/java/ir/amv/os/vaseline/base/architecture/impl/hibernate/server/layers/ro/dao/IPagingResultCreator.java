package ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao;

import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseReturningCallback;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by AMV on 2/24/2016.
 */
public interface IPagingResultCreator {
    <E extends Identifiable<?>> List<E> getPagingResult(BaseReadOnlyHibernateDaoImpl<E, ?, ?> dao, IBaseReturningCallback<DetachedCriteria> createCriteriaCallback, PagingDto pagingDto);

}
