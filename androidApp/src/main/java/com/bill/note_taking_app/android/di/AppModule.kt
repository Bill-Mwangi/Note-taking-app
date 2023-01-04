package com.bill.note_taking_app.android.di

import android.app.Application
import com.bill.note_taking_app.data.local.DatabaseDriverFactory
import com.bill.note_taking_app.data.note.SqlDelightNoteDataSource
import com.bill.note_taking_app.database.NoteDatabase
import com.bill.note_taking_app.domain.note.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideSqlDriver(app: Application): SqlDriver {
    return DatabaseDriverFactory(app).createDriver()
  }

  @Provides
  @Singleton
  fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
    return SqlDelightNoteDataSource(NoteDatabase(driver))
  }
}