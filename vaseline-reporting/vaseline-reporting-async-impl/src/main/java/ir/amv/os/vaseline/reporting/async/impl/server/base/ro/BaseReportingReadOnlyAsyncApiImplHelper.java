package ir.amv.os.vaseline.reporting.async.impl.server.base.ro;

import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dai.ReadOnlyDai;
import ir.amv.os.vaseline.base.core.server.base.ent.Identifiable;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.impl.BaseDoubleParameterCallbackImpl;
import ir.amv.os.vaseline.base.core.shared.util.callback.impl.CachingCallback;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.reporting.api.server.model.CreateReportRequestServer;
import ir.amv.os.vaseline.reporting.api.server.model.ICreateReportApi;
import ir.amv.os.vaseline.reporting.async.impl.server.base.parent.BaseReportingAsyncApiImplHelper;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseReportingReadOnlyAsyncApiImplHelper {

    private BaseReportingReadOnlyAsyncApiImplHelper() {
    }

    public static <E extends Identifiable<Id>, D extends IBaseDto<Id>, Id extends Serializable> Long reportByExample(
            final ReadOnlyDai<E, D, Id> api,
            CreateReportRequestServer request,
            final D example,
            ICreateReportApi createReportApi,
            IAuthenticationApi authenticationApi,
            IFileApi fileApi,
            String reportFileCategory)
            throws BaseVaselineServerException {
        return BaseReportingAsyncApiImplHelper.genericReport(api, request, createReportApi, authenticationApi, fileApi, reportFileCategory,
                new CachingCallback<Integer>() {
                    @Override
                    public Integer fetchValue() {
                        try {
                            return api.getProxy(ReadOnlyDai.class).countByExample(example).intValue();
                        } catch (BaseVaselineServerException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }, new BaseDoubleParameterCallbackImpl<IBaseCallback<List<E>, Void>, PagingDto, Void>() {
                    @Override
                    public void onSuccess(IBaseCallback<List<E>, Void> firstParam, PagingDto secondParameter) {
                        try {
                            firstParam.onSuccess(api.getProxy(ReadOnlyDai.class).searchByExample(example, secondParameter));
                        } catch (BaseVaselineServerException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
