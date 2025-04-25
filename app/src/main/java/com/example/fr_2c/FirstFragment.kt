package com.example.fr_2c

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fr_2c.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        //(activity as AppCompatActivity).supportActionBar?.title = "Pull of actual news"
        val recyclerView: RecyclerView = _binding!!.rcView
        binding.apply { recyclerView.layoutManager = LinearLayoutManager(context); recyclerView.adapter = adaptator}
        binding.button3.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
        }

        if (viewModel.state == "api"){
            binding.switch1.text = "  API"
            binding.switch1.isChecked = true
            binding.button2.visibility = View.VISIBLE
        } else{
            binding.switch1.text = "   DB"
            binding.switch1.isChecked = false
            binding.button2.visibility = View.INVISIBLE
        }

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                adaptator.updateNewsList(viewModel.newsAPI)
                viewModel.state = "api"
                binding.switch1.text = "  API"
                binding.button2.visibility = View.VISIBLE
                if (viewModel.newsAPI.size == 0){
                    viewModel.getNews();
                    viewModel.isGNFailure.observe(viewLifecycleOwner) {
                        if (it)
                            MakeToast("Нет доступа к интернету!")
                        else
                            MakeToast("Пул новостей обновлен")
                    }

                }
            }
            else
            {
                viewModel.newsAPI = adaptator.NewsList
                adaptator.updateNewsList(viewModel.newsDB)
                viewModel.state = "db"
                binding.switch1.text = "  DB"
                binding.button2.visibility = View.INVISIBLE
            }
        }

        binding.button2.setOnClickListener {
            if (viewModel.state == "api")
                for (elem in adaptator.NewsList){
                    if (elem !in viewModel.newsDB)
                        dbViewModel.insert(elem)
                }
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            viewModel.getNews();
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    public fun MakeToast(string: String){
        Toast.makeText(this.context, string, Toast.LENGTH_SHORT).show()
    }
}