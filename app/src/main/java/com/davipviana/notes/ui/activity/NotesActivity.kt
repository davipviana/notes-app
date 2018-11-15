package com.davipviana.notes.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.davipviana.notes.R
import com.davipviana.notes.dao.NoteDao
import com.davipviana.notes.model.Note
import com.davipviana.notes.ui.adapter.NotesAdapter

class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val notesListView = findViewById<ListView>(R.id.notes_list)

        val noteDao = NoteDao()
        noteDao.insert(Note("Primeira nota", "Primeira descrição"))

        notesListView.adapter = NotesAdapter(this, noteDao.getAll())
    }
}
