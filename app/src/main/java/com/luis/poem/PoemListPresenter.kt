package com.luis.poem

import GreenDaoManager
import android.annotation.SuppressLint
import com.czq.kotlin_arch.basePage.base.BasePagingPrensenterImpl
import com.czq.kotlin_arch.paging.PagingStrategy
import com.czq.kotlin_arch.paging.offset.OffsetPageInfo
import com.czq.kotlin_arch.paging.offset.OffsetStrategy
import com.luis.poem.PoemListContract


class PoemListPresenter(override val mView: PoemListContract.IView) :
    BasePagingPrensenterImpl(mView),
    PoemListContract.IPresenter {
    val PAGE_SIZE = 20;
    val greenDao: GreenDaoManager by lazy {
        GreenDaoManager.getInstance(mView.getContext())
    }
    override fun getPagingStrategy(): PagingStrategy? {
        return OffsetStrategy(PAGE_SIZE, "id")
    }

    @SuppressLint("CheckResult")
    override fun onLoadData(pagingStrategy: PagingStrategy?) {
        var temp:List<*>
        val pageInfo = pagingStrategy!!.getPageInfo() as OffsetPageInfo
        if (pageInfo.isFirstPage()) {
            temp =greenDao.query20Datas(pageInfo.pageNum, pageInfo.pageSize)
            datasource.clear()
            pagingList.clear()
        }else {
            temp =greenDao.query20Datas(pageInfo.pageNum, pageInfo.pageSize)
        }

        pagingList.addAll(temp)//用于计算分页的数据
        datasource.addAll(temp)//真实显示在列表上的数据
        loadSuccess(temp)


    }
}