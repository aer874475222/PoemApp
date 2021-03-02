package com.luis.poem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.luis.poem.R
import com.luis.poem.bean.PoemBean
import kotlinx.android.synthetic.main.item_poem.view.*


class PoemViewbinder : ItemViewBinder<PoemBean, PoemViewbinder.ViewHolder>() {

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): PoemViewbinder.ViewHolder =
        ViewHolder(inflater.inflate(R.layout.item_poem, parent, false))

    override fun onBindViewHolder(holder: PoemViewbinder.ViewHolder, itemData: PoemBean) {
        holder.itemView.tvTitle.text = "标题：${itemData?.title.trim()}"
        holder.itemView.tvWriter.text = "作者：${itemData?.writer.trim()}"
        holder.itemView.tvContent.text = "内容：${itemData?.content.trim()}"
       // println(itemData?.content.trim())

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


}