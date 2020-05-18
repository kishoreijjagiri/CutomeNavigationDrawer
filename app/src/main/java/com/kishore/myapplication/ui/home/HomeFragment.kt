package com.kishore.myapplication.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.kishore.myapplication.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    companion object{
        @JvmStatic
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        var rangebar1=root.findViewById<com.appyvet.materialrangebar.RangeBar>(R.id.rangebar1)
        rangebar1.setRangePinsByIndices(0,1000)

        var whats_btn=root.findViewById<Button>(R.id.whats_btn)

        var number=+919550670720

        whats_btn.setOnClickListener {
           /* val url = "https://api.whatsapp.com/send?phone=$number"
            val i = Intent(Intent.ACTION_VIEW)
            i.putExtra(Intent.EXTRA_TEXT, "http://qa.tradecarros.com/type/spare-parts/")
            i.data = Uri.parse(url)
            startActivity(i)*/

          /*  val shares = Intent(Intent.ACTION_SEND)
            shares.type = "text/plain"
            shares.putExtra(Intent.EXTRA_TEXT, "http://qa.tradecarros.com/type/spare-parts/")
            shares.setPackage("com.whatsapp") //Facebook App package
*/
           // activity?.startActivity(Intent.createChooser(shares, "Title of the dialog the system will open"))


            try {
                val text = "http://qa.tradecarros.com" // Replace with your message.

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$number&text=$text")
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return root
    }



}
