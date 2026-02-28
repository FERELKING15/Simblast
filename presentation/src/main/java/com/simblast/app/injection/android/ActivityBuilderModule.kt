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
package com.simblast.app.injection.android

import com.simblast.app.feature.backup.BackupActivity
import com.simblast.app.feature.blocking.BlockingActivity
import com.simblast.app.feature.compose.ComposeActivity
import com.simblast.app.feature.compose.ComposeActivityModule
import com.simblast.app.feature.contacts.ContactsActivity
import com.simblast.app.feature.contacts.ContactsActivityModule
import com.simblast.app.feature.conversationinfo.ConversationInfoActivity
import com.simblast.app.feature.gallery.GalleryActivity
import com.simblast.app.feature.gallery.GalleryActivityModule
import com.simblast.app.feature.main.MainActivity
import com.simblast.app.feature.main.MainActivityModule
import com.simblast.app.feature.notificationprefs.NotificationPrefsActivity
import com.simblast.app.feature.notificationprefs.NotificationPrefsActivityModule
import com.simblast.app.feature.plus.PlusActivity
import com.simblast.app.feature.plus.PlusActivityModule
import com.simblast.app.feature.qkreply.QkReplyActivity
import com.simblast.app.feature.qkreply.QkReplyActivityModule
import com.simblast.app.feature.scheduled.ScheduledActivity
import com.simblast.app.feature.scheduled.ScheduledActivityModule
import com.simblast.app.feature.settings.SettingsActivity
import com.simblast.app.injection.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PlusActivityModule::class])
    abstract fun bindPlusActivity(): PlusActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindBackupActivity(): BackupActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ComposeActivityModule::class])
    abstract fun bindComposeActivity(): ComposeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ContactsActivityModule::class])
    abstract fun bindContactsActivity(): ContactsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindConversationInfoActivity(): ConversationInfoActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [GalleryActivityModule::class])
    abstract fun bindGalleryActivity(): GalleryActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [NotificationPrefsActivityModule::class])
    abstract fun bindNotificationPrefsActivity(): NotificationPrefsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [QkReplyActivityModule::class])
    abstract fun bindQkReplyActivity(): QkReplyActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ScheduledActivityModule::class])
    abstract fun bindScheduledActivity(): ScheduledActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindSettingsActivity(): SettingsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindBlockingActivity(): BlockingActivity

}
