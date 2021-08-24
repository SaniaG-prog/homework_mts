package com.mtsteta.homework1.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.mtsteta.homework1.database.entities.Movie

class MovieDiffUtilCallback(private val oldList: List<Movie>,
                            private val newList: List<Movie>
                            ): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].overview == newList[newItemPosition].overview &&
                oldList[oldItemPosition].adult == newList[newItemPosition].adult &&
                oldList[oldItemPosition].voteAverage == newList[newItemPosition].voteAverage &&
                oldList[oldItemPosition].posterPath == newList[newItemPosition].posterPath
    }
}