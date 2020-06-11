package com.lamonjush.singlepagestepdowntutorial

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lamonjush.singlepagestepdowntutorial.adapter.Adapter
import com.lamonjush.singlepagestepdowntutorial.model.Tut

class SinglePageStepDownTutorial(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var recyclerView: RecyclerView? = null

    private var buttonBackgroundColor: Int? = null
    private var buttonTextColor: Int? = null
    private var titleTextColor: Int? = null
    private var blockedTitleTextColor: Int? = null

    private val tuts = arrayListOf<Tut>()

    private var listener: SinglePageStepDownTutorialListener? = null

    constructor(context: Context?) : this(context, null, 0, 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : this(context, attrs, 0, 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : this(context, attrs, defStyleAttr, 0)

    init {
        orientation = VERTICAL
        context?.let {
            val typedArray =
                it.theme.obtainStyledAttributes(attrs, R.styleable.SinglePageStepDownTutorial, 0, 0)
            try {
                buttonBackgroundColor = typedArray.getColor(
                    R.styleable.SinglePageStepDownTutorial_buttonBackgroundColor,
                    ContextCompat.getColor(it, R.color.colorAccent)
                )

                buttonTextColor = typedArray.getColor(
                    R.styleable.SinglePageStepDownTutorial_buttonTextColor,
                    ContextCompat.getColor(it, R.color.colorWhite)
                )

                titleTextColor = typedArray.getColor(
                    R.styleable.SinglePageStepDownTutorial_titleTextColor,
                    ContextCompat.getColor(it, R.color.colorBlack)
                )

                blockedTitleTextColor = typedArray.getColor(
                    R.styleable.SinglePageStepDownTutorial_blockedTitleTextColor,
                    ContextCompat.getColor(it, R.color.colorForeground)
                )
            } finally {
                typedArray.recycle()
            }

            recyclerView = RecyclerView(it)
            recyclerView?.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            recyclerView?.layoutManager = LinearLayoutManager(it)
            addView(recyclerView!!)
        }
    }

    fun setUpListener(listener: SinglePageStepDownTutorialListener?) {
        this.listener = listener
    }

    fun addItem(title: String, description: String, buttonText: String) {
        recyclerView?.let {
            val tut = Tut(
                tuts.size + 1,
                title,
                description,
                buttonText,
                buttonBackgroundColor!!,
                blockedTitleTextColor!!,
                titleTextColor!!,
                buttonTextColor!!
            )
            tuts.add(tut)
        }
    }

    private fun refresh() {
        tuts.forEach {
            it.showing = false
        }
    }

    fun show() {
        refresh()
        if (tuts.size > 1) {
            tuts[0].showing = true
            val adapter = Adapter(context, tuts)
            adapter.setUpListener(object : SinglePageStepDownTutorialListener {
                override fun onCompleteStep(stepNumber: Int) {
                    refresh()
                    if (stepNumber < tuts.size - 1) {
                        // not finished yet
                        tuts[stepNumber + 1].showing = true
                        adapter.notifyDataSetChanged()
                        listener?.onCompleteStep(stepNumber + 1)
                    } else if (stepNumber == tuts.size - 1) {
                        //finish
                        refresh()
                        adapter.notifyDataSetChanged()
                        listener?.onCompleteStep(stepNumber + 1)
                    }

                }

            })
            recyclerView?.adapter = adapter
        }
    }
}