package com.dbsrm.evo

import android.os.Bundle
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat

class OldCalenderViewActivity : AppCompatActivity(), OnDateChangeListener {
    @kotlin.jvm.JvmField
    //@BindView(R.id.calendarView)
    var widget: CalendarView? = null

    @kotlin.jvm.JvmField
   // @BindView(R.id.textView)
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old_calender_view2)
     //   ButterKnife.bind(this)
        widget!!.setOnDateChangeListener(this)
    }

    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
        textView!!.text = FORMATTER.format(view.date)
    }

    companion object {
        private val FORMATTER = SimpleDateFormat.getDateInstance()
    }
}