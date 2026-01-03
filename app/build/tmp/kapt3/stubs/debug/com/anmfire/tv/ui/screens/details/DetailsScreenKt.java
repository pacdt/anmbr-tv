package com.anmfire.tv.ui.screens.details;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u001a\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0003H\u0007\u001a$\u0010\u000b\u001a\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007\u001aD\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\r2$\u0010\u0013\u001a \u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a(\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u001a\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\r2\b\b\u0002\u0010\u001e\u001a\u00020\u0003H\u0007\u00a8\u0006\u001f"}, d2 = {"ActionsButton", "", "isFavorite", "", "onToggleFavorite", "Lkotlin/Function0;", "onTrailerClick", "AnimeInfo", "data", "Lcom/anmfire/tv/data/model/Anime;", "centerText", "AnimePoster", "url", "", "title", "modifier", "Landroidx/compose/ui/Modifier;", "DetailsScreen", "slug", "onEpisodeClick", "Lkotlin/Function4;", "onBack", "EpisodeItem", "episode", "Lcom/anmfire/tv/data/model/Episode;", "history", "Lcom/anmfire/tv/data/db/WatchHistory;", "onClick", "Tag", "text", "isGenre", "app_debug"})
public final class DetailsScreenKt {
    
    @androidx.compose.runtime.Composable
    public static final void DetailsScreen(@org.jetbrains.annotations.NotNull
    java.lang.String slug, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function4<? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, kotlin.Unit> onEpisodeClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void Tag(@org.jetbrains.annotations.NotNull
    java.lang.String text, boolean isGenre) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void EpisodeItem(@org.jetbrains.annotations.NotNull
    com.anmfire.tv.data.model.Episode episode, @org.jetbrains.annotations.Nullable
    com.anmfire.tv.data.db.WatchHistory history, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void AnimePoster(@org.jetbrains.annotations.Nullable
    java.lang.String url, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ActionsButton(boolean isFavorite, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onToggleFavorite, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onTrailerClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void AnimeInfo(@org.jetbrains.annotations.NotNull
    com.anmfire.tv.data.model.Anime data, boolean centerText) {
    }
}