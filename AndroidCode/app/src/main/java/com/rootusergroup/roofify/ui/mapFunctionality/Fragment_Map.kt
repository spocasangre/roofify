package com.rootusergroup.roofify.ui.mapFunctionality

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.navigation.NavigationView
import com.rootusergroup.roofify.R
import com.rootusergroup.roofify.databinding.FragmentFavoritesBinding
import com.rootusergroup.roofify.databinding.FragmentMapBinding
import com.rootusergroup.roofify.ui.loginSignup.LoginAndSignupActivity


class Fragment_Map : Fragment(), NavigationView.OnNavigationItemSelectedListener{

    private var _binding:  FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var googleMapsFragment: SupportMapFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        googleMapsFragment = childFragmentManager.findFragmentById(R.id.map_general) as SupportMapFragment

        binding.imageView.setOnClickListener {
            if(!binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }else {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            val size: Int = binding.navigationView.menu.size()
            for (i in 0 until size) {
                binding.navigationView.menu.getItem(i).isChecked = false
            }
        }
        
        binding.navigationView.setNavigationItemSelectedListener(this)

        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.profile_menu_item ->  {
                findNavController().navigate(R.id.fragment_UserProfile)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.myproperties_menu_item ->  {
                findNavController().navigate(R.id.myPropertiesFragment)
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.logout_menu_item ->  {
                requireContext().let {
                    it.startActivity(Intent(it, LoginAndSignupActivity::class.java))
                }
                activity?.finish()
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }

        return true
    }
}