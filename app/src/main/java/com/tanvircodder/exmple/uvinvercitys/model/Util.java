package com.tanvircodder.exmple.uvinvercitys.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Util implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "m_name")
    public String mName;
    @ColumnInfo(name = "m_domains")
    public String mDomains;
    @ColumnInfo(name = "m_url")
    public String mUrl ;


    public String getmDomains() {
        return mDomains;
    }

    public void setmDomains(String mDomains) {
        this.mDomains = mDomains;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
