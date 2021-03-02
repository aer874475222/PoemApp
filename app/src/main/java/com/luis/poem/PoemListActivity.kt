package com.luis.poem

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.czq.kotlin_arch.basePage.paging.BasePagingActivity
import com.czq.kotlin_arch.common.util.AssetUtil
import com.czq.kotlin_arch.common.util.ext.dp
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.luis.poem.R
import com.luis.poem.bean.PoemBean
import com.luis.poem.greendao.ImportDB
import kotlinx.android.synthetic.main.activity_poemlist.*


class PoemListActivity : BasePagingActivity<PoemListContract.IPresenter>(), PoemListContract.IView {

    @SuppressLint("CheckResult")
    override fun registItemBinder() {
        multiAdapter.register(PoemViewbinder())
    }

    override fun createPresenter(): PoemListContract.IPresenter {
        return PoemListPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_poemlist
    }

    override fun initView() {
        super.initView()
        importPoem()

        val layoutManager = LinearLayoutManager(this)
        pagingRecycleview.layoutManager = layoutManager
        pagingRecycleview.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                //val position = parent.getChildAdapterPosition(view)
                outRect.top = 15.dp
            }
        })
    }

    fun parseJArrayIntoDb(){
        var jsonStr = AssetUtil.getStringFromFile(this@PoemListActivity, "guwen0-1000.json")
        val  strresult = jsonStr?.replace(Regex("\\}\\{"),"},{")?.replace(Regex("\\}\\n\\s*\\r\\{"),"},{")
        val userJson= "[$strresult]"
        val gson= Gson()
        val mList: MutableList<PoemBean> = mutableListOf()
        val arry: JsonArray = JsonParser().parse(userJson).getAsJsonArray()
        for (jsonElement in arry) {
            mList.add(gson.fromJson(jsonElement, PoemBean::class.java))
        }

        for (value in mList) {
            if (GreenDaoManager.getInstance(this@PoemListActivity).findPoemByOid(value._id.`$oid`).isEmpty()) {
                GreenDaoManager.getInstance(this@PoemListActivity).addPoem(value)
            }
        }
        var osize = GreenDaoManager.getInstance(this@PoemListActivity).queryAllDatas().size;
        println( "size[${osize}]")
    }


    var importDB: ImportDB? = null
    fun importPoem(){
        importDB = ImportDB(this);
        importDB!!.openDatabase();
    }

    override fun onDestroy() {
        super.onDestroy()
        importDB!!.closeDatabase();

    }


    override fun needTitle(): Boolean {
        return false
    }

}