import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // Создайте таблицы базы данных здесь
        val createTableFoodsQuery =
            "CREATE TABLE IF NOT EXISTS Foods (id INTEGER PRIMARY KEY AUTOINCREMENT, foodname TEXT, kkal INTEGER, isProduct BOOLEAN);"
        db.execSQL(createTableFoodsQuery)
        val createTableСonsumptionsQuery =
            "CREATE TABLE IF NOT EXISTS Сonsumptions (id INTEGER PRIMARY KEY AUTOINCREMENT, id_food INTENGER, gram INTEGER, date TEXT);"
        db.execSQL(createTableСonsumptionsQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Обновление базы данных при необходимости
        // Вызывается, когда версия базы данных изменяется
        db.execSQL("DROP TABLE Foods");
        db.execSQL("DROP TABLE Сonsumptions");
        onCreate(db);
    }
    companion object {
        private const val DATABASE_NAME = "CalculatorDB"
        private const val DATABASE_VERSION = 1
    }
}