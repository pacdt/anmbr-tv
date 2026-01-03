package com.anmfire.tv.data.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.regex.Pattern

object VideoExtractor {
    private val client = OkHttpClient()

    suspend fun extractVideoUrl(originalUrl: String): String {
        return withContext(Dispatchers.IO) {
            try {
                if (originalUrl.contains("blogger.com/video")) {
                    extractBloggerUrl(originalUrl)
                } else {
                    originalUrl
                }
            } catch (e: Exception) {
                e.printStackTrace()
                originalUrl // Fallback to original if extraction fails
            }
        }
    }

    private fun extractBloggerUrl(url: String): String {
        try {
            val request = Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .build()

            val response = client.newCall(request).execute()
            val html = response.body?.string() ?: return url

            // Regex global para capturar todas as URLs do googlevideo
            val regex = "https://[a-zA-Z0-9-]+\\.googlevideo\\.com/videoplayback[^\"'\\s\\\\]+"
            val pattern = Pattern.compile(regex)
            val matcher = pattern.matcher(html)
            
            val uniqueUrls = mutableSetOf<String>()
            
            while (matcher.find()) {
                val foundUrl = matcher.group()
                // Decodifica unicode se necessário
                val decodedUrl = unescapeJavaString(foundUrl)
                uniqueUrls.add(decodedUrl)
            }

            if (uniqueUrls.isNotEmpty()) {
                // Ordena por score decrescente (qualidade)
                val sortedUrls = uniqueUrls.sortedByDescending { getQualityScore(it) }
                return sortedUrls.first()
            }
            
            return url
        } catch (e: Exception) {
            e.printStackTrace()
            return url
        }
    }
    
    private fun getQualityScore(url: String): Int {
        val pattern = Pattern.compile("[?&]itag=(\\d+)")
        val matcher = pattern.matcher(url)
        if (!matcher.find()) return 0
        
        val itag = matcher.group(1)?.toIntOrNull() ?: 0
        
        return when (itag) {
            37 -> 100 // 1080p
            22 -> 90  // 720p
            59 -> 50  // 480p
            18 -> 40  // 360p
            else -> 10
        }
    }
    
    private fun unescapeJavaString(st: String): String {
        val sb = StringBuilder(st.length)
        var i = 0
        while (i < st.length) {
            val ch = st[i]
            if (ch == '\\') {
                if (i < st.length - 1) {
                    val nextChar = st[i + 1]
                    when (nextChar) {
                        'u' -> {
                            if (i + 5 < st.length) {
                                try {
                                    val code = st.substring(i + 2, i + 6).toInt(16)
                                    sb.append(code.toChar())
                                    i += 5
                                } catch (e: NumberFormatException) {
                                    sb.append('\\').append('u')
                                    i++
                                }
                            } else {
                                sb.append('\\').append('u')
                                i++
                            }
                        }
                        '"' -> { sb.append('"'); i++ }
                        '\\' -> { sb.append('\\'); i++ }
                        '/' -> { sb.append('/'); i++ }
                        'b' -> { sb.append('\b'); i++ }
                        'f' -> { sb.append('\u000c'); i++ }
                        'n' -> { sb.append('\n'); i++ }
                        'r' -> { sb.append('\r'); i++ }
                        't' -> { sb.append('\t'); i++ }
                        else -> {
                            // Unknown escape, just keep the character
                            sb.append(nextChar)
                            i++
                        }
                    }
                } else {
                    sb.append('\\')
                }
            } else {
                sb.append(ch)
            }
            i++
        }
        return sb.toString()
    }
}
