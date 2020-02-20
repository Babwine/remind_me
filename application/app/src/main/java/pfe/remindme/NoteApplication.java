package pfe.remindme;

import android.app.Application;

import com.facebook.stetho.Stetho;

import pfe.remindme.data.di.FakeDependencyInjection;

/**
 * La classe principale de l'application
 */
public class NoteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}