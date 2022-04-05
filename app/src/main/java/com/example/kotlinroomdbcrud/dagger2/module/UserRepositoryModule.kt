package com.example.kotlinroomdbcrud.dagger2.module

import com.example.kotlinroomdbcrud.dagger2.FirebaseRepository
import com.example.kotlinroomdbcrud.dagger2.RoomRepository
import com.example.kotlinroomdbcrud.dagger2.UserRepositoryInterface
import com.example.kotlinroomdbcrud.dagger2.myannotations.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UserRepositoryModule {

    //todo we had created the Obj as @Inject is not used
    /*@Named("firebase Obj")
    @Provides
    fun getFirebaseRepository(): UserRepositoryInterface {
        return FirebaseRepository()
    }*/

    //todo dagger had created Obj as @Inject is used
    @ActivityScope
    @Named("room Obj")
    @Provides
    fun getRoomRepository(roomRepository: RoomRepository): UserRepositoryInterface {
        return roomRepository
    }
}