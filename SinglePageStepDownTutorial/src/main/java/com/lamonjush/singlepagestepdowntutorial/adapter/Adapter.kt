package com.lamonjush.singlepagestepdowntutorial.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lamonjush.singlepagestepdowntutorial.R
import com.lamonjush.singlepagestepdowntutorial.SinglePageStepDownTutorialListener
import com.lamonjush.singlepagestepdowntutorial.model.Tut

class Adapter(private val context: Context?, private val tuts: ArrayList<Tut>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var listener: SinglePageStepDownTutorialListener? = null

    fun setUpListener(listener: SinglePageStepDownTutorialListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(
            R.layout.list_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount() = tuts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        context?.let {
            val tut = tuts[position]
            holder.bind(position, tut)
        }
    }

    //View Holder
    inner class ViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        fun bind(pos: Int, obj: Tut?) {
            obj?.let {
                val numberHolder: FrameLayout = view.findViewById(R.id.tutNumberHolder)
                /*numberHolder.foreground = it.getNumberOffStateForegroundDrawable()*/

                val number: TextView = view.findViewById(R.id.tutNumberTextView)
                number.background = it.getNumberDrawable()
                number.text = it.number.toString()
                number.setTextColor(it.buttonTextColor)

                val title: TextView = view.findViewById(R.id.tutTitleTextView)
                title.text = it.title

                val description: TextView = view.findViewById(R.id.tutDesTextView)
                description.text = it.description
                description.setTextColor(it.textColor)

                val button: Button = view.findViewById(R.id.tutContinueButton)
                button.setBackgroundColor(it.ancientColor)
                button.text = it.buttonText
                button.setTextColor(it.buttonTextColor)

                val line: View = view.findViewById(R.id.tutLineView)
                line.setBackgroundColor(it.ancientColor)

                if (!it.showing) {
                    numberHolder.foreground = it.getNumberOffStateForegroundDrawable()
                    title.setTextColor(it.foregroundBlockColor)
                    description.visibility = View.GONE
                    button.visibility = View.GONE
                    line.visibility = View.GONE
                } else {
                    numberHolder.foreground = null
                    title.setTextColor(it.textColor)
                    description.visibility = View.VISIBLE
                    button.visibility = View.VISIBLE
                    line.visibility = View.VISIBLE

                    button.setOnClickListener {
                        listener?.onCompleteStep(pos)
                    }
                }
            }
        }
    }
}