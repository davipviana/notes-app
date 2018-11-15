package com.davipviana.notes.ui.adapter

import android.widget.BaseAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.davipviana.notes.R
import com.davipviana.notes.model.Note


class NotesAdapter(private val context: Context, private val notes: List<Note>) : BaseAdapter() {

    override fun getCount(): Int {
        return notes.size
    }

    override fun getItem(position: Int): Note {
        return notes[position]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val viewItem = LayoutInflater.from(context).inflate(R.layout.notes_item, viewGroup, false)
        val note = notes[position]

        val titleText = viewItem.findViewById<TextView>(R.id.notes_item_title)
        titleText.text = note.title

        val descriptionText = viewItem.findViewById<TextView>(R.id.notes_item_description)
        descriptionText.text = note.description

        return viewItem
    }
}
