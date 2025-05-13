package com.example.`data`.room

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RoomDao_Impl(
  __db: RoomDatabase,
) : RoomDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRoomEntity: EntityInsertAdapter<RoomEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRoomEntity = object : EntityInsertAdapter<RoomEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `user_table` (`id`,`name`,`age`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RoomEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindLong(3, entity.age.toLong())
      }
    }
  }

  public override suspend fun insert(user: RoomEntity): Unit = performSuspending(__db, false, true)
      { _connection ->
    __insertAdapterOfRoomEntity.insert(_connection, user)
  }

  public override suspend fun getUserById(userId: Int): RoomEntity? {
    val _sql: String = "SELECT * FROM user_table WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, userId.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfAge: Int = getColumnIndexOrThrow(_stmt, "age")
        val _result: RoomEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpAge: Int
          _tmpAge = _stmt.getLong(_columnIndexOfAge).toInt()
          _result = RoomEntity(_tmpId,_tmpName,_tmpAge)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
