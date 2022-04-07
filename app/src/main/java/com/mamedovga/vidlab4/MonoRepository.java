package com.mamedovga.vidlab4;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MonoRepository {
    private MonoDao monoDao;
    private LiveData<List<Note>> allMono;

    public MonoRepository(Application application) {
        MonoDatabase database = MonoDatabase.getInstance(application);
        monoDao = database.monoDao();
        allMono = monoDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(monoDao).execute(note);
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(monoDao).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(monoDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(monoDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allMono;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private MonoDao monoDao;

        private InsertNoteAsyncTask(MonoDao monoDao) {
            this.monoDao = monoDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            monoDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private MonoDao monoDao;

        private UpdateNoteAsyncTask(MonoDao monoDao) {
            this.monoDao = monoDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            monoDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private MonoDao monoDao;

        private DeleteNoteAsyncTask(MonoDao monoDao) {
            this.monoDao = monoDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            monoDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private MonoDao monoDao;

        private DeleteAllNotesAsyncTask(MonoDao monoDao) {
            this.monoDao = monoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            monoDao.deleteAllNotes();
            return null;
        }
    }
}
