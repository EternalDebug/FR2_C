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
import com.google.android.material.tabs.TabLayout

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = binding.rcView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adaptator

        binding.button3.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_thirdFragment)
        }

        // Устанавливаем начальное состояние
        setupInitialTabSelection()

        // Добавление слушателя для выбора табов
        setupTabSelectionListener()

        binding.button2.setOnClickListener {
            saveNewsIfApi()
        }

        binding.buttonFirst.setOnClickListener {
            if (viewModel.state == "api")
            {
                viewModel.getNews()
            }

        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            if (viewModel.state == "api"){
                viewModel.getNews()
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        return binding.root
    }

    private fun setupInitialTabSelection() {
        if (viewModel.state == "api") {
            binding.sourceTabLayout.getTabAt(0)?.select()
            binding.button2.visibility = View.VISIBLE
            binding.buttonFirst.visibility = View.VISIBLE
        } else {
            binding.sourceTabLayout.getTabAt(1)?.select()
            binding.button2.visibility = View.INVISIBLE
            binding.buttonFirst.visibility = View.INVISIBLE
        }
    }

    private fun setupTabSelectionListener() {
        binding.sourceTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> switchToApi()
                    1 -> switchToSavedNews()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun switchToApi(flag: Boolean = false) {
        adaptator.updateNewsList(viewModel.newsAPI)
        viewModel.state = "api"
        binding.button2.visibility = View.VISIBLE
        binding.buttonFirst.visibility = View.VISIBLE
        if (viewModel.newsAPI.isEmpty() or flag) {
            viewModel.getNews()
            observeNewsFetchResult()
        }
    }

    private fun switchToSavedNews() {
        viewModel.newsAPI = adaptator.NewsList
        adaptator.updateNewsList(viewModel.newsDB)
        viewModel.state = "db"
        binding.button2.visibility = View.INVISIBLE
        binding.buttonFirst.visibility = View.INVISIBLE
    }

    private fun observeNewsFetchResult() {
        viewModel.isGNFailure.observe(viewLifecycleOwner) { isFailure ->
            if (isFailure) {
                MakeToast("Нет доступа к интернету!")
            } else {
                MakeToast("Пул новостей обновлен")
            }
        }
    }

    private fun saveNewsIfApi() {
        // Сохранить новости из текущего списка, если в API
        if (viewModel.state == "api") {
            for (elem in adaptator.NewsList) {
                if (elem !in viewModel.newsDB)
                    dbViewModel.insert(elem)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun MakeToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }
}