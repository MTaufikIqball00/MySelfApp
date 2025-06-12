package com.example.myselfapp.data.repository

import com.example.myselfapp.R
import com.example.myselfapp.util.Constants
import com.example.myselfapp.data.local.dao.DailyActivityDao
import com.example.myselfapp.data.local.dao.FriendDao
import com.example.myselfapp.data.local.dao.GalleryDao
import com.example.myselfapp.data.local.dao.MusicVideoDao
import com.example.myselfapp.data.local.dao.ProfileDao
import com.example.myselfapp.data.local.entity.DailyActivityEntity
import com.example.myselfapp.data.local.entity.FriendEntity
import com.example.myselfapp.data.local.entity.GalleryItemEntity
import com.example.myselfapp.data.local.entity.MusicVideoEntity
import com.example.myselfapp.data.local.entity.ProfileEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DummyDataInitializer @Inject constructor(
    private val profileDao: ProfileDao,
    private val dailyActivityDao: DailyActivityDao,
    private val friendDao: FriendDao,
    private val galleryDao: GalleryDao,
    private val musicVideoDao: MusicVideoDao
) {

    @Volatile private var isProfileDataInserted = false
    @Volatile private var isDailyActivityDataInserted = false
    @Volatile private var isFriendDataInserted = false
    @Volatile private var isGalleryDataInserted = false
    @Volatile private var isMusicVideoDataInserted = false

    suspend fun insertProfileData() {
        withContext(Dispatchers.IO) {
            if (!isProfileDataInserted) {
                if (profileDao.getProfile() == null) {
                    val profile = ProfileEntity(
                        id = 1,
                        photoResId = R.drawable.profile_1,
                        name = "Muhammad Taufik Iqbal",
                        description = "Seorang pengembang Android yang bersemangat dengan kecintaan pada teknologi dan desain UI/UX. Suka belajar hal baru dan berbagi pengetahuan.",
                        hobbies = listOf("Ngoding", "Push Rank Sampe Immortal"),
                        makes = listOf("Aplikasi Android", "Kopi Susu", "Rencana Perjalanan"),
                        interests = listOf("Kecerdasan Buatan", "Blockchain"),
                        dreams = listOf("Trilioner")
                    )
                    profileDao.insertProfile(profile)
                    isProfileDataInserted = true
                }
            }
        }
    }

    suspend fun insertDailyActivityData() {
        withContext(Dispatchers.IO) {
            if (!isDailyActivityDataInserted) {
                if (dailyActivityDao.getDailyActivityCount() == 0) {
                    val activities = listOf(
                        DailyActivityEntity(title = "Lari Pagi", description = "Lari 5km di taman dekat rumah."),
                        DailyActivityEntity(title = "Belajar Kotlin Coroutines", description = "Menyelesaikan modul lanjutan tentang Coroutines."),
                        DailyActivityEntity(title = "Rapat Tim", description = "Diskusi progres proyek MySelfApp."),
                        DailyActivityEntity(title = "Memasak Makan Malam", description = "Mencoba resep baru: Pasta Aglio Olio.")
                    )
                    dailyActivityDao.insertDailyActivities(activities)
                    isDailyActivityDataInserted = true
                }
            }
        }
    }

    suspend fun insertFriendListData() {
        withContext(Dispatchers.IO) {
            if (!isFriendDataInserted) {
                if (friendDao.getFriendCount() == 0) {
                    val friends = listOf(
                        FriendEntity(name = "Joko", imageUrl = Constants.DUMMY_FRIEND_ALICE_URL, phoneNumber = "08123456789"),
                        FriendEntity(name = "Osas", imageUrl = Constants.DUMMY_FRIEND_BOB_URL, phoneNumber = "08987654321"),
                        FriendEntity(name = "Susi", imageUrl = Constants.DUMMY_FRIEND_CHARLIE_URL, phoneNumber = "081122334455"),
                        FriendEntity(name = "Tono", imageUrl = Constants.DUMMY_FRIEND_DIANA_URL, phoneNumber = "087766554433"),
                        FriendEntity(name = "Ynto", imageUrl = Constants.DUMMY_FRIEND_DIANA_URL, phoneNumber = "1234558856"),
                    )
                    friendDao.insertFriends(friends)
                    isFriendDataInserted = true
                }
            }
        }
    }

    suspend fun insertGalleryData() {
        withContext(Dispatchers.IO) {
            if (!isGalleryDataInserted) {
                if (galleryDao.getGalleryItemCount() == 0) {
                    val galleryItems = listOf(
                        GalleryItemEntity(imageResId = R.drawable.gambar_1, description = "Villa"),
                        GalleryItemEntity(imageResId = R.drawable.profile_1, description = "HooMan"),

                    )
                    galleryDao.insertGalleryItems(galleryItems)
                    isGalleryDataInserted = true
                }
            }
        }
    }

    suspend fun insertMusicVideoData() {
        withContext(Dispatchers.IO) {
            if (!isMusicVideoDataInserted) {
                if (musicVideoDao.getMusicVideoCount() == 0) {
                    val musicVideos = listOf(
                        MusicVideoEntity(
                            title = "Abracadabra",
                            artist = "Lady Gaga",
                            thumbnailUrl = Constants.DUMMY_MUSIC_THUMB_1_URL,
                            contentUrl = "https://youtu.be/vBynw9Isr28?si=zEJcgj3Buu68Qwrk", // Contoh URL YouTube, ganti ID asli
                            type = "music",
                            album = "Album Santai",
                            genre = "Pop",
                            duration = 430000,
                            dateAdded = System.currentTimeMillis(),
                            mimeType = "video/mp4"
                        ),
                        MusicVideoEntity(
                            title = "Tutorial Android Jetpack Compose",
                            artist = "Phillip Lacknar",
                            thumbnailUrl = Constants.DUMMY_VIDEO_THUMB_1_URL,
                            contentUrl = "https://youtu.be/6_wK_Ud8--0?si=YW1yHKwZAc4F92ON", // Contoh URL YouTube, ganti ID asli
                            type = "video",
                            album = null,
                            genre = "Edukasi",
                            duration = 4800000,
                            dateAdded = System.currentTimeMillis(),
                            mimeType = "video/mp4"
                        ),
                        MusicVideoEntity(
                            title = "Bird of Feather",
                            artist = "Billie Eilish",
                            thumbnailUrl = Constants.DUMMY_MUSIC_THUMB_2_URL, // Menggunakan thumbnail dari Constants
                            contentUrl = "https://youtu.be/V9PVRfjEBTI?si=eCIU5PxmJtdShKGR", // URL tanpa markdown
                            type = "music",
                            album = "Hit Me Hard and Soft", // Nama album yang benar
                            genre = "Pop",
                            duration = 351000,
                            dateAdded = System.currentTimeMillis(),
                            mimeType = "audio/mpeg"
                        ),
                        MusicVideoEntity(
                            title = "Sabo Encounters Imu",
                            artist = "One Piece",
                            thumbnailUrl = Constants.DUMMY_VIDEO_THUMB_2_URL, // Contoh thumbnail lain
                            contentUrl = "https://youtu.be/qQIsvIOQS8U?si=7zPEC2-qkJhQlgaY", // Contoh URL YouTube lain
                            type = "video",
                            album = null,
                            genre = "Action",
                            duration = 13900,
                            dateAdded = System.currentTimeMillis(),
                            mimeType = "video/mp4"
                        )
                    )
                    musicVideoDao.insertMusicVideos(musicVideos)
                    isMusicVideoDataInserted = true
                }
            }
        }
    }
}