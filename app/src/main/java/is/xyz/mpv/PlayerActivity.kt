class PlayerActivity : AppCompatActivity() {

    // --- (Kode bawaan mpv-android lainnya di atas...) ---

    // 1. Fungsi Pencarian Subtitle yang sudah dibuat sebelumnya
    private fun searchAndApplySubtitle(videoTitle: String) {
        // ... isi kodenya ...
    }

    // 2. TARUH KODE downloadAndLoadSubtitle DI SINI (Sejajar dengan fungsi lainnya)
    private fun downloadAndLoadSubtitle(subtitleItem: SubtitleItem) {
        val api = SubsourceApi.create()
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Menyertakan API Key saat mengunduh file dari Subsource
                val response = api.downloadFile(
                    apiKey = SubsourceApi.MY_API_KEY, 
                    fileUrl = subtitleItem.downloadUrl
                )
                
                if (response.isSuccessful && response.body() != null) {
                    val file = File(cacheDir, subtitleItem.filename)
                    file.writeBytes(response.body()!!.bytes())

                    withContext(Dispatchers.Main) {
                        // Masukkan subtitle ke pemutar MPV secara real-time
                        MPVLib.command(arrayOf("sub-add", file.absolutePath, "select"))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

} // <--- Batas akhir kelas PlayerActivity
