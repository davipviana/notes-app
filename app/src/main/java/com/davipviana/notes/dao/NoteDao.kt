package com.davipviana.notes.dao

import com.davipviana.notes.model.Note
import java.util.*

class NoteDao() {

    fun getAll(): List<Note> {
        return notes.clone() as List<Note>
    }

    fun insert(vararg notes: Note) {
        NoteDao.notes += Arrays.asList(notes) as ArrayList<Note>
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