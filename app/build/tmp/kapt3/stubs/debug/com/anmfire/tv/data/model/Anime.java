package com.anmfire.tv.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bH\u00c6\u0003J\u0011\u0010-\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000bH\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u00a1\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0006\u00103\u001a\u00020\u0003J\u0006\u00104\u001a\u00020\u0003J\t\u00105\u001a\u000206H\u00d6\u0001J\t\u00107\u001a\u00020\u0003H\u00d6\u0001R\u001e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016\u00a8\u00068"}, d2 = {"Lcom/anmfire/tv/data/model/Anime;", "", "slug", "", "title", "titleOriginal", "synopsis", "images", "Lcom/anmfire/tv/data/model/Images;", "image", "genres", "", "episodes", "Lcom/anmfire/tv/data/model/Episode;", "format", "type", "score", "title_jp", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/anmfire/tv/data/model/Images;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)V", "getEpisodes", "()Ljava/util/List;", "getFormat", "()Ljava/lang/String;", "getGenres", "getImage", "getImages", "()Lcom/anmfire/tv/data/model/Images;", "getScore", "()Ljava/lang/Object;", "getSlug", "getSynopsis", "getTitle", "getTitleOriginal", "getTitle_jp", "getType", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "getPoster", "getScoreString", "hashCode", "", "toString", "app_debug"})
public final class Anime {
    @com.google.gson.annotations.SerializedName(value = "slug")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String slug = null;
    @com.google.gson.annotations.SerializedName(value = "title")
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = null;
    @com.google.gson.annotations.SerializedName(value = "title_original")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String titleOriginal = null;
    @com.google.gson.annotations.SerializedName(value = "synopsis")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String synopsis = null;
    @com.google.gson.annotations.SerializedName(value = "images")
    @org.jetbrains.annotations.Nullable
    private final com.anmfire.tv.data.model.Images images = null;
    @com.google.gson.annotations.SerializedName(value = "image")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String image = null;
    @com.google.gson.annotations.SerializedName(value = "genres")
    @org.jetbrains.annotations.Nullable
    private final java.util.List<java.lang.String> genres = null;
    @com.google.gson.annotations.SerializedName(value = "episodes")
    @org.jetbrains.annotations.Nullable
    private final java.util.List<com.anmfire.tv.data.model.Episode> episodes = null;
    @com.google.gson.annotations.SerializedName(value = "format")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String format = null;
    @com.google.gson.annotations.SerializedName(value = "type")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String type = null;
    @com.google.gson.annotations.SerializedName(value = "score")
    @org.jetbrains.annotations.Nullable
    private final java.lang.Object score = null;
    @com.google.gson.annotations.SerializedName(value = "title_jp")
    @org.jetbrains.annotations.Nullable
    private final java.lang.String title_jp = null;
    
    public Anime(@org.jetbrains.annotations.NotNull
    java.lang.String slug, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String titleOriginal, @org.jetbrains.annotations.Nullable
    java.lang.String synopsis, @org.jetbrains.annotations.Nullable
    com.anmfire.tv.data.model.Images images, @org.jetbrains.annotations.Nullable
    java.lang.String image, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> genres, @org.jetbrains.annotations.Nullable
    java.util.List<com.anmfire.tv.data.model.Episode> episodes, @org.jetbrains.annotations.Nullable
    java.lang.String format, @org.jetbrains.annotations.Nullable
    java.lang.String type, @org.jetbrains.annotations.Nullable
    java.lang.Object score, @org.jetbrains.annotations.Nullable
    java.lang.String title_jp) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSlug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTitleOriginal() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getSynopsis() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.anmfire.tv.data.model.Images getImages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getImage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> getGenres() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.anmfire.tv.data.model.Episode> getEpisodes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getFormat() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getScore() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getTitle_jp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPoster() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getScoreString() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.anmfire.tv.data.model.Images component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.util.List<com.anmfire.tv.data.model.Episode> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.anmfire.tv.data.model.Anime copy(@org.jetbrains.annotations.NotNull
    java.lang.String slug, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.Nullable
    java.lang.String titleOriginal, @org.jetbrains.annotations.Nullable
    java.lang.String synopsis, @org.jetbrains.annotations.Nullable
    com.anmfire.tv.data.model.Images images, @org.jetbrains.annotations.Nullable
    java.lang.String image, @org.jetbrains.annotations.Nullable
    java.util.List<java.lang.String> genres, @org.jetbrains.annotations.Nullable
    java.util.List<com.anmfire.tv.data.model.Episode> episodes, @org.jetbrains.annotations.Nullable
    java.lang.String format, @org.jetbrains.annotations.Nullable
    java.lang.String type, @org.jetbrains.annotations.Nullable
    java.lang.Object score, @org.jetbrains.annotations.Nullable
    java.lang.String title_jp) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}