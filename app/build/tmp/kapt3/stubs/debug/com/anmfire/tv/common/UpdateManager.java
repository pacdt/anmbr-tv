package com.anmfire.tv.common;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0002J\u0010\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001d"}, d2 = {"Lcom/anmfire/tv/common/UpdateManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "VERSION_JSON_URL", "", "_status", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/anmfire/tv/common/UpdateStatus;", "api", "Lcom/anmfire/tv/data/api/AnmApi;", "downloadId", "", "status", "Lkotlinx/coroutines/flow/StateFlow;", "getStatus", "()Lkotlinx/coroutines/flow/StateFlow;", "checkForUpdate", "", "currentVersionCode", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "installApk", "monitorDownload", "downloadManager", "Landroid/app/DownloadManager;", "startDownload", "url", "app_debug"})
public final class UpdateManager {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.anmfire.tv.common.UpdateStatus> _status = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.anmfire.tv.common.UpdateStatus> status = null;
    @org.jetbrains.annotations.NotNull
    private final com.anmfire.tv.data.api.AnmApi api = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String VERSION_JSON_URL = "https://raw.githubusercontent.com/pacdt/anmbr-tv/main/version.json";
    private long downloadId = -1L;
    
    public UpdateManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.anmfire.tv.common.UpdateStatus> getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object checkForUpdate(int currentVersionCode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void startDownload(@org.jetbrains.annotations.NotNull
    java.lang.String url) {
    }
    
    private final void monitorDownload(android.app.DownloadManager downloadManager) {
    }
    
    private final void installApk() {
    }
}