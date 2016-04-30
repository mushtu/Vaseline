package ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.crud.dai;

import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.ent.hascreatedate.HasCreateDateEntity;
import ir.amv.os.vaseline.base.core.server.base.ent.hasmodifydate.HasModifyDateEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.util.date.DateUtil;

import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseEntityCrudDaiHelper {

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void preSave(
            E entity)
            throws BaseVaselineServerException {
        if (entity instanceof HasCreateDateEntity<?>) {
            HasCreateDateEntity<?> hasCreateDateEntity = (HasCreateDateEntity<?>) entity;
            hasCreateDateEntity.setCreateDate(DateUtil.newDate());
        }
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void postSave(
            E entity)
            throws BaseVaselineServerException {
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void preUpdate(
            E entity)
            throws BaseVaselineServerException {
        if (entity instanceof HasModifyDateEntity<?>) {
            HasModifyDateEntity<?> hasModifyDateEntity = (HasModifyDateEntity<?>) entity;
            hasModifyDateEntity.setModifyDate(DateUtil.newDate());
        }
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void postUpdate(
            E entity)
            throws BaseVaselineServerException {
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void preDelete(
            E entity)
            throws BaseVaselineServerException {
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> void postDelete(
            E entity)
            throws BaseVaselineServerException {
    }

}
