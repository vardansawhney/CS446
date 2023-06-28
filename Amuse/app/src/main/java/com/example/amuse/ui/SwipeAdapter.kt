package com.example.amuse.ui
import com.example.amuse.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter


class SwipeAdapter(private val context: Context, val data: ArrayList<Int>) :
    BaseAdapter() {

    private val dataList = mutableListOf<Int>()

    init {
        if (data != null){
            dataList.addAll(data)
        }
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Int {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
//        val holder: DataViewHolder
//        var view = convertView
//
//        if (view == null) {
//            view = android.view.LayoutInflater.from(parent.context).inflate(R.layout.item_koloda, parent, false)
//            holder = DataViewHolder(view)
//            view?.tag = holder
//        } else {
//            holder = view.tag as DataViewHolder
//        }
//
//        holder.bindData(context, getItem(position))
//
//        return view
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        } else {
            view = convertView
        }
        return view
    }

//    /**
//     * Static view items holder
//     */
//    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var picture = view.kolodaImage
//
//        internal fun bindData(context: Context, data: Int) {
//            val transforms = RequestOptions().transforms(CenterCrop(), RoundedCorners(20))
//            Glide.with(context)
//                .load(data)
//                .apply(transforms)
//                .into(picture)
//        }
//
//    }
}
