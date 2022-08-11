package view

import Fragments.HelpFragment
import Fragments.InstructionsFragment
import Fragments.SettingsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.mymvvm.R
import com.example.mymvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, MainFragment())
                .commit()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_appbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.instruction) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, InstructionsFragment()).addToBackStack(null).commit()
        }
        if (itemId == R.id.settings) {
            supportFragmentManager.beginTransaction().replace(R.id.container, SettingsFragment())
                .addToBackStack(null).commit()
        }
        if (itemId == R.id.help) {
            supportFragmentManager.beginTransaction().replace(R.id.container, HelpFragment())
                .addToBackStack(null).commit()
        }
        return super.onOptionsItemSelected(item)
    }
}