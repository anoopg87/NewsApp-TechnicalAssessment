package com.anooplab.newsapp.factslist.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.anooplab.newsapp.R
import com.anooplab.newsapp.factslist.model.FactItemUiModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fact_item_view.view.*
/***
 * Custom View for showing the Facts item in [FactsListAdapter]
 *
 */
class FactItemView : LinearLayout {
    private var factItem: FactItemUiModel? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        View.inflate(context, R.layout.fact_item_view, this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    fun setFact(factItem: FactItemUiModel) {
        this.factItem = factItem
        title.text = factItem.title
        description.text = factItem.description
        if (factItem.imageHref != null) {
            Glide.with(this).load(factItem.imageHref).into(image)
            image.visibility = View.VISIBLE
        } else {
            image.visibility = View.GONE
        }
    }
}