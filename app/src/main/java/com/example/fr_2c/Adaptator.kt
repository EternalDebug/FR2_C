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
            binding.textView.setText(task.title);

            val txt = binding.textView//itemView.findViewById<TextView>(R.id.textView_CA)
            txt.setOnClickListener {
                //code_req = task.urlpath!!;

                txt.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
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

    /*fun TestInit(){
        NewsList.add(Data("Title1"));
        NewsList.add(Data("Title2"));
        NewsList.add(Data("Title3"));
        NewsList.add(Data("Title4"));
        NewsList.add(Data("Title5"));
    }*/

    fun TestInit(){
        NewsList.add(Articles("Hui", "Title1"));
        NewsList.add(Articles("Hui", "Title2"))
        NewsList.add(Articles("Hui", "Title3"))
    }
}