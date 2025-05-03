package com.example.fr_2c

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fr_2c.DataClasses.Articles
import com.example.fr_2c.DataClasses.InnerAPIResponse
import com.example.fr_2c.databinding.NewsOneBinding
import java.text.SimpleDateFormat
import java.util.Locale

class Adaptator : RecyclerView.Adapter<Adaptator.TaskHolder>(){

    var NewsList: MutableList<Articles> = mutableListOf<Articles>();

    class TaskHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = NewsOneBinding.bind(item)
        fun bind(task: Articles, pos: Int) = with(binding) {
            binding.textView.text = task.author ?: "Unknown Source";
            binding.textView2.text = task.title;

            // Show publication date if available
            task.publishedAt?.let { pubDate ->
                if (binding.publishedDate != null) { // Check if the TextView exists in layout
                    try {
                        // Format date if possible
                        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
                        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
                        val date = inputFormat.parse(pubDate)
                        binding.publishedDate.text = date?.let { outputFormat.format(it) } ?: pubDate
                    } catch (e: Exception) {
                        // If date parsing fails, show as is
                        binding.publishedDate.text = pubDate
                    }
                }
            }

            // Set card click listener to view article details
            binding.root.setOnClickListener {
                viewModel.curNews = task

                //Страшный костыль, обеспечивающий сброс интерфейса
                viewModel.Answer.postValue(InnerAPIResponse())

                viewModel.getAnswer()
                it.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

            binding.button.setOnClickListener{
                adaptator.removeNews(pos);
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_one, parent, false)
        return TaskHolder(view)
    }

    override fun getItemCount(): Int {
        return NewsList.size
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        //holder.bind(tdViewModel!!.notes.value!![position], position) { i -> removeTask(position) }
        holder.bind(NewsList[position], position)
    }

    fun updateNewsList(news: List<Articles>) {
        this.NewsList = news.toMutableList()
        notifyDataSetChanged()
    }

    fun removeNews(pos: Int){
        if (viewModel.state == "db"){
            dbViewModel.delete(NewsList[pos])
        }
        this.NewsList.removeAt(pos);
        notifyDataSetChanged();
    }

}
