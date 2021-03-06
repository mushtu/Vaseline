package ir.amv.os.vaseline.reporting.api.server.requestfiller;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.dai.DataAccessInterface;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;

/**
 * Created by AMV on 2/22/2016.
 */
public interface IBaseReportRequestFiller {

    CreateReportRequestServer fillReportRequest(CreateReportRequestServer reportRequestServer, DataAccessInterface api);
}
