package com.mtsteta.homework1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mtsteta.homework1.R
import com.mtsteta.homework1.database.entities.Actor

private const val MOVIE_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

class ActorsAdapter: RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {
    private var actors: List<Actor> = emptyList()

    class ActorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val photo: ImageView = itemView.findViewById(R.id.item_actor_photo)
        private val name: TextView = itemView.findViewById(R.id.item_actor_name)

        fun bind(actor: Actor) {
            photo.load(MOVIE_IMAGE_BASE_URL + actor.profilePath)
            name.text = actor.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(LayoutInflater.from(parent.context).
            inflate(R.layout.item_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors.get(position))
    }

    override fun getItemCount(): Int = actors.size

    fun setData(newActors: List<Actor>) {
        actors = newActors
        notifyDataSetChanged()
    }

    fun getData(): List<Actor> {
        return actors
    }
}