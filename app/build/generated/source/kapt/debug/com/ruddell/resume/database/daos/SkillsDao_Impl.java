package com.ruddell.resume.database.daos;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ruddell.resume.database.typeconverters.JsonConverter;
import com.ruddell.resume.models.Skills;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SkillsDao_Impl implements SkillsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Skills> __insertionAdapterOfSkills;

  private final JsonConverter __jsonConverter = new JsonConverter();

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public SkillsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSkills = new EntityInsertionAdapter<Skills>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Skills` (`_id`,`categoryName`,`itemNames`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Skills value) {
        if (value.get_id() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.get_id());
        }
        if (value.getCategoryName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCategoryName());
        }
        final String _tmp = __jsonConverter.fromList(value.getItemNames());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM skills";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Skills... item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSkills.insert(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Skills>> selectAll() {
    final String _sql = "SELECT * FROM skills";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"skills"}, false, new Callable<List<Skills>>() {
      @Override
      public List<Skills> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfCategoryName = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryName");
          final int _cursorIndexOfItemNames = CursorUtil.getColumnIndexOrThrow(_cursor, "itemNames");
          final List<Skills> _result = new ArrayList<Skills>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Skills _item;
            final Integer _tmp_id;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmp_id = null;
            } else {
              _tmp_id = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final List<String> _tmpItemNames;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfItemNames)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfItemNames);
            }
            _tmpItemNames = __jsonConverter.fromString(_tmp);
            _item = new Skills(_tmp_id,_tmpCategoryName,_tmpItemNames);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
