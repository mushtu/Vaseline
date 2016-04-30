package ir.amv.os.vaseline.bpm.api.server.base.entity;

import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;

import java.io.Serializable;

/**
 * Created by AMV on 11/10/2015.
 */
public interface IBaseBpmEntity<Id extends Serializable> extends Identifiable<Id> {

    Boolean getInProgress();
    void setInProgress(Boolean inProgress);
}
