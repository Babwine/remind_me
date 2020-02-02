package pfe.remindme;

import android.app.Application;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

import pfe.remindme.data.db.TagDatabase;
import pfe.remindme.data.di.FakeDependencyInjection;

public class NoteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}