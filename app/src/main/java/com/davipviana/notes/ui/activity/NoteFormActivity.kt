package com.davipviana.notes.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.davipviana.notes.R
import com.davipviana.notes.model.Note

class NoteFormActivity : AppCompatActivity() {
    companion object {
        const val APPBAR_TITLE_INSERT: String = "Insere nota"
        const val APPBAR_TITLE_UPDATE: String = "Altera nota"
    }

    private var receivedPosition: Int = Constants.INVALID_POSITION
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_form)

        title = APPBAR_TITLE_INSERT

        titleEditText = findViewById(R.id.note_form_title)
        descriptionEditText = findViewById(R.id.note_form_description)

        if(intent.hasExtra(Constants.NOTE_KEY)) {
            title = APPBAR_TITLE_UPDATE
            val receivedNote = intent.getSerializableExtra(Constants.NOTE_KEY) as Note
            receivedPosition = intent.getIntExtra(Constants.POSITION_KEY, Constants.INVALID_POSITION)

            loadNoteInfo(receivedNote)
        }
    }

    private fun loadNoteInfo(note: Note) {
        titleEditText.setText(note.title)
        descriptionEditText.setText(note.description)
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
        return Note(titleEditText.text.toString(), descriptionEditText.text.toString())
    }

    private fun isSaveAction(item: MenuItem?) = item?.itemId == R.id.note_form_menu_ic_save_note
}
