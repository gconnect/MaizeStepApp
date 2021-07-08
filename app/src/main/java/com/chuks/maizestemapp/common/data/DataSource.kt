package com.chuks.maizestemapp.common.data

/**
* This class holds dummy data for local testing
 */
class DataSource {


//Dummy data to be replaced with real data from the api

//    fun dataSet(): List<Insect> {
//        val list = ArrayList<Insect>()
//        list.add(
//            Insect(
//                1,
//                "https://cdn.pixabay.com/photo/2019/05/03/03/09/vietnam-4174969_960_720.jpg",
//                "Fall Armworm", "NW", "SW",
//                "12 01 2020", "12:00PM", 2
//            )
//        )
//        list.add(
//            Insect(
//                2,
//                "https://cdn.pixabay.com/photo/2019/05/03/03/09/vietnam-4174969_960_720.jpg",
//                "Fall Armworm", "NW", "SW",
//                "12 01 2020", "12:00PM", 2
//            )
//        )
//        return list
//    }


    fun maizPlot() : List<MaizePlot>{
        val maizeList = ArrayList<MaizePlot>()
        maizeList.add(
            MaizePlot(1, "20f", "30f",
            "60f", "70f", "80f")
        )

        return maizeList
    }
}