package com.example.fr_2c

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fr_2c.databinding.FragmentMoreInfoBinding
import com.example.fr_2c.databinding.FragmentSecondBinding
import kotlin.math.roundToInt

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

        val cn = viewModel.curNews;

        binding.textTitle.text = cn.title
        var ls = viewModel.curAnswer.linearSent
        ls = if (ls != null) (ls * 1000).roundToInt() / 1000.0 else 0.5
        var lp = viewModel.curAnswer.linearPercent
        lp = if (lp != null) (lp * 1000).roundToInt() / 1000.0 else 0.0

        var ns = viewModel.curAnswer.neuroSent
        ns = if (ns != null) (ns * 1000).roundToInt() / 1000.0 else 0.5
        var np = viewModel.curAnswer.neuroPercent
        np = if (np != null) (np * 1000).roundToInt() / 1000.0 else 0.0

        var lls = viewModel.curAnswer.aiSent
        lls = if (lls != null) (lls * 1000).roundToInt() / 1000.0 else 0.5
        var llp = viewModel.curAnswer.aiPercent
        llp = if (llp != null) (llp * 1000).roundToInt() / 1000.0 else 0.0

        binding.textSentLin.text = ls.toString()
        binding.textPercLin.text = "${lp}%"

        binding.textSentNeur.text = ns.toString()
        binding.textPercNeur.text = "${np}%"

        binding.textSentLlama.text = lls.toString()
        binding.textPercLlama.text = "${llp}%"

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