package view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import model.AppState
import viewmodel.MainViewModel
import com.example.mymvvm.databinding.FragmentMainGameBinding

class MainGameFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentMainGameBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.requestDataFromLocal()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val observer = Observer<AppState> { a -> renderData(a) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        binding.btn.setOnClickListener {
            viewModel.restartDataFromLocal()
        }
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val myData = data.questions
                with(binding) {
                    loadingLayout.visibility = View.GONE
                    val rnd = (0..52).random()
                    statement.text = myData[rnd].statement
                    exerciceSecond.text = myData[rnd].exercise
                }
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.main, "error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("reload") { viewModel.requestDataFromLocal() }
                    .show()
            }
            is AppState.Reload -> {
                val myData = data.questions
                with(binding) {
                    val rnd = (0..52).random()
                    statement.text = myData[rnd].statement
                    exerciceSecond.text = myData[rnd].exercise
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}