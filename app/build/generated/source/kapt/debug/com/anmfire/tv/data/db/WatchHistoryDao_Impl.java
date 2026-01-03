package com.anmfire.tv.data.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WatchHistoryDao_Impl implements WatchHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WatchHistory> __insertionAdapterOfWatchHistory;

  private final SharedSQLiteStatement __preparedStmtOfClearHistory;

  public WatchHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWatchHistory = new EntityInsertionAdapter<WatchHistory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `watch_history` (`slug`,`episodeNumber`,`title`,`coverUrl`,`position`,`duration`,`lastWatched`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WatchHistory entity) {
        if (entity.getSlug() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getSlug());
        }
        if (entity.getEpisodeNumber() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getEpisodeNumber());
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitle());
        }
        if (entity.getCoverUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCoverUrl());
        }
        statement.bindLong(5, entity.getPosition());
        statement.bindLong(6, entity.getDuration());
        statement.bindLong(7, entity.getLastWatched());
      }
    };
    this.__preparedStmtOfClearHistory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM watch_history";
        return _query;
      }
    };
  }

  @Override
  public Object insertOrUpdateHistory(final WatchHistory history,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWatchHistory.insert(history);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object clearHistory(final Continuation<? super Unit> arg0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearHistory.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearHistory.release(_stmt);
        }
      }
    }, arg0);
  }

  @Override
  public Flow<List<WatchHistory>> getAllHistory() {
    final String _sql = "SELECT * FROM watch_history ORDER BY lastWatched DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"watch_history"}, new Callable<List<WatchHistory>>() {
      @Override
      @NonNull
      public List<WatchHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "slug");
          final int _cursorIndexOfEpisodeNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfCoverUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUrl");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfLastWatched = CursorUtil.getColumnIndexOrThrow(_cursor, "lastWatched");
          final List<WatchHistory> _result = new ArrayList<WatchHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WatchHistory _item;
            final String _tmpSlug;
            if (_cursor.isNull(_cursorIndexOfSlug)) {
              _tmpSlug = null;
            } else {
              _tmpSlug = _cursor.getString(_cursorIndexOfSlug);
            }
            final String _tmpEpisodeNumber;
            if (_cursor.isNull(_cursorIndexOfEpisodeNumber)) {
              _tmpEpisodeNumber = null;
            } else {
              _tmpEpisodeNumber = _cursor.getString(_cursorIndexOfEpisodeNumber);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpCoverUrl;
            if (_cursor.isNull(_cursorIndexOfCoverUrl)) {
              _tmpCoverUrl = null;
            } else {
              _tmpCoverUrl = _cursor.getString(_cursorIndexOfCoverUrl);
            }
            final long _tmpPosition;
            _tmpPosition = _cursor.getLong(_cursorIndexOfPosition);
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final long _tmpLastWatched;
            _tmpLastWatched = _cursor.getLong(_cursorIndexOfLastWatched);
            _item = new WatchHistory(_tmpSlug,_tmpEpisodeNumber,_tmpTitle,_tmpCoverUrl,_tmpPosition,_tmpDuration,_tmpLastWatched);
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

  @Override
  public Flow<List<WatchHistory>> getHistoryForAnime(final String slug) {
    final String _sql = "SELECT * FROM watch_history WHERE slug = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (slug == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, slug);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"watch_history"}, new Callable<List<WatchHistory>>() {
      @Override
      @NonNull
      public List<WatchHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSlug = CursorUtil.getColumnIndexOrThrow(_cursor, "slug");
          final int _cursorIndexOfEpisodeNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfCoverUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverUrl");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfLastWatched = CursorUtil.getColumnIndexOrThrow(_cursor, "lastWatched");
          final List<WatchHistory> _result = new ArrayList<WatchHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WatchHistory _item;
            final String _tmpSlug;
            if (_cursor.isNull(_cursorIndexOfSlug)) {
              _tmpSlug = null;
            } else {
              _tmpSlug = _cursor.getString(_cursorIndexOfSlug);
            }
            final String _tmpEpisodeNumber;
            if (_cursor.isNull(_cursorIndexOfEpisodeNumber)) {
              _tmpEpisodeNumber = null;
            } else {
              _tmpEpisodeNumber = _cursor.getString(_cursorIndexOfEpisodeNumber);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpCoverUrl;
            if (_cursor.isNull(_cursorIndexOfCoverUrl)) {
              _tmpCoverUrl = null;
            } else {
              _tmpCoverUrl = _cursor.getString(_cursorIndexOfCoverUrl);
            }
            final long _tmpPosition;
            _tmpPosition = _cursor.getLong(_cursorIndexOfPosition);
            final long _tmpDuration;
            _tmpDuration = _cursor.getLong(_cursorIndexOfDuration);
            final long _tmpLastWatched;
            _tmpLastWatched = _cursor.getLong(_cursorIndexOfLastWatched);
            _item = new WatchHistory(_tmpSlug,_tmpEpisodeNumber,_tmpTitle,_tmpCoverUrl,_tmpPosition,_tmpDuration,_tmpLastWatched);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
