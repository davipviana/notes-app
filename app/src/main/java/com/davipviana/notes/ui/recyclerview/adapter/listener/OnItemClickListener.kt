package com.davipviana.notes.ui.recyclerview.adapter.listener

import com.davipviana.notes.model.Note

interface OnItemClickListener {
    fun onItemClick(note: Note, position: Int)
}