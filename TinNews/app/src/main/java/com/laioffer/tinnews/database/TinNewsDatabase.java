package com.laioffer.tinnews.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.laioffer.tinnews.model.Article;


@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class TinNewsDatabase extends RoomDatabase {

    public abstract ArticleDao articleDao();

}

/*
* Make sure both the class and the methods are abstract. Why? We do not implement it. The Room annotation processor will.
version specifies a current version. Once we introduce/modify the new version, we have to increase the version and define the migration strategy.
Entities specifies the tables the database contains.
exportSchema option is for dumping a database schema to file system. We do not need that.
* */