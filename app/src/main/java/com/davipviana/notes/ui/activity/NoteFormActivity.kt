package com.davipviana.notes.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import com.davipviana.notes.R
import com.davipviana.notes.model.Note

class NoteFormActivity : AppCompatActivity() {
    private var receivedPosition: Int = Constants.INVALID_POSITION
    private lateinit var title: EditText
    private lateinit var description: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_form)

        title = findViewById(R.id.note_form_title)
        description = findViewById(R.id.note_form_description)

        if(intent.hasExtra(Constants.NOTE_KEY)) {
            val receivedNote = intent.getSerializableExtra(Constants.NOTE_KEY) as Note
            receivedPosition = intent.getIntExtra(Constants.POSITION_KEY, Constants.INVALID_POSITION)

            loadNoteInfo(receivedNote)
        }
    }

    private fun loadNoteInfo(note: Note) {
        title.setText(note.title)
        description.setText(note.description)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_form_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (isSaveAction(item)) {
            val newNote = createNoteFromInputs()
            returnCreatedNote(newNote)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun returnCreatedNote(newNote: Note) {
        val insertResult = Intent()
        insertResult.putExtra(Constants.NOTE_KEY, newNote)
        insertResult.putExtra(Constants.POSITION_KEY, this.receivedPosition)
        setResult(Activity.RESULT_OK, insertResult)
    }

    private fun createNoteFromInputs(): Note {
        return Note(title.text.toString(), description.text.toString())
    }

    private fun isSaveAction(item: MenuItem?) = item?.itemId == R.id.note_form_menu_ic_save_note
}
