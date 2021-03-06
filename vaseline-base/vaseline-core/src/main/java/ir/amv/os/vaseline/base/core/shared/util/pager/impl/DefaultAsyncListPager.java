package ir.amv.os.vaseline.base.core.shared.util.pager.impl;

import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseDoubleParameterCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.impl.BaseCallbackImpl;
import ir.amv.os.vaseline.base.core.shared.util.pager.IBaseAsyncListPager;

import java.util.List;

public class DefaultAsyncListPager<D> implements IBaseAsyncListPager<D> {

    private int totalCount = -1;
    private int pageSize;
    private int pageNo;
    private List<D> currentPage;
    private IBaseDoubleParameterCallback<IBaseCallback<List<D>, Void>, PagingDto, Void> loadDataCallback;
    private IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback;
    private boolean initialized = false;

    @Override
    public int getTotalCount() {
        return totalCount;
    }

    @Override
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        pageNo++;
        refreshCurrentPage();
        initialized = true;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getCurrentPageNo() {
        return pageNo;
    }

    @Override
    public void setCurrentPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public List<D> getCurrentPage() {
        return currentPage;
    }

    @Override
    public void reset() {
        pageNo = -1;
        count();
    }

    @Override
    public boolean nextPage() {
        if (totalCount == -1) {
            count();
        }
        pageNo++;
        if ((pageNo * pageSize) < totalCount) {
            loadData();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean previousPage() {
        if (totalCount == -1) {
            count();
        }
        pageNo--;
        if (pageNo >= 0) {
            loadData();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void refreshCurrentPage() {
        if (totalCount == -1) {
            count();
        }
        loadData();
    }

    @Override
    public IBaseDoubleParameterCallback<IBaseCallback<List<D>, Void>, PagingDto, Void> getLoadDataCallback() {
        return loadDataCallback;
    }

    @Override
    public void setLoadDataCallback(IBaseDoubleParameterCallback<IBaseCallback<List<D>, Void>, PagingDto, Void> loadDataCallback) {
        this.loadDataCallback = loadDataCallback;
    }

    @Override
    public IBaseCallback<IBaseCallback<Integer, Void>, Void> getCountDataCallback() {
        return countDataCallback;
    }

    @Override
    public void setCountDataCallback(IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback) {
        this.countDataCallback = countDataCallback;
    }

    private void loadData() {
        loadDataCallback.onSuccess(new BaseCallbackImpl<List<D>, Void>() {
            @Override
            public void onSuccess(List<D> result) {
                currentPage = result;
            }
        }, new PagingDto(null, getCurrentPageNo(), getPageSize()));
    }

    private void count() {
        countDataCallback.onSuccess(new BaseCallbackImpl<Integer, Void>() {
            @Override
            public void onSuccess(Integer result) {
                setTotalCount(result);
            }
        });
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }
}
