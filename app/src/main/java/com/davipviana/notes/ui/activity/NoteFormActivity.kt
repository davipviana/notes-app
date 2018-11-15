package com.davipviana.notes.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.davipviana.notes.R
import com.davipviana.notes.model.Note

class NoteFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_form)
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
        setResult(Constants.RESULT_CODE_NOTE_CREATED, insertResult)
    }

    private fun createNoteFromInputs(): Note {
        val titleEditText = findViewById<EditText>(R.id.note_form_title)
        val descriptionEditText = findViewById<EditText>(R.id.note_form_description)
        return Note(titleEditText.text.toString(), descriptionEditText.text.toString())
    }

    private fun isSaveAction(item: MenuItem?) = item?.itemId == R.id.note_form_menu_ic_save_note
}
