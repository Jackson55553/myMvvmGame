package view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mymvvm.R
import com.example.mymvvm.databinding.FragmentMainBinding
import kotlin.system.exitProcess


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startGame.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, StartGameFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.exit.setOnClickListener {
            exitProcess(0)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}