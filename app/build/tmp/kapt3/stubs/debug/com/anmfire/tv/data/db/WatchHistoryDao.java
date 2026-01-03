package com.anmfire.tv.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\'J\u001c\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\n\u001a\u00020\u000bH\'J\u0016\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/anmfire/tv/data/db/WatchHistoryDao;", "", "clearHistory", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllHistory", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/anmfire/tv/data/db/WatchHistory;", "getHistoryForAnime", "slug", "", "insertOrUpdateHistory", "history", "(Lcom/anmfire/tv/data/db/WatchHistory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface WatchHistoryDao {
    
    @androidx.room.Query(value = "SELECT * FROM watch_history ORDER BY lastWatched DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.anmfire.tv.data.db.WatchHistory>> getAllHistory();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertOrUpdateHistory(@org.jetbrains.annotations.NotNull
    com.anmfire.tv.data.db.WatchHistory history, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM watch_history WHERE slug = :slug")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.anmfire.tv.data.db.WatchHistory>> getHistoryForAnime(@org.jetbrains.annotations.NotNull
    java.lang.String slug);
    
    @androidx.room.Query(value = "DELETE FROM watch_history")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object clearHistory(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}