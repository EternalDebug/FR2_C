package com.example.fr_2c

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fr_2c.databinding.FragmentMoreInfoBinding
import com.example.fr_2c.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MoreInfoFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoreInfoFrag : Fragment() {
    private var _binding: FragmentMoreInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMoreInfoBinding.inflate(inflater, container, false)

        //var cn = viewModel.curNews;
        //binding.textAuthor.setText("Автор: ${cn.author}");
        //if (cn.publishedAt == null)
        //    cn.publishedAt = "Неизвестно";
        //binding.textPublished.setText("Опубликовано: ${cn.publishedAt}");
        //binding.textTitle.setText("Заголовок: ${cn.title}");

        //viewModel.getAnswer()

        //viewModel.Answer.observe(viewLifecycleOwner, Observer {
        //    binding.textSentiment.setText("${it.status}, Сентимент-оценка: ${it.resSent}, Процент: ${it.resPercent}");
        //})

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_moreInfoFrag_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}