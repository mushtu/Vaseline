package ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.CrudDao;
import ir.amv.os.vaseline.security.authentication.spring.impl.shared.dto.model.user.BaseUserDto;

/**
 * Created by AMV on 2/16/2016.
 */
public interface IBaseUserDao
        extends CrudDao<BaseUserEntity, BaseUserDto, Long> {
}
