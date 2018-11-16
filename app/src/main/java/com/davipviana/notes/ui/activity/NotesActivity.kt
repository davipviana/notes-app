package com.davipviana.notes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast
import com.davipviana.notes.R
import com.davipviana.notes.dao.NoteDao
import com.davipviana.notes.model.Note
import com.davipviana.notes.ui.recyclerview.adapter.NotesAdapter
import com.davipviana.notes.ui.recyclerview.adapter.OnItemClickListener

class NotesActivity : AppCompatActivity() {

    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        initializeNotesRecyclerView(NoteDao().getAll())
        initializeNewNoteClick()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(isCreatedNoteResult(requestCode, resultCode, data)) {
            addNote(data)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun addNote(data: Intent?) {
        val newNote = data?.getSerializableExtra(Constants.NOTE_KEY) as Note
        NoteDao().insert(newNote)
        adapter.add(newNote)
    }

    private fun isCreatedNoteResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return isCreateNoteRequestCode(requestCode) &&
                isCreatedNoteResultCode(resultCode) &&
                hasNote(data)
    }

    private fun isCreatedNoteResultCode(resultCode: Int) = resultCode == Constants.RESULT_CODE_NOTE_CREATED

    private fun isCreateNoteRequestCode(requestCode: Int) = requestCode == Constants.REQUEST_CODE_NEW_NOTE

    private fun hasNote(data: Intent?) = data != null && data.hasExtra("note")

    private fun initializeNewNoteClick() {
        val newNoteTextView = findViewById<TextView>(R.id.notes_new_note)
        newNoteTextView.setOnClickListener {
            startActivityForResult(
                Intent(this, NoteFormActivity::class.java),
                Constants.REQUEST_CODE_NEW_NOTE
            )
        }
    }

    private fun initializeNotesRecyclerView(notes: ArrayList<Note>) {
        val notesRecyclerView = findViewById<RecyclerView>(R.id.notes_list)
        adapter = NotesAdapter(this, notes)

        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick() {
                Toast.makeText(this@NotesActivity, "viewholder na activity", Toast.LENGTH_LONG).show()
            }
        })

        notesRecyclerView.adapter = adapter
    }
}
