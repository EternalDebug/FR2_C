package com.example.fr_2c

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.fr_2c.databinding.ActivityMainBinding

var adaptator = Adaptator();

lateinit var viewModel: AppViewModel
lateinit var key:String

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val retrofitService = ExternalApiService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        key = getApiKey()!!


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.toolbar.title = "Actual News:";
        setSupportActionBar(binding.toolbar)
        binding.toolbar.subtitle = "Actual News:";
        //binding.toolbar.setTitle("Actual News:") //.title = "Actual News:";

        viewModel = AppViewModel(RetroRepository(retrofitService));

        adaptator.TestInit();
        viewModel.newsList.observe(this, Observer {
            adaptator.updateNewsList(it);
        })
        //adaptator.notifyDataSetChanged();

        //viewModel.getNews();

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun getApiKey(): String? {
        val applicationInfo: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val bundle = applicationInfo.metaData
        return bundle?.getString("keyValue") // Получаем строковое значение из метаданных
    }
}