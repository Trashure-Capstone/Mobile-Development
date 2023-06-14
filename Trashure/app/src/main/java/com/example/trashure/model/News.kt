package com.example.trashure.model

import com.example.trashure.R

data class News(
    val id : Long,
    val image : Int,
    val title : String,
    val description: String,
    val date : String,
)

val dummyNews = listOf(
    News(
        0,
        R.drawable.news_01 ,
        "Pabrik Daur Ulang Botol di Kendal Jawa Tengah Turut Kurangi Dampak Limbah Plastik",
        "Polusi yang diakibatkan limbah plastik merupakan masalah pelik bagi semua negara. Setiap tahun, sekitar 8 hingga 12 juta ton plastik berakhir di lautan.  \n" +
                "\n" +
                "Menurut data statistik persampahan domestik Indonesia, produksi sampah plastik di dalam negeri mencapai 5.4 juta ton per tahun atau 14 persen dari total produksi sampah. \n" +
                "\n" +
                "Untuk menjawab persoalan limbah plastik itu, diperlukan kolaborasi dari berbagai pemangku kepentingan dengan menggunakan pendekatan ekonomi sirkuler.  \n" +
                "\n" +
                "Salah satu kolaborasi terbaru untuk menjawab persoalan limbah plastik di Indonesia adalah pembangunan pabrik daur ulang botol PET di Kawasan Industri Kendal, Jawa Tengah. ",
        "7 Juni 2023"),
    News(
        1,
        R.drawable.news_04 ,
        "10 Alasan Kenapa Orang Masih Sering Buang Sampah Sembarangan",
        "Indonesia sendiri menghasilkan 11.330 ton sampah organik maupun anorganik setiap harinya. Bayangkan jika sampah-sampah ini tidak dapat dikelola dengan baik akibat dibuang secara sembarangan.\n" +
                "\n" +
                "Sebelum kita membahas lebih jauh bagaimana solusi sampah di Indonesia, kita perlu memahami tentang alasan kenapa orang masih sering membuang sampah sembarangan.\n" +
                "\n" +
                "1. Anggapan Bahwa Sampah Bukanlah Barang yang Bernilai Sehingga Tidak Memerlukan Perhatian Khusus\n" +
                "\n" +
                "Hal ini dikemukakan oleh Sosiolog dari Universitas Sebelas Maret (UNS), Drajat Tri Kartono. Sampah yang dikelola dan diolah dengan tepat maka dapat menjadi sesuatu yang lebih bernilai seperti kompos ataupun barang-barang daur ulang lainnya.\n" +
                "\n" +
                "2. Merasa Bahwa Sampah Bukan Tanggungjawab Pribadi\n" +
                "Selain itu, Bapak Drajat juga mengungkapkan bahwa orang yang membuang sampah sembarangan di tempat umum kerap kali berpikir bahwa itu bukanlah tanggungjawabnya, melainkan tugas dan kewajiban dari petugas kebersihan ataupun pemerintah setempat. Ia tidak menyadari bahwa menjaga kebersihan lingkungan adalah tanggungjawab bersama, tidak sekadar salah satu pihak saja.\n" +
                "\n" +
                "3. Pola Pikir dan Kebiasaan Membuang Sampah Sembarangan yang Sudah Mendarah Daging\n" +
                "\n" +
                "Seperti halnya menganggap membuang sampah pada tempatnya bukanlah kewajiban pribadi, kebiasaan membuang sampah sembarangan yang melekat pada diri seseorang merupakan sesuatu yang sulit untuk diubah seperti yang telah dipaparkan oleh Ghianina Armand.\n" +
                "\n" +
                "Pola pikir ini ini semakin sulit untuk diubah karena manusia mudah terpengaruh orang-orang dan lingkungan sekitar, jika di sekitarnya sering membuang sampah sembarangan, maka orang cenderung akan mengikuti perilaku tersebut.\n" +
                "\n" +
                "4. Tidak Peduli Terhadap Perilaku Sendiri\n" +
                "Ibu Ghianina juga menjelaskan bahwa salah satu alasan mengapa orang masih sering membuang sampah sembarangan ialah karena tidak peduli pada perilakunya sendiri, bersikap acuh tak acuh pada lingkungan, serta cenderung egois. Padahal, bagaimana perilaku kita terhadap lingkungan akan menjadi bagaimana kita mempresentasikan citra diri.\n" +
                "\n" +
                "5. Yakin Bahwa Tidak Ada Konsekuensi Membuang Sampah Sembarangan\n" +
                "Masih banyak orang percaya bahwa tidak akan terjadi apa-apa jika ‘sekadar’ melemparkan sampah ke pinggir jalan.\n" +
                "\n" +
                "“Tidak ada hukuman yang akan menanti,” begitulah pikirnya.\n" +
                "\n" +
                "Namun coba bayangkan jika ada 225 juta orang Indonesia yang juga berpikiran demikian, maka sampah akan sulit untuk dikelola sehingga dapat menimbulkan bencana seperti banjir yang tiap tahunnya menjadi momok di negeri kita.\n" +
                "\n" +
                "Sebenarnya, Indonesia telah mengatur terkait pengelolaan sampah yang tertuang dalam Undang-Undang (UU) Nomor 18 Tahun 2008 tentang Pengelolaan Sampah. Pada Pasal 29 Ayat (1), disebutkan bahwa setiap orang dilarang membuang sampah tidak pada tempat yang telah ditentukan dan disediakan. Terkait hal tersebut, UU juga menyebutkan bahwa akan diatur lebih lanjut dalam peraturan daerah kabupaten/kota.\n" +
                "\n" +
                "6. Hukuman atau Denda yang Tidak Efektif\n" +
                "Meskipun negeri kita telah mengatur bagaimana kita harus mengurus sampah setidaknya untuk tidak membuangnya secara sembarangan, nyatanya hukuman atau denda yang berlaku di tiap kabupaten/kota dirasa masih kurang efektif di beberapa area, misalnya aturan jam membuang sampah hanya efektif pada area yang mempunyai CCTV (Closed Circuit Television). Namun, di beberapa tempat seperti trotoar, orang masih bebas membuang sampah sembarangan karena hukuman atau denda yang masih tidak efektif untuk menertibkan setiap orang yang membuang sampah sembarangan.\n" +
                "\n" +
                "7. Meniru Apa Yang Dilakukan Oleh Kebanyakan Orang\n" +
                "Penelitian telah membuktikan korelasi antara keberadaan sampah di suatu area tertentu dan pembuangan sampah yang disengaja atau tidak disengaja di tempat tersebut. Hal ini menunjukkan bahwa ketika seseorang melihat sampah menumpuk di suatu tempat, itu memberinya kesan bahwa itu adalah tempat yang tepat untuk membuang barang. Dalam kebanyakan kasus, itu tidak disengaja atau disengaja.\n" +
                "\n" +
                "Lebih lanjut, jika suatu area sudah sangat berserakan, orang cenderung menambahkan lebih banyak sampah, sementara semakin bersih suatu area, semakin kecil kemungkinan orang mengganggu pemandangan dengan membuang sampah sembarangan menurut Robert Cialdini, Profesor Psikologi Arizona State University.\n" +
                "\n" +
                "8. Rasa Malas untuk Membuang Sampah Pada Tempatnya\n" +
                "Rasa malas telah melahirkan budaya membuang sampah sembarangan. Biasanya, orang menjadi terlalu malas dan tidak mau membuang sampah pada tempatnya. Kita sering melihat orang yang tinggal di dekat sungai dengan mudahnya membuang sampah ke sungai tersebut daripada mengelolanya pada tempat yang tepat.\n" +
                "\n" +
                "9. Kurangnya Tempat Sampah\n" +
                "Seringkali, orang membuang sampah sembarangan hanya karena tidak ada tempat sampah di dekatnya. Daripada tidak nyaman membawa sampah, orang memutuskan lebih mudah untuk meninggalkannya, menurut penelitian yang dilakukan oleh Allegheny Front.\n" +
                "\n" +
                "4\n" +
                "\n" +
                "Di beberapa tempat, misalnya saat festival kerap kali sulit untuk menemukan tempat sampah sehingga orang dengan mudahnya membuang sampah di tengah keramaian. Selain itu, ada tempat yang memiliki tempat sampah, namun tidak dikelola dengan baik sehingga kontainer kelebihan muatan dan akhirnya berserakan karena ditiup angin atau karena diaduk-aduk hewan liar.\n" +
                "\n" +
                "10. Masih Kurangnya Pemahaman Terkait Akibat Sampah yang Tidak Dikelola\n" +
                "Banyak masyarakat yang tidak mengetahui bahwa berbagai tindakan membuang sampah sembarangan berdampak negatif terhadap lingkungan. Akibatnya, masyarakat terus membuang sampah sembarangan tanpa memikirkan dampak lingkungan yang ditimbulkannya. Misalnya perilaku membuang sampah ke sungai yang dapat mengakibatkan meluapnya air saat musim penghujan.\n" +
                "\n" +
                "Nah, itulah beberapa alasan mengapa masih banyak orang yang membuang sampah sembarangan. Biasanya, mereka yang masih mengabaikan pengelolaan sampah di lingkungannya ini karena tidak terdampak langsung dari situasi penimbunan sampah seperti banjir, bau yang menyengat, atau paparan kuman/ bakteri dari sampah yang berpotensi menjadi bibit penyakit orang di sekitarnya.\n" +
                "\n" +
                "Setelah mengetahui alasannya, apakah teman-teman SDGs Youth Hub punya saran agar orang nggak buang sampah sembarangan lagi?",
        "23 Februari 2023"),
    News(
        2,
        R.drawable.news_05 ,
        "Pentingnya Kesadaran Masyarakat Akan Kebersihan",
        "Kesadaran Masyarakat terhadap kerbesihan dalam agama disebutkan Orang mengatakan bahwa kebersihan merupakan sebagian dari iman. Kebersihan adalah hal yang mutlak dibutuhkan oleh setiap orang yang tinggal di dalam lingkungan hidup. Lingkungan hidup yang bersih, menciptakan rasa kenyamanan serta kesehatan bagi setiap individu yang tinggal di dalamnya.\n" +
                "\n" +
                "Bayangkan saja, seandainya lingkungan hidup yang Anda tinggali kotor, maka rasa tidak nyaman akan menghampiri. Kemungkinan timbulnya berbagai penyakit pun lebih besar dibandingkan dengan lingkungan hidup yang bersih.\n" +
                "\n" +
                "Sebagai contoh, rumah Anda kotor. Banyak sampah dan genangan air di sekitar rumah Anda. Maka, nyamuk-nyamuk demam berdarah dapat berkembang biak dengan subur. Menyebabkan anggota keluarga Anda, bahkan orang-orang yang tinggal di sekitar Anda memiliki peluang besar terkena penyakit demam berdarah.\n" +
                "\n" +
                "Dalam lingkungan hidup, penting untuk menjaga kesadaran dalam menjaga kebersihan.\n" +
                "\n" +
                "Menjaga Kebersihan Dimulai dari Diri Sendiri\n" +
                "\n" +
                "Kebersihan diri merupakan cermin kehidupan seseorang. Apakah orang tersebut tipe pemalas, atau tipe orang yang rajin. Orang yang malas membersihkan diri sendiri, bagaimana mungkin peduli terhadap kebersihan lingkungan hidup yang ada di sekitarnya?\n" +
                "\n" +
                "Menjaga kebersihan diri sendiri merupakan hal yang sangat pribadi dan menjadi hak Anda untuk hidup bersih dan sehat, seperti: mandi paling tidak dua kali sehari, selalu mengenakan pakaian bersih, mencuci tangan sebelum makan atau menyentuh makanan, serta hal lain yang dapat menimbulkan rasa nyaman dan sehat di diri kita sendiri.\n" +
                "\n" +
                "Menjaga Kebersihan di Lingkungan Keluarga\n" +
                "\n" +
                "Hal yang besar dimulai dari hal kecil. Begitu juga dengan menjaga kebersihan. Setelah menjaga kebersihan dari diri sendiri, maka kebersihan di lingkungan keluarga pun dapat tercipta. Setiap anggota keluarga berperan dalam menjaga kebersihan dan kesehatan masing-masing anggota keluarganya.",
        "19 November 2022"),
    News(
        3,
        R.drawable.news_02 ,
        "WWF sebut perlu lebih banyak aksi global untuk tangani polusi plastik",
        "Jenewa(Antara), lebih banyak aksi untuk mengatasi sampah plastik, demikian disampaikan oleh kepala kebijakan plastik global world wide fund for nature(WWF) Eirik Lindabjerk",
        "29 Mei 2023"),
)