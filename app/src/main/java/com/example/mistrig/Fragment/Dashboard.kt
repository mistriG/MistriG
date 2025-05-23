package com.example.mistrig.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.mistrig.DataClass.ServiceName
import com.example.mistrig.DataClass.serviceCardIdList
import com.example.mistrig.DataClass.serviceNameList
import com.example.mistrig.R
import kotlin.system.exitProcess

class Dashboard : Fragment() {

     private var cardViewList: MutableList<CardView> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Dashboard", "onCreate called")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // initializing the service cards
        initCards(view)

        // setting click listener on card
        for ( (index, card) in cardViewList.withIndex()){
                handleClick(card, index)
        }


        return view
    }

    private fun loadFragment(fragment: Fragment) {
       requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initCards(view: View){
        cardViewList = mutableListOf<CardView>(
            view.findViewById<CardView>(R.id.dash_cardView_1),
            view.findViewById<CardView>(R.id.dash_cardView_2),
            view.findViewById<CardView>(R.id.dash_cardView_3),
            view.findViewById<CardView>(R.id.dash_cardView_4),
            view.findViewById<CardView>(R.id.dash_cardView_5),
            view.findViewById<CardView>(R.id.dash_cardView_6),
            view.findViewById<CardView>(R.id.dash_cardView_7),
            view.findViewById<CardView>(R.id.dash_cardView_8),
            view.findViewById<CardView>(R.id.dash_cardView_9),
            view.findViewById<CardView>(R.id.dash_cardView_10),
            view.findViewById<CardView>(R.id.dash_cardView_11),
            view.findViewById<CardView>(R.id.dash_cardView_12),
            view.findViewById<CardView>(R.id.dash_cardView_13),
            view.findViewById<CardView>(R.id.dash_cardView_14),
            view.findViewById<CardView>(R.id.dash_cardView_15),
            view.findViewById<CardView>(R.id.dash_cardView_16),
            view.findViewById<CardView>(R.id.dash_cardView_17),
            view.findViewById<CardView>(R.id.dash_cardView_18),
            view.findViewById<CardView>(R.id.dash_cardView_19),
            view.findViewById<CardView>(R.id.dash_cardView_20)
        )
    }

    private fun handleClick(card : CardView, index : Int){
        var serviceName : String? = null
        card.setOnClickListener {
            try {
                Toast.makeText(requireContext(), "clicked ${serviceNameList()[index].name}", Toast.LENGTH_SHORT).show()
                serviceName = serviceNameList()[index].name
            }catch (e : Exception){
                Toast.makeText(requireContext(),"$e", Toast.LENGTH_LONG).show()
            }finally {
                val bundle = Bundle()
                bundle.putString("name", "$serviceName")
                ServiceMap().arguments = bundle
                loadFragment(ServiceMap())
            }
        }
    }

}