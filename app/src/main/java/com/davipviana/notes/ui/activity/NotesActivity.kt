package com.davipviana.notes.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.davipviana.notes.R
import com.davipviana.notes.dao.NoteDao
import com.davipviana.notes.model.Note
import com.davipviana.notes.ui.recyclerview.adapter.NotesAdapter

class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val notesRecyclerView = findViewById<RecyclerView>(R.id.notes_list)

        val noteDao = NoteDao()
        for(i in 1..10000) {
            noteDao.insert(Note("Nota " + i.toString(), "Descrição " + i.toString()))
        }


        notesRecyclerView.adapter = NotesAdapter(this, noteDao.getAll())
        notesRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
