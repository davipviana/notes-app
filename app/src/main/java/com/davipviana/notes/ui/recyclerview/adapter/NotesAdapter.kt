package com.davipviana.notes.ui.recyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.davipviana.notes.R
import com.davipviana.notes.model.Note
import com.davipviana.notes.ui.recyclerview.adapter.listener.OnItemClickListener

class NotesAdapter(
    val context: Context,
    val notes: ArrayList<Note>
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

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

    fun add(note: Note) {
        notes.add(note)
        notifyDataSetChanged()
    }

    fun update(position: Int, note: Note) {
        notes[position] = note
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.notes_item_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.notes_item_description)

        private lateinit var note: Note

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(note, adapterPosition)
            }
        }


        fun bindNoteInfo(note: Note) {
            this.note = note
            this.titleTextView.text = note.title
            this.descriptionTextView.text = note.description
        }
    }
}
