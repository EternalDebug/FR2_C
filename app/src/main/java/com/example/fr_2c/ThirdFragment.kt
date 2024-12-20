package com.example.fr_2c

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.example.Articles
import com.example.fr_2c.databinding.FragmentSecondBinding
import com.example.fr_2c.databinding.FragmentThirdBinding
import okio.Utf8

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
        }

        binding.button4.setOnClickListener{
            val au = binding.textAuthor.text.toString();
            val pub = binding.textPublished.text.toString();
            val tit = String(binding.textTitle.text.toString().toByteArray(), Charsets.UTF_8);
            viewModel.curNews = Articles(0,tit,au,pub);
            findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}