package com.example.fr_2c

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Articles
import com.example.fr_2c.databinding.NewsOneBinding

class Adaptator : RecyclerView.Adapter<Adaptator.TaskHolder>(){

    var NewsList: MutableList<Articles> = mutableListOf<Articles>();

    class TaskHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = NewsOneBinding.bind(item)
        fun bind(task: Articles, pos: Int) = with(binding) {
            binding.textView.setText(task.author);
            binding.textView2.setText(task.title);

            val txt = binding.textView2//itemView.findViewById<TextView>(R.id.textView_CA)
            txt.setOnClickListener {
                //code_req = task.urlpath!!;
                viewModel.curNews = adaptator.NewsList[pos];
                txt.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
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
        this.NewsList.removeAt(pos);
        notifyDataSetChanged();
    }

    /*fun TestInit(){
        NewsList.add(Data("Title1"));
        NewsList.add(Data("Title2"));
        NewsList.add(Data("Title3"));
        NewsList.add(Data("Title4"));
        NewsList.add(Data("Title5"));
    }*/

    fun TestInit(){
        NewsList.add(Articles("Wasya", "Title1 GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"));
        NewsList.add(Articles("Petya", "Title2 Title2 Title2 Title2 Title2 Title2 Title2 Title2 Title2 Title2 Title2 Title2 Title2 Title2"))
        NewsList.add(Articles("Nastya", "Title3  TestTestTestTest TestTestTestTest TestTestTestTest TestTestTestTest TestTestTestTest TestTestTestTest"))
        NewsList.add(Articles("Wasya", "Title1 GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"));
    }
}