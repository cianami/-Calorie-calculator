import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // Создайте таблицы базы данных здесь
        val createTableQuery =
            "CREATE TABLE IF NOT EXISTS MyTable (id INTEGER PRIMARY KEY AUTOINCREMENT, foodname TEXT, kkal INTEGER);"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Обновление базы данных при необходимости
        // Вызывается, когда версия базы данных изменяется
        db.execSQL("DROP TABLE MyTable");
        onCreate(db);
    }
    companion object {
        private const val DATABASE_NAME = "MyDatabase"
        private const val DATABASE_VERSION = 1
    }
}