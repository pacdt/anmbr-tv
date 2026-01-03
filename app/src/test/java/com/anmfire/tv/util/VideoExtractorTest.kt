package com.anmfire.tv.util

import org.junit.Test
import org.junit.Assert.*
import java.util.regex.Pattern

class VideoExtractorTest {

    @Test
    fun testRegexExtraction() {
        val html = """
            <html>
            <script>
            var VIDEO_CONFIG = {
                "streams": [
                    {"play_url": "https://r1---sn-cx5-n8vl.googlevideo.com/videoplayback?expire=1703&itag=18&source=blogger&ip=200.200.200.200"},
                    {"play_url": "https://r1---sn-cx5-n8vl.googlevideo.com/videoplayback?expire=1703&itag=22&source=blogger&ip=200.200.200.200"}
                ]
            };
            </script>
            </html>
        """

        val regex = "https://[a-zA-Z0-9-]+\\.googlevideo\\.com/videoplayback[^\"'\\s\\\\]+"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(html)

        val foundUrls = mutableListOf<String>()
        while (matcher.find()) {
            foundUrls.add(matcher.group())
        }

        assertEquals(2, foundUrls.size)
        // Check if urls are extracted correctly
        assertTrue(foundUrls.any { it.contains("itag=18") })
        assertTrue(foundUrls.any { it.contains("itag=22") })
    }

    @Test
    fun testQualityScore() {
        val url1080 = "https://example.com?itag=37"
        val url720 = "https://example.com?itag=22"
        val url480 = "https://example.com?itag=59"
        val url360 = "https://example.com?itag=18"
        val urlUnknown = "https://example.com?itag=999"

        assertEquals(100, getQualityScore(url1080))
        assertEquals(90, getQualityScore(url720))
        assertEquals(50, getQualityScore(url480))
        assertEquals(40, getQualityScore(url360))
        assertEquals(10, getQualityScore(urlUnknown))
    }
    
    @Test
    fun testSorting() {
         val urls = listOf(
             "https://example.com?itag=18", // 360p
             "https://example.com?itag=37", // 1080p
             "https://example.com?itag=22"  // 720p
         )
         
         val sorted = urls.sortedByDescending { getQualityScore(it) }
         
         assertEquals("https://example.com?itag=37", sorted[0])
         assertEquals("https://example.com?itag=22", sorted[1])
         assertEquals("https://example.com?itag=18", sorted[2])
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
}
