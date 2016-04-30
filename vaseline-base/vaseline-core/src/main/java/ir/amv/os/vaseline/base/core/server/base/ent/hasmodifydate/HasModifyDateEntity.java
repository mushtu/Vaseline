package ir.amv.os.vaseline.base.core.server.base.ent.hasmodifydate;

import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by AMV on 2/9/2016.
 */
public interface HasModifyDateEntity<Id extends Serializable>
        extends Identifiable<Id> {

    Date getModifyDate();
    void setModifyDate(Date modifyDate);
}
