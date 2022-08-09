package view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvm.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import model.AppState
import viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val observer = Observer<AppState> { a -> renderData(a) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        binding.btn.setOnClickListener {
            viewModel.requestData()
        }

    }


    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {

                val myData = data.questions
                with(binding) {
                    firstInstruction.text = "Я никогда не..."
                    loadingLayout.visibility = View.GONE
                    question.visibility = View.VISIBLE
                    btn.text = "Следующее утверждение"
                }
                val rnd = (0..52).random()
                binding.question.text = myData[rnd].statement
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.main, "error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("reload") { viewModel.requestData() }
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}