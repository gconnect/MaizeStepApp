package com.chuks.maizestemapp.maizestemplot.ui


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chuks.maizestemapp.R
import com.chuks.maizestemapp.capturedinsect.viewmodel.CapturedInsectViewModel
import com.chuks.maizestemapp.categoriesofspecies.egyptianarmyworm.viewmodel.EgyptianWormViewModel
import com.chuks.maizestemapp.common.data.DataSource
import com.chuks.maizestemapp.common.data.Insect
import com.chuks.maizestemapp.common.data.MaizePlot
import com.chuks.maizestemapp.common.util.showToast
import com.chuks.maizestemapp.maizestemplot.viewmodel.MaizePlotViewModel
import com.chuks.maizestemapp.maizestemplot.viewmodel.combineWith
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
    private val viewmodel by viewModel<CapturedInsectViewModel>()
    private val viewModel1 by viewModel<EgyptianWormViewModel>()
    private lateinit var progress: ProgressBar
    private var dataVal1 = ArrayList<Entry>()

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
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        progress = view.findViewById<ProgressBar>(R.id.progressBarPlot)
        linechart = view.findViewById(R.id.lineChart)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        initMe()
        showMessage()
        showProgress()
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        swipe.setOnRefreshListener {
            initMe()
            showMessage()
            showProgress()
            swipe.isRefreshing = false
        }
        return view
    }


    /**
     * The [lineGraph] has an [entry] param and sets the different features of the chart
     */
    private fun lineGraph(entry: List<Entry>) {
//        val linedataSet1 = LineDataSet(entry, "Insect Pests Density Population Plots\n" +
//                " for EIL & ET determination")
        val linedataSet1 = LineDataSet(entry, "Fall")
        val linedataSet2 = LineDataSet(entry, "Egyptian")
        val linedataSet3 = LineDataSet(entry, "Fall")

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(linedataSet1)
        dataSet.add(linedataSet2)
        dataSet.add(linedataSet3)

        linedataSet1.notifyDataSetChanged()
        linedataSet2.notifyDataSetChanged()
        linedataSet3.notifyDataSetChanged()

        //Adding features to the lines
        linedataSet1.setDrawCircleHole(true)
        linedataSet1.setValueTextColor(Color.BLACK)
        linedataSet1.valueTextSize = 16f
//        linedataSet1.setColors(colorArray)
        linedataSet1.color = Color.RED
        linedataSet2.color = Color.GREEN
        linedataSet3.color = Color.BLUE
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
        description.text = "Day captured"
        description.textColor = Color.BLACK
        description.textSize = 16f
        linechart.description = description

        val linedata = LineData(dataSet)
        linechart.data = linedata
        linechart.invalidate()
    }


    private fun lineGraph1(firstSetEntry: List<Entry>, secondSetEntry: List<Entry>, thirdSetEntry: List<Entry>) {
        val linedataSet1 = LineDataSet(firstSetEntry, "Africa")
        val linedataSet2 = LineDataSet(secondSetEntry, "Egypt")
        val linedataSet3 = LineDataSet(thirdSetEntry, "Fall")

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(linedataSet1)
        dataSet.add(linedataSet2)
         dataSet.add(linedataSet3)
        linedataSet1.notifyDataSetChanged()
        linedataSet2.notifyDataSetChanged()
        linedataSet3.notifyDataSetChanged()

        //Adding features to the lines
        linedataSet1.setDrawCircleHole(true)
        linedataSet1.setValueTextColor(Color.BLACK)
        linedataSet1.valueTextSize = 16f
//        linedataSet1.setColors(colorArray)
        linedataSet1.color = Color.RED
        linedataSet2.color = Color.GREEN
        linedataSet3.color = Color.BLUE
        linedataSet1.lineWidth = 2f

        val description = Description()
        description.text = "Date captured"
        description.textColor = Color.BLACK
        description.textSize = 16f


        val linedata = LineData(dataSet)
        linechart.data = linedata
        linechart.invalidate()
    }

    fun initMe() {
        val africanEntries = insectViewModel.dataEntries("AAW")
        val egyptianEntries = insectViewModel.dataEntries("ECLW")
        val fallEntries = insectViewModel.dataEntries("FAW")
        val ET = insectViewModel.dataEntries("ET")
        val EIL = insectViewModel.dataEntries("EIL")
        africanEntries.combineWith(egyptianEntries, fallEntries) { afr, egy, fall ->
            Timber.d("afric ${afr?.size}")
            Timber.d("egy ${egy?.size}")
            lineGraph1(afr?: emptyList(), egy ?: emptyList(),
                fall?: emptyList())
        }.observe(viewLifecycleOwner, Observer {
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


    fun getAllInsects() {
        val dataVal = ArrayList<Entry>()
        viewmodel.capturedInsect.observe(viewLifecycleOwner, Observer { maizePlot ->
            if (maizePlot.isNotEmpty()) {
                linechart.visibility = View.VISIBLE
                val fall: List<Insect> = maizePlot
//                        Timber.d("${maizePlot.}")
                maizePlot.forEach {
                    try {
                        dataVal.add(
                            Entry(
                                it.date.takeLast(2).toInt().toFloat(),
                                it.count.toFloat()
                            )
                        )
                        Timber.d("data value egypt ${dataVal.size}")
                    } catch (e: NumberFormatException) {
                        Timber.d("Exception is $e")
                    }
                }
//                lineGraph1(dataVal)
                Timber.d("data size for egyptian plot ${maizePlot.size}")
            } else {
                linechart.visibility = View.GONE
            }
        })
    }
}





