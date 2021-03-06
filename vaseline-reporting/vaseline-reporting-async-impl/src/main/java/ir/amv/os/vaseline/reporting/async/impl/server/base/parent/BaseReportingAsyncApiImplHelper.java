package ir.amv.os.vaseline.reporting.async.impl.server.base.parent;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.ReadOnlyDao;
import ir.amv.os.vaseline.base.architecture.server.layers.parent.dai.DataAccessInterface;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.base.core.shared.util.date.DateUtil;
import ir.amv.os.vaseline.base.core.shared.util.pager.impl.DefaultAsyncListPager;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;
import ir.amv.os.vaseline.reporting.api.server.datasource.BaseBeansDataSource;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.api.server.requestfiller.IBaseReportRequestFiller;
import ir.amv.os.vaseline.reporting.async.api.server.base.parent.IBaseReportingAsyncApi;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by AMV on 2/13/2016.
 */
public class BaseReportingAsyncApiImplHelper {

    private static List<IBaseReportRequestFiller> reportRequestFillers = new ArrayList<IBaseReportRequestFiller>();

    private BaseReportingAsyncApiImplHelper() {
    }

    public static <E> Long genericReport(
            DataAccessInterface api,
            CreateReportRequestServer request,
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory,
            IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback,
            IBaseDoubleParameterCallback<IBaseCallback<List<E>, Void>, PagingDto, Void> loadDataCallback)
            throws BaseVaselineServerException {
        request = fillRepReq(request, api, authenticationApi);
        final DefaultAsyncListPager<E> asyncListPager = new DefaultAsyncListPager<E>();
        asyncListPager.setCountDataCallback(countDataCallback);
        asyncListPager.setLoadDataCallback(loadDataCallback);
        BaseBeansDataSource<E> dataSource = new BaseBeansDataSource<E>(asyncListPager);
        return doGenerateReport(createReportApi, authenticationApi, fileApi, reportFileCategory, request, dataSource);
    }

    private static <E> Long doGenerateReport(
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory,
            CreateReportRequestServer request,
            BaseBeansDataSource<E> dataSource) throws BaseVaselineServerException {
        try {
            File repResTmpFile = File.createTempFile("reportOutput", ".tmp");
            Future<Void> voidFuture = createReportApi.generateReport(request,
                    new FileOutputStream(repResTmpFile),
                    dataSource);
            voidFuture.get();
            IFileEntity fileEntity = fileApi.createFile(reportFileCategory);
            fileEntity.setCategory(reportFileCategory);
            fileEntity.setContentType(request.getOutputType()
                    .getContentType());
            fileEntity.setFileName(getFileNameFor(authenticationApi, request));
            fileEntity.setOwner(authenticationApi.getCurrentUsername());
            InputStream inputStream = new FileInputStream(repResTmpFile);
            fileEntity.setFileSize((long) inputStream.available());
            Long fileId = fileApi.uploadFile(reportFileCategory, fileEntity, inputStream);
            inputStream.close();
            return fileId;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <Id extends Serializable, DAO extends ReadOnlyDao<?, ?, Id>> String getFileNameFor(
            IAuthenticationApi authenticationApi,
            CreateReportRequestServer request) throws BaseVaselineServerException {
        StringBuffer fileName = new StringBuffer();
        fileName.append(authenticationApi.getCurrentUsername());
        fileName.append("-");
        fileName.append(DateUtil.toString(DateUtil.newJalaliCalendar()));
        fileName.append(".");
        fileName.append(request.getOutputType().fileSuffix());
        return fileName.toString();
    }

    private static CreateReportRequestServer fillRepReq(CreateReportRequestServer request, DataAccessInterface api, IAuthenticationApi authenticationApi) throws BaseVaselineServerException {
        String currentUsername = authenticationApi.getCurrentUsername();
        if (request.getArgsMap() != null) {
            request.setArgsMap(new HashMap<String, Object>());
        }
        request.getArgsMap().put("currentUser", currentUsername);
        for (IBaseReportRequestFiller reportRequestFiller : reportRequestFillers) {
            request = reportRequestFiller.fillReportRequest(request, api);
        }
        return request;
    }

    public static <E> String getReportFileCategory(IBaseReportingAsyncApi<E> api, CreateReportRequestServer request) {
        return "report";
    }

    public static void addReportRequestFiller(IBaseReportRequestFiller reportRequestFiller) {
        reportRequestFillers.add(reportRequestFiller);
    }
}
