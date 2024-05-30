package com.example.fr_2c

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fr_2c.databinding.FragmentSecondBinding
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        var cn = viewModel.curNews;
        binding.textAuthor.setText("Автор: ${cn.author}");
        if (cn.publishedAt == null)
            cn.publishedAt = "Неизвестно";
        binding.textPublished.setText("Опубликовано: ${cn.publishedAt}");
        binding.textTitle.setText("Заголовок: ${cn.title}");

        viewModel.GetSentiment();
        //viewModel.cnSentiment

        //binding.textSentiment.setText("Сентимент-оценка: ${viewModel.cnSentiment}");
        viewModel.cnSentiment.observe(viewLifecycleOwner, Observer {
            binding.textSentiment.setText("Сентимент-оценка: ${it}");
        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.textSentiment.setText("Сентимент-оценка: ${viewModel.cnSentiment}");

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}