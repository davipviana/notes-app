package com.davipviana.notes.ui.recyclerview.helper.callback

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.davipviana.notes.dao.NoteDao
import com.davipviana.notes.ui.recyclerview.adapter.NotesAdapter

class NoteItemTouchHelperCallback(private val adapter: NotesAdapter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipeFlags = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        val dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT

        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        swapNotes(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    private fun swapNotes(startPosition: Int, endPosition: Int) {
        NoteDao().swap(startPosition, endPosition)
        adapter.swap(startPosition, endPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        removeNote(viewHolder.adapterPosition)
    }

    private fun removeNote(position: Int) {
        NoteDao().remove(position)
        adapter.remove(position)
    }
}
