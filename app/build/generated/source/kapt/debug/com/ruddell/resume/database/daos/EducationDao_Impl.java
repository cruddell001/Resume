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
import com.ruddell.resume.models.Education;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EducationDao_Impl implements EducationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Education> __insertionAdapterOfEducation;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public EducationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEducation = new EntityInsertionAdapter<Education>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Education` (`_id`,`schoolName`,`degreeName`,`graduationDate`,`gpa`,`website`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Education value) {
        if (value.get_id() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.get_id());
        }
        if (value.getSchoolName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSchoolName());
        }
        if (value.getDegreeName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDegreeName());
        }
        if (value.getGraduationDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getGraduationDate());
        }
        if (value.getGpa() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getGpa());
        }
        if (value.getWebsite() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getWebsite());
        }
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM education";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Education... item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEducation.insert(item);
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
  public LiveData<List<Education>> selectAll() {
    final String _sql = "SELECT * FROM education";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"education"}, false, new Callable<List<Education>>() {
      @Override
      public List<Education> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "_id");
          final int _cursorIndexOfSchoolName = CursorUtil.getColumnIndexOrThrow(_cursor, "schoolName");
          final int _cursorIndexOfDegreeName = CursorUtil.getColumnIndexOrThrow(_cursor, "degreeName");
          final int _cursorIndexOfGraduationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "graduationDate");
          final int _cursorIndexOfGpa = CursorUtil.getColumnIndexOrThrow(_cursor, "gpa");
          final int _cursorIndexOfWebsite = CursorUtil.getColumnIndexOrThrow(_cursor, "website");
          final List<Education> _result = new ArrayList<Education>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Education _item;
            final Integer _tmp_id;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmp_id = null;
            } else {
              _tmp_id = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpSchoolName;
            if (_cursor.isNull(_cursorIndexOfSchoolName)) {
              _tmpSchoolName = null;
            } else {
              _tmpSchoolName = _cursor.getString(_cursorIndexOfSchoolName);
            }
            final String _tmpDegreeName;
            if (_cursor.isNull(_cursorIndexOfDegreeName)) {
              _tmpDegreeName = null;
            } else {
              _tmpDegreeName = _cursor.getString(_cursorIndexOfDegreeName);
            }
            final String _tmpGraduationDate;
            if (_cursor.isNull(_cursorIndexOfGraduationDate)) {
              _tmpGraduationDate = null;
            } else {
              _tmpGraduationDate = _cursor.getString(_cursorIndexOfGraduationDate);
            }
            final Float _tmpGpa;
            if (_cursor.isNull(_cursorIndexOfGpa)) {
              _tmpGpa = null;
            } else {
              _tmpGpa = _cursor.getFloat(_cursorIndexOfGpa);
            }
            final String _tmpWebsite;
            if (_cursor.isNull(_cursorIndexOfWebsite)) {
              _tmpWebsite = null;
            } else {
              _tmpWebsite = _cursor.getString(_cursorIndexOfWebsite);
            }
            _item = new Education(_tmp_id,_tmpSchoolName,_tmpDegreeName,_tmpGraduationDate,_tmpGpa,_tmpWebsite);
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
