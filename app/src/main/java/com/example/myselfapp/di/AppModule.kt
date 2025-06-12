package com.example.myselfapp.di
/*
nama : muhammad taufik iqbal
kelas : P.Andro4
nim : 10122336
tanggal pengerjaan terkahir 12-6-2025
 */
import com.example.myselfapp.data.local.dao.DailyActivityDao
import com.example.myselfapp.data.local.dao.FriendDao
import com.example.myselfapp.data.local.dao.GalleryDao
import com.example.myselfapp.data.local.dao.MusicVideoDao
import com.example.myselfapp.data.local.dao.ProfileDao
import com.example.myselfapp.data.repository.DailyActivityRepository
import com.example.myselfapp.data.repository.DummyDataInitializer
import com.example.myselfapp.data.repository.FriendRepository
import com.example.myselfapp.data.repository.GalleryRepository
import com.example.myselfapp.data.repository.MusicVideoRepository
import com.example.myselfapp.data.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideProfilerepository(
        profilDao: ProfileDao,
        dummyDataIntitializer : DummyDataInitializer
    ) : ProfileRepository {
        return ProfileRepository(profilDao, dummyDataIntitializer)
    }
    @Singleton
    @Provides
    fun provideDailyActivityRepository(
        dailyActivityDao: DailyActivityDao,
        dummyDataInitializer: DummyDataInitializer
    ): DailyActivityRepository {
        return DailyActivityRepository(dailyActivityDao, dummyDataInitializer)
    }

    @Singleton
    @Provides
    fun provideFriendRepository(
        friendDao: FriendDao,
        dummyDataInitializer: DummyDataInitializer
    ): FriendRepository {
        return FriendRepository(friendDao, dummyDataInitializer)
    }

    @Singleton
    @Provides
    fun provideGalleryRepository(
        galleryDao: GalleryDao,
        dummyDataInitializer: DummyDataInitializer
    ): GalleryRepository {
        return GalleryRepository(galleryDao, dummyDataInitializer)
    }

    @Singleton
    @Provides
    fun provideMusicVideoRepository(
        musicVideoDao: MusicVideoDao,
        dummyDataInitializer: DummyDataInitializer
    ): MusicVideoRepository {
        return MusicVideoRepository(musicVideoDao, dummyDataInitializer)
    }
}