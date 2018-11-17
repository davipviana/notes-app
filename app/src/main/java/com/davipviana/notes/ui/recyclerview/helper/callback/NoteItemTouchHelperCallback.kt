package com.davipviana.notes.ui.recyclerview.helper.callback

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.davipviana.notes.dao.NoteDao
import com.davipviana.notes.ui.recyclerview.adapter.NotesAdapter

class NoteItemTouchHelperCallback(private val adapter: NotesAdapter) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipeFlags = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT

        return makeMovementFlags(0, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        NoteDao().remove(viewHolder.adapterPosition)
        adapter.remove(viewHolder.adapterPosition)
    }
}
