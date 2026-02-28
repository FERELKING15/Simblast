/*
 * Copyright (C) 2017 Moez Bhatti <moez.bhatti@gmail.com>
 *
 * This file is part of QKSMS.
 *
 * QKSMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QKSMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with QKSMS.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.simblast.app.injection

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.simblast.app.blocking.BlockingClient
import com.simblast.app.blocking.BlockingManager
import com.simblast.app.common.ViewModelFactory
import com.simblast.app.common.util.BillingManagerImpl
import com.simblast.app.common.util.NotificationManagerImpl
import com.simblast.app.common.util.ShortcutManagerImpl
import com.simblast.app.feature.conversationinfo.injection.ConversationInfoComponent
import com.simblast.app.feature.themepicker.injection.ThemePickerComponent
import com.simblast.app.listener.ContactAddedListener
import com.simblast.app.listener.ContactAddedListenerImpl
import com.simblast.app.manager.ActiveConversationManager
import com.simblast.app.manager.ActiveConversationManagerImpl
import com.simblast.app.manager.AlarmManager
import com.simblast.app.manager.AlarmManagerImpl
import com.simblast.app.manager.AnalyticsManager
import com.simblast.app.manager.AnalyticsManagerImpl
import com.simblast.app.manager.BillingManager
import com.simblast.app.manager.ChangelogManager
import com.simblast.app.manager.ChangelogManagerImpl
import com.simblast.app.manager.KeyManager
import com.simblast.app.manager.KeyManagerImpl
import com.simblast.app.manager.NotificationManager
import com.simblast.app.manager.PermissionManager
import com.simblast.app.manager.PermissionManagerImpl
import com.simblast.app.manager.RatingManager
import com.simblast.app.manager.ReferralManager
import com.simblast.app.manager.ReferralManagerImpl
import com.simblast.app.manager.ShortcutManager
import com.simblast.app.manager.WidgetManager
import com.simblast.app.manager.WidgetManagerImpl
import com.simblast.app.mapper.CursorToContact
import com.simblast.app.mapper.CursorToContactGroup
import com.simblast.app.mapper.CursorToContactGroupImpl
import com.simblast.app.mapper.CursorToContactGroupMember
import com.simblast.app.mapper.CursorToContactGroupMemberImpl
import com.simblast.app.mapper.CursorToContactImpl
import com.simblast.app.mapper.CursorToConversation
import com.simblast.app.mapper.CursorToConversationImpl
import com.simblast.app.mapper.CursorToMessage
import com.simblast.app.mapper.CursorToMessageImpl
import com.simblast.app.mapper.CursorToPart
import com.simblast.app.mapper.CursorToPartImpl
import com.simblast.app.mapper.CursorToRecipient
import com.simblast.app.mapper.CursorToRecipientImpl
import com.simblast.app.mapper.RatingManagerImpl
import com.simblast.app.repository.BackupRepository
import com.simblast.app.repository.BackupRepositoryImpl
import com.simblast.app.repository.BlockingRepository
import com.simblast.app.repository.BlockingRepositoryImpl
import com.simblast.app.repository.ContactRepository
import com.simblast.app.repository.ContactRepositoryImpl
import com.simblast.app.repository.ConversationRepository
import com.simblast.app.repository.ConversationRepositoryImpl
import com.simblast.app.repository.MessageRepository
import com.simblast.app.repository.MessageRepositoryImpl
import com.simblast.app.repository.ScheduledMessageRepository
import com.simblast.app.repository.ScheduledMessageRepositoryImpl
import com.simblast.app.repository.SyncRepository
import com.simblast.app.repository.SyncRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [
    ConversationInfoComponent::class,
    ThemePickerComponent::class])
class AppModule(private var application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    fun provideContentResolver(context: Context): ContentResolver = context.contentResolver

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideRxPreferences(preferences: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(preferences)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    // Listener

    @Provides
    fun provideContactAddedListener(listener: ContactAddedListenerImpl): ContactAddedListener = listener

    // Manager

    @Provides
    fun provideBillingManager(manager: BillingManagerImpl): BillingManager = manager

    @Provides
    fun provideActiveConversationManager(manager: ActiveConversationManagerImpl): ActiveConversationManager = manager

    @Provides
    fun provideAlarmManager(manager: AlarmManagerImpl): AlarmManager = manager

    @Provides
    fun provideAnalyticsManager(manager: AnalyticsManagerImpl): AnalyticsManager = manager

    @Provides
    fun blockingClient(manager: BlockingManager): BlockingClient = manager

    @Provides
    fun changelogManager(manager: ChangelogManagerImpl): ChangelogManager = manager

    @Provides
    fun provideKeyManager(manager: KeyManagerImpl): KeyManager = manager

    @Provides
    fun provideNotificationsManager(manager: NotificationManagerImpl): NotificationManager = manager

    @Provides
    fun providePermissionsManager(manager: PermissionManagerImpl): PermissionManager = manager

    @Provides
    fun provideRatingManager(manager: RatingManagerImpl): RatingManager = manager

    @Provides
    fun provideShortcutManager(manager: ShortcutManagerImpl): ShortcutManager = manager

    @Provides
    fun provideReferralManager(manager: ReferralManagerImpl): ReferralManager = manager

    @Provides
    fun provideWidgetManager(manager: WidgetManagerImpl): WidgetManager = manager

    // Mapper

    @Provides
    fun provideCursorToContact(mapper: CursorToContactImpl): CursorToContact = mapper

    @Provides
    fun provideCursorToContactGroup(mapper: CursorToContactGroupImpl): CursorToContactGroup = mapper

    @Provides
    fun provideCursorToContactGroupMember(mapper: CursorToContactGroupMemberImpl): CursorToContactGroupMember = mapper

    @Provides
    fun provideCursorToConversation(mapper: CursorToConversationImpl): CursorToConversation = mapper

    @Provides
    fun provideCursorToMessage(mapper: CursorToMessageImpl): CursorToMessage = mapper

    @Provides
    fun provideCursorToPart(mapper: CursorToPartImpl): CursorToPart = mapper

    @Provides
    fun provideCursorToRecipient(mapper: CursorToRecipientImpl): CursorToRecipient = mapper

    // Repository

    @Provides
    fun provideBackupRepository(repository: BackupRepositoryImpl): BackupRepository = repository

    @Provides
    fun provideBlockingRepository(repository: BlockingRepositoryImpl): BlockingRepository = repository

    @Provides
    fun provideContactRepository(repository: ContactRepositoryImpl): ContactRepository = repository

    @Provides
    fun provideConversationRepository(repository: ConversationRepositoryImpl): ConversationRepository = repository

    @Provides
    fun provideMessageRepository(repository: MessageRepositoryImpl): MessageRepository = repository

    @Provides
    fun provideScheduledMessagesRepository(repository: ScheduledMessageRepositoryImpl): ScheduledMessageRepository = repository

    @Provides
    fun provideSyncRepository(repository: SyncRepositoryImpl): SyncRepository = repository

}