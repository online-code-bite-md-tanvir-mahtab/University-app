package com.tanvircodder.exmple.uvinvercitys.databaes;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tanvircodder.exmple.uvinvercitys.model.Util;

@Database(entities = {Util.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract VersityDao versityDao();
}
