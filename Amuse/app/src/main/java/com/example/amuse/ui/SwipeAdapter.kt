package com.example.amuse.ui
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_koloda.view.*

class SwipeAdapter(private val context: Context, val data: ArrayList<Int>?) :
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
        val holder: DataViewHolder
        var view = convertView

        if (view == null) {
            view = android.view.LayoutInflater.from(parent.context).inflate(R.layout.item_koloda, parent, false)
            holder = DataViewHolder(view)
            view?.tag = holder
        } else {
            holder = view.tag as DataViewHolder
        }

        holder.bindData(context, getItem(position))

        return view
    }

    /**
     * Static view items holder
     */
    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var picture = view.kolodaImage

        internal fun bindData(context: Context, data: Int) {
            val transforms = RequestOptions().transforms(CenterCrop(), RoundedCorners(20))
            Glide.with(context)
                .load(data)
                .apply(transforms)
                .into(picture)
        }

    }
}
