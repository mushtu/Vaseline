package ir.amv.os.vaseline.base.core.server.base.ent;

import java.io.Serializable;

public interface Identifiable<Id extends Serializable> {

	Id getId();
	void setId(Id id);

}
