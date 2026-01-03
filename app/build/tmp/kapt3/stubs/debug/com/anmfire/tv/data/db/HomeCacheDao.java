package com.anmfire.tv.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/anmfire/tv/data/db/HomeCacheDao;", "", "clearCache", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCache", "Lcom/anmfire/tv/data/db/HomeCache;", "insertCache", "cache", "(Lcom/anmfire/tv/data/db/HomeCache;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface HomeCacheDao {
    
    @androidx.room.Query(value = "SELECT * FROM home_cache WHERE id = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCache(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.anmfire.tv.data.db.HomeCache> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertCache(@org.jetbrains.annotations.NotNull
    com.anmfire.tv.data.db.HomeCache cache, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM home_cache")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object clearCache(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}