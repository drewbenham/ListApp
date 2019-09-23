package com.drew_benham.listapp.database;

import android.os.AsyncTask;

/**
 * This is a generic async task. not used currently.
 * @param <T>
 */

public abstract class DaoAsyncProcessor<T> {

    public interface DaoProcessCallback<T> {
        void onResult(T result);
    }

    private DaoProcessCallback daoProcessCallback;

    abstract T doAsync();

    public DaoAsyncProcessor(DaoProcessCallback daoProcessCallback) {
        this.daoProcessCallback = daoProcessCallback;
    }

    public void start() {
        new DaoProcessAsyncTask().execute();
    }

    private class DaoProcessAsyncTask extends AsyncTask<Void, Void, T> {

        @Override
        protected T doInBackground(Void... voids) {
            return doAsync();
        }

        @Override
        protected void onPostExecute(T t) {
            if (daoProcessCallback != null) {
                daoProcessCallback.onResult(t);
            }
        }
    }
}
