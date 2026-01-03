package com.anmfire.tv.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\u0011J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00122\u0006\u0010\u001b\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0012H\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0012H\u0086@\u00a2\u0006\u0002\u0010\u001dJ\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\u00112\u0006\u0010\u0017\u001a\u00020\u0018J\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00112\u0006\u0010\u0017\u001a\u00020\u0018J\u0016\u0010#\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010&R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/anmfire/tv/data/repository/AnimeRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "api", "Lcom/anmfire/tv/data/api/AnmApi;", "db", "Lcom/anmfire/tv/data/db/AppDatabase;", "gson", "Lcom/google/gson/Gson;", "addFavorite", "", "anime", "Lcom/anmfire/tv/data/model/Anime;", "(Lcom/anmfire/tv/data/model/Anime;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFavorites", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/anmfire/tv/data/db/Favorite;", "getAllHistory", "Lcom/anmfire/tv/data/db/WatchHistory;", "getAnimeDetails", "slug", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAnimesByGenre", "genre", "getCatalog", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGenreList", "Lcom/anmfire/tv/data/model/Genre;", "getHistoryForAnime", "isFavorite", "", "removeFavorite", "saveHistory", "history", "(Lcom/anmfire/tv/data/db/WatchHistory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AnimeRepository {
    @org.jetbrains.annotations.NotNull
    private final com.anmfire.tv.data.api.AnmApi api = null;
    @org.jetbrains.annotations.Nullable
    private final com.anmfire.tv.data.db.AppDatabase db = null;
    @org.jetbrains.annotations.NotNull
    private final com.google.gson.Gson gson = null;
    
    public AnimeRepository(@org.jetbrains.annotations.Nullable
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCatalog(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.anmfire.tv.data.model.Anime>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAnimeDetails(@org.jetbrains.annotations.NotNull
    java.lang.String slug, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.anmfire.tv.data.model.Anime> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getGenreList(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.anmfire.tv.data.model.Genre>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAnimesByGenre(@org.jetbrains.annotations.NotNull
    java.lang.String genre, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.anmfire.tv.data.model.Anime>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.anmfire.tv.data.db.Favorite>> getAllFavorites() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isFavorite(@org.jetbrains.annotations.NotNull
    java.lang.String slug) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object addFavorite(@org.jetbrains.annotations.NotNull
    com.anmfire.tv.data.model.Anime anime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object removeFavorite(@org.jetbrains.annotations.NotNull
    java.lang.String slug, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.anmfire.tv.data.db.WatchHistory>> getAllHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object saveHistory(@org.jetbrains.annotations.NotNull
    com.anmfire.tv.data.db.WatchHistory history, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.anmfire.tv.data.db.WatchHistory>> getHistoryForAnime(@org.jetbrains.annotations.NotNull
    java.lang.String slug) {
        return null;
    }
    
    public AnimeRepository() {
        super();
    }
}