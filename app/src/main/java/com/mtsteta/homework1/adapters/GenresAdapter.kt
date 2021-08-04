package com.mtsteta.homework1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtsteta.homework1.R
import com.mtsteta.homework1.dto.GenreDto
import com.mtsteta.homework1.dto.MovieDto
import com.mtsteta.homework1.listeners.GenreItemClickListener

class GenresAdapter(private val listener: GenreItemClickListener):
        RecyclerView.Adapter<GenresAdapter.GenreViewHolder>() {

    private var genres:List<GenreDto> = emptyList()

    class GenreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.item_genre_name)

        fun bind(genre: GenreDto) {
            name.text = genre.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenresAdapter.GenreViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_genre, parent, false))
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres.get(position))
        holder.itemView.setOnClickListener {
            listener.onGenreClick(genres.get(position).name)
        }
    }

    override fun getItemCount(): Int = genres.size

    fun setData(newGenres: List<GenreDto>) {
        genres = newGenres
        notifyDataSetChanged()
    }

    fun getData(): List<GenreDto> {
        return genres
    }
}