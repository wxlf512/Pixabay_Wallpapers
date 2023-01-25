package dev.wxlf.pixabaywallpapers.presentation.fragments

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import dev.wxlf.pixabaywallpapers.R
import dev.wxlf.pixabaywallpapers.presentation.common.Routes

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed({
         findNavController().navigate(Uri.parse(Routes.Categories.route),
         navOptions {
             popUpTo(R.id.nav_graph_app_xml) {
                 inclusive = true
             }
         })
        }, 750)
    }
}