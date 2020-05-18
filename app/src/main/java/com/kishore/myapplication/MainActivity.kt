package com.kishore.myapplication
import android.animation.ObjectAnimator
import android.graphics.Path
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.animation.PathInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.navigation.NavigationView
import com.kishore.myapplication.ui.gallery.GalleryFragment
import com.kishore.myapplication.ui.home.HomeFragment


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var menu_icon:ImageView;
    var isOutSideClicked:Boolean=false
    lateinit var  drawerLayout: MyDrawerLayout
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


         drawerLayout = findViewById(R.id.drawer_layout)
         navView = findViewById(R.id.nav_view)


        val imageView = navView.findViewById<ImageView>(R.id.imageView)
        val imageView1 = navView.findViewById<ImageView>(R.id.imageView1)

        var toplayout=navView.findViewById<View>(R.id.toplayout);


        imageView1.setOnClickListener {

            navView.visibility=View.GONE
            drawerLayout.closeDrawers()
            Toast.makeText(applicationContext,"Test",Toast.LENGTH_SHORT).show()
            var fragmenttransaction =supportFragmentManager.beginTransaction()
            fragmenttransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            fragmenttransaction.replace(R.id.container, GalleryFragment.newInstance()).commit()
        }

        imageView.setOnClickListener {
            //drawerLayout.closeDrawers();
     /*       val newFragment: Fragment = HomeFragment()
            supportFragmentManager.beginTransaction().add(R.id.container,newFragment).commit()*/
            navView.visibility=View.GONE
            drawerLayout.closeDrawers()
            Toast.makeText(applicationContext,"Test",Toast.LENGTH_SHORT).show()
            var fragmenttransaction =supportFragmentManager.beginTransaction()
            fragmenttransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
            fragmenttransaction.add(R.id.container, HomeFragment.newInstance()).commit()


        }
        menu_icon=findViewById(R.id.menu_icon)

        val toggle: MyActionBarDrawerToggle =
            object : MyActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            ) {

                var alreredyanim=false;
                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    alreredyanim=true
                    //toplayout.visibility=View.GONE

                    ObjectAnimator.ofFloat(navView, "translationY", -100f).apply {
                        duration = 1000
                        start()
                    }


                }

                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    //toplayout.visibility=View.VISIBLE
                    alreredyanim=false

                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    Log.v("Main actvty",""+slideOffset)

                    if(slideOffset<1&&alreredyanim)
                    {
                        alreredyanim=false
                        Log.v("Main actvty","anim "+slideOffset)

                        ObjectAnimator.ofFloat(navView, "translationY", 100f).apply {
                            duration = 1000
                            start()
                        }
                    }

                }
            }

        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        toggle.setDrawerIndicatorEnabled(false)
        val drawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_menu_camera,
            getTheme()
        )
        toggle.setHomeAsUpIndicator(drawable)
        toggle.setToolbarNavigationClickListener(View.OnClickListener {
            if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        })

        var app_bar_layout = findViewById<AppBarLayout>(R.id.app_bar_layout)

       // actionBar!!.elevation = 0f


        menu_icon.setOnClickListener {
            navView.visibility=View.VISIBLE
            drawerLayout?.openDrawer(Gravity.LEFT)
        }

    }







}
