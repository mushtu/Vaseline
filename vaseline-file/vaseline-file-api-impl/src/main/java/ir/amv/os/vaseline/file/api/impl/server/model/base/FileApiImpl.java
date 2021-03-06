package ir.amv.os.vaseline.file.api.impl.server.model.base;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.multidao.crud.dai.BaseMultiDaoCrudDai;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.file.api.server.daoregisterer.IFileDaoRegisterer;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.file.api.server.model.base.IFileDao;
import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;
import ir.amv.os.vaseline.file.api.shared.model.base.IFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
@Component
public class FileApiImpl
        extends BaseMultiDaoCrudDai<IFileEntity, IFileDto, Long, IFileDao>
        implements IFileApi {

    private List<IFileDaoRegisterer> daoRegisterers;

    @Override
    public Long uploadFile(String coreId, IFileEntity fileEntity, InputStream inputStream) throws BaseVaselineServerException {
        preSave(fileEntity);
        try {
            Long fileId = getDaoFor(coreId).saveFileUsingStream(fileEntity, inputStream);
            postSave(fileEntity);
            return fileId;
        } catch (Exception e) {
            throw new BaseVaselineServerException(e);
        }
    }

    @Override
    public void writeFileContent(String coreId, Long fileId, OutputStream outputStream) throws BaseVaselineServerException {
        try {
            getDaoFor(coreId).writeFileContent(fileId, outputStream);
        } catch (Exception e) {
            throw new BaseVaselineServerException(e);
        }
    }

    @Override
    public IFileEntity createFile(String coreId) throws BaseVaselineServerException {
        return getDaoFor(coreId).createFile();
    }

    @Override
    public IFileDao getDaoFor(String coreId) throws BaseVaselineServerException {
        for (IFileDaoRegisterer daoRegisterer : daoRegisterers) {
            IFileDao daoFor = daoRegisterer.getDaoFor(coreId, daoList);
            if (daoFor != null) {
                return daoFor;
            }
        }
        return null;
    }

    @Autowired
    public void setDaoRegisterers(List<IFileDaoRegisterer> daoRegisterers) {
        this.daoRegisterers = daoRegisterers;
        Collections.sort(daoRegisterers, new Comparator<IFileDaoRegisterer>() {
            @Override
            public int compare(IFileDaoRegisterer o1, IFileDaoRegisterer o2) {
                return o2.priority().compareTo(o1.priority()); // sort descending
            }
        });
    }

}
