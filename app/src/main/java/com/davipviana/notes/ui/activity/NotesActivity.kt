package com.davipviana.notes.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.TextView
import android.widget.Toast
import com.davipviana.notes.R
import com.davipviana.notes.dao.NoteDao
import com.davipviana.notes.model.Note
import com.davipviana.notes.ui.recyclerview.adapter.NotesAdapter
import com.davipviana.notes.ui.recyclerview.adapter.listener.OnItemClickListener
import com.davipviana.notes.ui.recyclerview.helper.callback.NoteItemTouchHelperCallback

class NotesActivity : AppCompatActivity() {

    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        initializeNotesRecyclerView(getNotes())
        initializeNewNoteClick()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (isCreatedNoteResult(requestCode, data)) {
            if (isSuccessfulResult(resultCode)) {
                addNote(data)
            }
        }

        if (isEditedNoteResult(requestCode, data)) {
            if (isSuccessfulResult(resultCode)) {
                val receivedNote = data?.getSerializableExtra(Constants.NOTE_KEY) as Note
                val receivedPosition = data.getIntExtra(Constants.POSITION_KEY, Constants.INVALID_POSITION)

                if (isValidPosition(receivedPosition)) {
                    update(receivedPosition, receivedNote)
                } else {
                    Toast.makeText(this, "Erro ao alterar nota", Toast.LENGTH_LONG).show()
                }
            }

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getNotes(): ArrayList<Note> {
        val noteDao = NoteDao()

        for (i in 1..10) {
            noteDao.insert(Note("Titulo " + i.toString(), "Descrição " + i.toString()))
        }

        return noteDao.getAll()
    }

    private fun update(position: Int, note: Note) {
        NoteDao().update(position, note)
        adapter.update(position, note)
    }

    private fun isValidPosition(receivedPosition: Int) = receivedPosition > Constants.INVALID_POSITION

    private fun isEditedNoteResult(requestCode: Int, data: Intent?): Boolean {
        return isEditedNoteRequestCode(requestCode) &&
                hasNote(data)
    }

    private fun isEditedNoteRequestCode(requestCode: Int) = requestCode == Constants.REQUEST_CODE_EDIT_NOTE

    private fun addNote(data: Intent?) {
        val newNote = data?.getSerializableExtra(Constants.NOTE_KEY) as Note
        NoteDao().insert(newNote)
        adapter.add(newNote)
    }

    private fun isCreatedNoteResult(requestCode: Int, data: Intent?): Boolean {
        return isCreateNoteRequestCode(requestCode) &&
                hasNote(data)
    }

    private fun isSuccessfulResult(resultCode: Int) = resultCode == Activity.RESULT_OK

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
        initializeAdapter(notes, notesRecyclerView)

        val itemTouchHelper = ItemTouchHelper(NoteItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(notesRecyclerView)
    }

    private fun initializeAdapter(notes: ArrayList<Note>, notesRecyclerView: RecyclerView) {
        adapter = NotesAdapter(this, notes)

        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(note: Note, position: Int) {
                val openNoteFormIntent = Intent(this@NotesActivity, NoteFormActivity::class.java)
                openNoteFormIntent.putExtra(Constants.NOTE_KEY, note)
                openNoteFormIntent.putExtra(Constants.POSITION_KEY, position)

                startActivityForResult(openNoteFormIntent, Constants.REQUEST_CODE_EDIT_NOTE)
            }
        })

        notesRecyclerView.adapter = adapter
    }
}
