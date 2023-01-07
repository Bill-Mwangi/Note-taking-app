//
//  NoteDetailViewModel.swift
//  iosApp
//
//  Created by Bill on 04/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared


extension NoteDetailScreen {
    @MainActor class NoteDetailViewModel: ObservableObject {
        private var noteDataSource: NoteDataSource?
        private var noteId: Int64? = nil
        @Published var noteTitle: String = ""
        @Published var noteContent: String = ""
        private(set) var noteColour = Note.Companion().generateRandomColour()
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNoteIfExists(id: Int64?) {
            if id != nil {
                self.noteId = id
                noteDataSource?.getNoteById(id: id!, completionHandler: { note, error in
                    self.noteTitle = note?.title ?? ""
                    self.noteContent = note?.content ?? ""
                    self.noteColour = note?.colourHex ?? Note.Companion().generateRandomColour()
                })
            }
        }
        
        func saveNote(onSaved: @escaping () -> Void) {
            noteDataSource?.insertNote(note: Note(
                id: noteId == nil ? nil : KotlinLong(value: noteId!),
                title: noteTitle,
                content: noteContent,
                colourHex: noteColour,
                created: DateTimeUtil().now()
            ),
                completionHandler: { error in
                onSaved()
            })
        }
        
        func setParameters(noteDataSource: NoteDataSource, noteId: Int64?) {
            self.noteDataSource = noteDataSource
            loadNoteIfExists(id: noteId)
        }
    }
}
