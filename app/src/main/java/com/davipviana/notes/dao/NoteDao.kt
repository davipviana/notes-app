package com.davipviana.notes.dao

import com.davipviana.notes.model.Note
import java.util.*

class NoteDao {

    fun getAll(): ArrayList<Note> {
        return notes.clone() as ArrayList<Note>
    }

    fun insert(note: Note) {
        NoteDao.notes +=  note
    }

    fun update(position: Int, note: Note) {
        notes[position] = note
    }

    fun remove(position: Int) {
        notes.removeAt(position)
    }

    fun swap(start: Int, end: Int) {
        Collections.swap(notes, start, end)
    }

    fun removeAll() {
        notes.clear()
    }

    companion object {

        private val notes = ArrayList<Note>()
    }
}