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
import com.ruddell.resume.models.WorkExperience;
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
public final class WorkExperienceDao_Impl implements WorkExperienceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkExperience> __insertionAdapterOfWorkExperience;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public WorkExperienceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkExperience = new EntityInsertionAdapter<WorkExperience>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `WorkExperience` (`_id`,`position`,`companyName`,`dates`,`description`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WorkExperience value) {
        if (value.get_id() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.get_id());
        }
        if (value.getPosition() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPosition());
        }
        if (value.getCompanyName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCompanyName());
        }
        if (value.getDates() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDates());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDescription());
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM workexperience";
        return _query;
      }
    };
  }

  @Override
  public void insert(final WorkExperience... item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWorkExperience.insert(item);
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
  public LiveData<List<WorkExperience>> selectAll() {
    final String _sql = "SELECT * FROM workexperience";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"workexperience"}, false, new Callable<List<WorkExperience>>() {
      @Override
      public List<WorkExperience> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfCompanyName = CursorUtil.getColumnIndexOrThrow(_cursor, "companyName");
          final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<WorkExperience> _result = new ArrayList<WorkExperience>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final WorkExperience _item;
            final Integer _tmp_id;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmp_id = null;
            } else {
              _tmp_id = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpPosition;
            if (_cursor.isNull(_cursorIndexOfPosition)) {
              _tmpPosition = null;
            } else {
              _tmpPosition = _cursor.getString(_cursorIndexOfPosition);
            }
            final String _tmpCompanyName;
            if (_cursor.isNull(_cursorIndexOfCompanyName)) {
              _tmpCompanyName = null;
            } else {
              _tmpCompanyName = _cursor.getString(_cursorIndexOfCompanyName);
            }
            final String _tmpDates;
            if (_cursor.isNull(_cursorIndexOfDates)) {
              _tmpDates = null;
            } else {
              _tmpDates = _cursor.getString(_cursorIndexOfDates);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item = new WorkExperience(_tmp_id,_tmpPosition,_tmpCompanyName,_tmpDates,_tmpDescription);
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
