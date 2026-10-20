private fun searchAndApplySubtitle(videoTitle: String) {
    val api = SubsourceApi.create()
    lifecycleScope.launch(Dispatchers.IO) {
        try {
            val response = api.searchSubtitles(query = videoTitle)
            if (response.isSuccessful && response.body() != null) {
                val subtitles = response.body()!!
                // Tampilkan daftar subtitle ke dalam Dialog/BottomSheet di UI Thread
                withContext(Dispatchers.Main) {
                    showSubtitleChooserDialog(subtitles)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
