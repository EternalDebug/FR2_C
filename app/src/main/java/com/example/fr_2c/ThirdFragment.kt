package com.example.fr_2c

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fr_2c.DataClasses.Articles
import com.example.fr_2c.databinding.FragmentThirdBinding
import java.util.Calendar

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

    private var dpdFlag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_thirdFragment_to_FirstFragment)
        }

        binding.textPublished.setOnClickListener{
            if (! dpdFlag)
                showDatePickerDialog(view)
        }

        binding.button4.setOnClickListener{
            val au = binding.textAuthor.text.toString();
            val pub = binding.textPublished.text.toString();


            //showDatePickerDialog(view)

            val tit = binding.textTitle.text.toString()
            val desc = binding.textDescription.text.toString()
                //String(binding.textTitle.text.toString().toByteArray(), Charsets.UTF_8);
            viewModel.curNews = Articles(null,tit,au,pub, desc);
            dbViewModel.insert(viewModel.curNews)

            viewModel.getAnswer()
            findNavController().navigate(R.id.action_thirdFragment_to_SecondFragment);
        }
    }

    private fun showDatePickerDialog(view:View) {
        // Получаем текущее время
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Создаем DatePickerDialog
        val datePickerDialog = view.context?.let {
            DatePickerDialog(it, { _, selectedYear, selectedMonth, selectedDay ->
                // Устанавливаем дату в формате "YYYY/MM/DD"
                val selDay = if (selectedDay < 10) "0${selectedDay}" else "$selectedDay"
                val selMonth = if (selectedMonth < 9) "0${selectedMonth + 1}" else "${selectedMonth + 1}"
                val selectedDate = "$selectedYear-${selMonth}-$selDay"
                binding.textPublished.setText(selectedDate)
            }, year, month, day)
        }

        datePickerDialog?.setOnDismissListener {
            // Код, выполняемый при закрытии диалога
            dpdFlag = false
        }
        datePickerDialog?.setOnCancelListener {
            dpdFlag = false
        }

        dpdFlag = true
        // Показываем диалог
        datePickerDialog?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}