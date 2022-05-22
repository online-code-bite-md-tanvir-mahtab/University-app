package com.tanvircodder.exmple.uvinvercitys.databaes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tanvircodder.exmple.uvinvercitys.model.Util;

import java.util.List;


@Dao
public interface VersityDao {
    @Query("SELECT * FROM  util")
    List<Util> getAll();
    @Insert
    void insert(Util util);
    @Delete
    void delete(Util util);
    @Update
    void update(Util util);
}
