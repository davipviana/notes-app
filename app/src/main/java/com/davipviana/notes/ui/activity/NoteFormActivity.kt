package com.davipviana.notes.ui.activity

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

            NoteDao().insert(Note(titleEditText.text.toString(), descriptionEditText.text.toString()))
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
