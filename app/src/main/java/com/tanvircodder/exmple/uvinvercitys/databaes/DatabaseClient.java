package com.tanvircodder.exmple.uvinvercitys.databaes;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

    private Context context;
    private AppDatabase appDatabase;
    private static DatabaseClient mInstance;
    public DatabaseClient(Context context){
        this.context = context;
        appDatabase = Room.databaseBuilder(context,AppDatabase.class,"My_University").build();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public synchronized static DatabaseClient getmInstance(Context context) {
        if (mInstance == null){
            mInstance = new DatabaseClient(context);
        }
        return mInstance;
    }
}
