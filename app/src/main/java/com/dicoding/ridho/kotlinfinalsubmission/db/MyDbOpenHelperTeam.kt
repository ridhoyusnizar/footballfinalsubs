package com.dicoding.ridho.kotlinfinalsubmission.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dicoding.ridho.kotlinfinalsubmission.favorites.model.FavoriteTeam
import org.jetbrains.anko.db.*

class MyDbOpenHelperTeam(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDbOpenHelperTeam? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDbOpenHelperTeam {
            if (instance == null) {
                instance = MyDbOpenHelperTeam(ctx.applicationContext)
            }
            return instance as MyDbOpenHelperTeam
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteTeam.TABLE_FAVORITE, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.ID_TEAM to TEXT + UNIQUE,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteTeam.TABLE_FAVORITE, true)
    }
}

val Context.databaseTeam: MyDbOpenHelperTeam
    get() = MyDbOpenHelperTeam.getInstance(applicationContext)