package com.ruddell.resume.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.ruddell.resume.database.daos.EducationDao;
import com.ruddell.resume.database.daos.EducationDao_Impl;
import com.ruddell.resume.database.daos.SkillsDao;
import com.ruddell.resume.database.daos.SkillsDao_Impl;
import com.ruddell.resume.database.daos.WorkExperienceDao;
import com.ruddell.resume.database.daos.WorkExperienceDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile WorkExperienceDao _workExperienceDao;

  private volatile SkillsDao _skillsDao;

  private volatile EducationDao _educationDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `WorkExperience` (`_id` INTEGER, `position` TEXT NOT NULL, `companyName` TEXT, `dates` TEXT, `description` TEXT, PRIMARY KEY(`_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Education` (`_id` INTEGER, `schoolName` TEXT, `degreeName` TEXT, `graduationDate` TEXT, `gpa` REAL, `website` TEXT, PRIMARY KEY(`_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Skills` (`_id` INTEGER, `categoryName` TEXT NOT NULL, `itemNames` TEXT NOT NULL, PRIMARY KEY(`_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c1a5b71826cab33de1e6dad961710241')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `WorkExperience`");
        _db.execSQL("DROP TABLE IF EXISTS `Education`");
        _db.execSQL("DROP TABLE IF EXISTS `Skills`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsWorkExperience = new HashMap<String, TableInfo.Column>(5);
        _columnsWorkExperience.put("_id", new TableInfo.Column("_id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkExperience.put("position", new TableInfo.Column("position", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkExperience.put("companyName", new TableInfo.Column("companyName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkExperience.put("dates", new TableInfo.Column("dates", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkExperience.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkExperience = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorkExperience = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorkExperience = new TableInfo("WorkExperience", _columnsWorkExperience, _foreignKeysWorkExperience, _indicesWorkExperience);
        final TableInfo _existingWorkExperience = TableInfo.read(_db, "WorkExperience");
        if (! _infoWorkExperience.equals(_existingWorkExperience)) {
          return new RoomOpenHelper.ValidationResult(false, "WorkExperience(com.ruddell.resume.models.WorkExperience).\n"
                  + " Expected:\n" + _infoWorkExperience + "\n"
                  + " Found:\n" + _existingWorkExperience);
        }
        final HashMap<String, TableInfo.Column> _columnsEducation = new HashMap<String, TableInfo.Column>(6);
        _columnsEducation.put("_id", new TableInfo.Column("_id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEducation.put("schoolName", new TableInfo.Column("schoolName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEducation.put("degreeName", new TableInfo.Column("degreeName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEducation.put("graduationDate", new TableInfo.Column("graduationDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEducation.put("gpa", new TableInfo.Column("gpa", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEducation.put("website", new TableInfo.Column("website", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEducation = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEducation = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEducation = new TableInfo("Education", _columnsEducation, _foreignKeysEducation, _indicesEducation);
        final TableInfo _existingEducation = TableInfo.read(_db, "Education");
        if (! _infoEducation.equals(_existingEducation)) {
          return new RoomOpenHelper.ValidationResult(false, "Education(com.ruddell.resume.models.Education).\n"
                  + " Expected:\n" + _infoEducation + "\n"
                  + " Found:\n" + _existingEducation);
        }
        final HashMap<String, TableInfo.Column> _columnsSkills = new HashMap<String, TableInfo.Column>(3);
        _columnsSkills.put("_id", new TableInfo.Column("_id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkills.put("categoryName", new TableInfo.Column("categoryName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSkills.put("itemNames", new TableInfo.Column("itemNames", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSkills = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSkills = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSkills = new TableInfo("Skills", _columnsSkills, _foreignKeysSkills, _indicesSkills);
        final TableInfo _existingSkills = TableInfo.read(_db, "Skills");
        if (! _infoSkills.equals(_existingSkills)) {
          return new RoomOpenHelper.ValidationResult(false, "Skills(com.ruddell.resume.models.Skills).\n"
                  + " Expected:\n" + _infoSkills + "\n"
                  + " Found:\n" + _existingSkills);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c1a5b71826cab33de1e6dad961710241", "dd9f5fe28468b5c97441a3e7f984ea29");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "WorkExperience","Education","Skills");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `WorkExperience`");
      _db.execSQL("DELETE FROM `Education`");
      _db.execSQL("DELETE FROM `Skills`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(WorkExperienceDao.class, WorkExperienceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SkillsDao.class, SkillsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EducationDao.class, EducationDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public WorkExperienceDao workExperienceDao() {
    if (_workExperienceDao != null) {
      return _workExperienceDao;
    } else {
      synchronized(this) {
        if(_workExperienceDao == null) {
          _workExperienceDao = new WorkExperienceDao_Impl(this);
        }
        return _workExperienceDao;
      }
    }
  }

  @Override
  public SkillsDao skillsDao() {
    if (_skillsDao != null) {
      return _skillsDao;
    } else {
      synchronized(this) {
        if(_skillsDao == null) {
          _skillsDao = new SkillsDao_Impl(this);
        }
        return _skillsDao;
      }
    }
  }

  @Override
  public EducationDao educationDao() {
    if (_educationDao != null) {
      return _educationDao;
    } else {
      synchronized(this) {
        if(_educationDao == null) {
          _educationDao = new EducationDao_Impl(this);
        }
        return _educationDao;
      }
    }
  }
}
