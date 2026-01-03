package com.anmfire.tv.data.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\f2\u0006\u0010\b\u001a\u00020\tH\'\u00a8\u0006\u0011"}, d2 = {"Lcom/anmfire/tv/data/db/FavoriteDao;", "", "deleteFavorite", "", "favorite", "Lcom/anmfire/tv/data/db/Favorite;", "(Lcom/anmfire/tv/data/db/Favorite;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFavoriteBySlug", "slug", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFavorites", "Lkotlinx/coroutines/flow/Flow;", "", "insertFavorite", "isFavorite", "", "app_debug"})
@androidx.room.Dao
public abstract interface FavoriteDao {
    
    @androidx.room.Query(value = "SELECT * FROM favorites")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.anmfire.tv.data.db.Favorite>> getAllFavorites();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertFavorite(@org.jetbrains.annotations.NotNull
    com.anmfire.tv.data.db.Favorite favorite, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteFavorite(@org.jetbrains.annotations.NotNull
    com.anmfire.tv.data.db.Favorite favorite, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM favorites WHERE slug = :slug")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteFavoriteBySlug(@org.jetbrains.annotations.NotNull
    java.lang.String slug, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM favorites WHERE slug = :slug)")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isFavorite(@org.jetbrains.annotations.NotNull
    java.lang.String slug);
}