package com.example.mistrig.DataClass

import androidx.cardview.widget.CardView
import com.example.mistrig.R

data class ServiceName(
    val name : String
    // val workDescription : String
)

 fun serviceNameList () : List<ServiceName>{
    return listOf(
        ServiceName("Painter Services"),
        ServiceName("Plumber Services"),
        ServiceName("Carpenter Services"),
        ServiceName("Mason (Construction Worker) Services"),
        ServiceName("Electrician Services"),
        ServiceName("Welder Services"),
        ServiceName("Tile Installer Services"),
        ServiceName("Interior Designer Services"),
        ServiceName("Gardener Services"),
        ServiceName("House Cleaner Services"),
        ServiceName("AC Technician Services"),
        ServiceName("Roofer Services"),
        ServiceName("Pest Control Specialist Services"),
        ServiceName("Glass Installer Services"),
        ServiceName("Furniture Assembler Services"),
        ServiceName("Waterproofing Expert Services"),
        ServiceName("CCTV Technician Services"),
        ServiceName("Locksmith Services"),
        ServiceName("Solar Panel Installer Services"),
        ServiceName("Handyman (General Repair Expert) Services")
    )
}

fun serviceCardIdList () : List<Int>{
    return listOf(
        (R.id.dash_cardView_1),
        (R.id.dash_cardView_2),
        (R.id.dash_cardView_3),
        (R.id.dash_cardView_4),
        (R.id.dash_cardView_5),
        (R.id.dash_cardView_6),
        (R.id.dash_cardView_7),
        (R.id.dash_cardView_8),
        (R.id.dash_cardView_9),
        (R.id.dash_cardView_10),
        (R.id.dash_cardView_11),
        (R.id.dash_cardView_12),
        (R.id.dash_cardView_13),
        (R.id.dash_cardView_14),
        (R.id.dash_cardView_15),
        (R.id.dash_cardView_16),
        (R.id.dash_cardView_17),
        (R.id.dash_cardView_18),
        (R.id.dash_cardView_19),
        (R.id.dash_cardView_20)
    )
}