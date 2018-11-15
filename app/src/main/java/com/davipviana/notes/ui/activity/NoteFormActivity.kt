package com.davipviana.notes.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import com.davipviana.notes.R
import com.davipviana.notes.dao.NoteDao
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
        if(item?.itemId == R.id.note_form_menu_ic_save_note) {

            val titleEditText = findViewById<EditText>(R.id.note_form_title)
            val descriptionEditText = findViewById<EditText>(R.id.note_form_description)

            val newNote = Note(titleEditText.text.toString(), descriptionEditText.text.toString())
            val insertResult = Intent()
            insertResult.putExtra("note", newNote)

            setResult(2, insertResult)

            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
