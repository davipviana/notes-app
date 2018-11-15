package com.davipviana.notes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.davipviana.notes.R
import com.davipviana.notes.dao.NoteDao
import com.davipviana.notes.model.Note
import com.davipviana.notes.ui.recyclerview.adapter.NotesAdapter

class NotesActivity : AppCompatActivity() {

    private lateinit var adapter: NotesAdapter

    private lateinit var notes: ArrayList<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        notes = getExampleNotes()

        initializeNotesRecyclerView(notes)

        initializeNewNoteClick()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1 && resultCode == 2 && data != null && data.hasExtra("note")) {
            val newNote = data.getSerializableExtra("note") as Note

            NoteDao().insert(newNote)
            adapter.add(newNote)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun initializeNewNoteClick() {
        val newNoteTextView = findViewById<TextView>(R.id.notes_new_note)
        newNoteTextView.setOnClickListener {
            startActivityForResult(Intent(this, NoteFormActivity::class.java), 1)
        }
    }

    private fun getExampleNotes(): ArrayList<Note> {
        val noteDao = NoteDao()
        noteDao.insert(Note("Nota", "Descrição"))
        return noteDao.getAll()
    }



    private fun initializeNotesRecyclerView(notes: ArrayList<Note>) {
        val notesRecyclerView = findViewById<RecyclerView>(R.id.notes_list)

        adapter = NotesAdapter(this, notes)

        notesRecyclerView.adapter = adapter
    }
}
