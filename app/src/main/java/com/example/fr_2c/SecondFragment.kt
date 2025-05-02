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
import kotlin.math.roundToInt

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
        //выставляем базовую информацию о новости
        val cn = viewModel.curNews;
        binding.textAuthor.text = cn.author;
        if (cn.publishedAt == null)
            cn.publishedAt = "Неизвестно";
        binding.textPublished.text = cn.publishedAt
        binding.textTitle.text = cn.title
        binding.textDesc.text = cn.description
        //ставим базовое состояние анализа
        binding.textSentiment.visibility = View.VISIBLE
        binding.sentimentProgress2.progress = 50
        binding.textSent.text = "0.5"
        binding.textPerc.text = "0%"
        binding.textComment.text = "No comments..."
        binding.buttonMore.visibility = View.INVISIBLE

        viewModel.Answer.observe(viewLifecycleOwner, Observer {
            //binding.textSentiment.text = "${it.status}, Сентимент-оценка: ${it.resSent}, Процент: ${it.resPercent}";
            viewModel.curAnswer = it
            binding.textSentiment.visibility = View.INVISIBLE
            binding.buttonMore.visibility = View.VISIBLE

            binding.sentimentProgress2.progress = (it.resSent!! * 100).toInt()

            var s = it.resSent
            s = if (s != null) (s * 1000).roundToInt() / 1000.0 else 0.5
            var p = it.resPercent
            p = if (p != null) (p * 1000).roundToInt() / 1000.0 else 0.0

            binding.textSent.text = s.toString()
            binding.textPerc.text = "${p}%"
            binding.textComment.text = if (it.comment != null) it.comment else "Null comment"
        })



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonMore.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_moreInfoFrag)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}