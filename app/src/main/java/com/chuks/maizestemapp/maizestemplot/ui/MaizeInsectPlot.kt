package com.chuks.maizestemapp.maizestemplot.ui


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.chuks.maizestemapp.R
import com.chuks.maizestemapp.common.data.DataSource
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.MaizePlot
import com.chuks.maizestemapp.common.util.showToast
import com.chuks.maizestemapp.maizestemplot.viewmodel.MaizePlotViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_maize_insect_plot.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import com.github.mikephil.charting.utils.EntryXComparator
import java.util.*
import kotlin.collections.ArrayList

/**
 * This class handles the plotting of the chart
 */
class MaizeInsectPlot : Fragment() {

    private lateinit var linechart: LineChart
    private val insectViewModel by viewModel<MaizePlotViewModel>()

    var colorArray: MutableList<Int> = mutableListOf(
        Color.RED, Color.BLACK, Color.BLUE, Color.GREEN, Color.GRAY
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            com.chuks.maizestemapp.R.layout.fragment_maize_insect_plot,
            container,
            false
        )

        linechart = view.findViewById(R.id.lineChart)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        //lineGraph()
        setPlotData2()
        showMessage()
        showProgress()
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }


        return view
    }


    /**
     * The [lineGraph] has an [entry] param and sets the different features of the chart
     */
    private fun lineGraph(entry: List<Entry>) {
        val linedataSet1 = LineDataSet(entry, "Insect Pests Density Population Plots\n" +
                " for EIL & ET determination")
        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(linedataSet1)

        linedataSet1.notifyDataSetChanged()

        //Adding features to the lines
        linedataSet1.setDrawCircleHole(true)
        linedataSet1.setValueTextColor(Color.BLACK)
        linedataSet1.valueTextSize = 16f
        linedataSet1.setColors(colorArray)
        linedataSet1.lineWidth = 2f
        linechart.setDrawGridBackground(true)
        linechart.setNoDataText("No Data")
        linechart.setNoDataTextColor(Color.RED)
        linechart.setDrawBorders(true)
        linechart.setBorderColor(Color.BLUE)
        linechart.xAxis.getFormattedLabel(R.string.time)
//        linechart.xAxis.getFormattedLabel(R.string.insect_density)
        // Adding Legend
        val legend: Legend = linechart.legend
        legend.isEnabled
        legend.form = Legend.LegendForm.LINE

        var legenedEntries = arrayListOf<LegendEntry>()


        /*Labelling of the legends with their names and color*/
        legenedEntries.add(
            LegendEntry(
                "Fall Army Worm",
                Legend.LegendForm.SQUARE,
                8f,
                8f,
                null,
                Color.rgb(139, 235, 255)
            )
        )
        legenedEntries.add(
            LegendEntry(
                "Egyptian Army Worm",
                Legend.LegendForm.SQUARE,
                8f,
                8f,
                null,
                Color.rgb(246, 174, 94)
            )
        )
        legenedEntries.add(
            LegendEntry(
                "African Army Worm",
                Legend.LegendForm.SQUARE,
                8f,
                8f,
                null,
                Color.rgb(197, 255, 140)
            )
        )
        legenedEntries.add(
            LegendEntry(
                "Economic Injury Level",
                Legend.LegendForm.SQUARE,
                8f,
                8f,
                null,
                Color.rgb(103, 166, 239)
            )
        )
        legenedEntries.add(
            LegendEntry(
                "Economic Threshold",
                Legend.LegendForm.SQUARE,
                8f,
                8f,
                null,
                Color.rgb(251, 110, 119)
            )
        )
        legend.setCustom(legenedEntries)
        legend.setYOffset(2f)
        legend.setXOffset(2f)
        legend.setYEntrySpace(0f)
        legend.setTextSize(5f)
        //Adding description
        val description = Description()
        description.text = "Insect pest vs Time"
        description.textColor = Color.BLACK
        description.textSize = 16f
        linechart.description = description

        val linedata = LineData(dataSet)
        linechart.data = linedata
        linechart.invalidate()
    }



    private fun setPlotData2() {
        val dataVal = ArrayList<Entry>()
        insectViewModel.maize.observe(viewLifecycleOwner, Observer { maizePlot ->
            if (maizePlot.isNotEmpty()) {
                linechart.visibility = View.VISIBLE
                maizePlot.forEach {
                    dataVal.add(Entry(1f, it.african_worm.toFloat()))
                    dataVal.add(Entry(2f, it.fall_armyworm.toFloat()))
                    dataVal.add(Entry(2f, it.egyptian_worm.toFloat()))
                    dataVal.add(Entry(4f, it.economic_threshold.toFloat()))
                    dataVal.add(Entry(5f, it.economic_injury_level.toFloat()))
                }
                Timber.d("data size for plot ${dataVal.size}")
                lineGraph(dataVal)
            }else{
                linechart.visibility = View.GONE
            }

        })
    }


    /**
     * This shows the progressbar as the chart loads
     */
    private fun showProgress() {
        insectViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            progressBarPlot.visibility = if (it) View.VISIBLE else View.GONE
        })
    }


    /**
     * This shows an error or failure message
     */
    private fun showMessage() {
        insectViewModel.showMessage.observe(viewLifecycleOwner, Observer {
            val message = it
            context?.showToast(message)
        })
    }


}



