package com.davipviana.notes.ui.recyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.davipviana.notes.R
import com.davipviana.notes.model.Note

class NotesAdapter(
    val context: Context,
    val notes: List<Note>
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.notes_item, parent, false)

        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bindNoteInfo(note)
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.notes_item_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.notes_item_description)

        fun bindNoteInfo(note: Note) {
            titleTextView.text = note.title
            descriptionTextView.text = note.description
        }
    }
}
