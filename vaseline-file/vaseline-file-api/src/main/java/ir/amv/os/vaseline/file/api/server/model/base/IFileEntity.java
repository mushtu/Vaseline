package ir.amv.os.vaseline.file.api.server.model.base;

import ir.amv.os.vaseline.base.core.server.base.ent.hascreatedate.HasCreateDateEntity;
import ir.amv.os.vaseline.base.core.server.base.ent.hasmodifydate.HasModifyDateEntity;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileEntity extends HasCreateDateEntity<Long>, HasModifyDateEntity<Long> {

    String getFileName();

    void setFileName(String fileName);

    Long getFileSize();

    void setFileSize(Long fileSize);

    String getOwner();

    void setOwner(String owner);

    String getCategory();

    void setCategory(String category);

    String getContentType();

    void setContentType(String contentType);
}
