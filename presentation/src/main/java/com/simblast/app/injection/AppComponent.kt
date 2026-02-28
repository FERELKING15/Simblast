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

import com.simblast.app.common.QKApplication
import com.simblast.app.common.QkDialog
import com.simblast.app.common.util.QkChooserTargetService
import com.simblast.app.common.widget.AvatarView
import com.simblast.app.common.widget.PagerTitleView
import com.simblast.app.common.widget.PreferenceView
import com.simblast.app.common.widget.QkEditText
import com.simblast.app.common.widget.QkSwitch
import com.simblast.app.common.widget.QkTextView
import com.simblast.app.common.widget.RadioPreferenceView
import com.simblast.app.feature.backup.BackupController
import com.simblast.app.feature.blocking.BlockingController
import com.simblast.app.feature.blocking.manager.BlockingManagerController
import com.simblast.app.feature.blocking.messages.BlockedMessagesController
import com.simblast.app.feature.blocking.numbers.BlockedNumbersController
import com.simblast.app.feature.compose.editing.DetailedChipView
import com.simblast.app.feature.conversationinfo.injection.ConversationInfoComponent
import com.simblast.app.feature.settings.SettingsController
import com.simblast.app.feature.settings.about.AboutController
import com.simblast.app.feature.settings.swipe.SwipeActionsController
import com.simblast.app.feature.themepicker.injection.ThemePickerComponent
import com.simblast.app.feature.widget.WidgetAdapter
import com.simblast.app.injection.android.ActivityBuilderModule
import com.simblast.app.injection.android.BroadcastReceiverBuilderModule
import com.simblast.app.injection.android.ServiceBuilderModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    BroadcastReceiverBuilderModule::class,
    ServiceBuilderModule::class])
interface AppComponent {

    fun conversationInfoBuilder(): ConversationInfoComponent.Builder
    fun themePickerBuilder(): ThemePickerComponent.Builder

    fun inject(application: QKApplication)

    fun inject(controller: AboutController)
    fun inject(controller: BackupController)
    fun inject(controller: BlockedMessagesController)
    fun inject(controller: BlockedNumbersController)
    fun inject(controller: BlockingController)
    fun inject(controller: BlockingManagerController)
    fun inject(controller: SettingsController)
    fun inject(controller: SwipeActionsController)

    fun inject(dialog: QkDialog)

    fun inject(service: WidgetAdapter)

    /**
     * This can't use AndroidInjection, or else it will crash on pre-marshmallow devices
     */
    fun inject(service: QkChooserTargetService)

    fun inject(view: AvatarView)
    fun inject(view: DetailedChipView)
    fun inject(view: PagerTitleView)
    fun inject(view: PreferenceView)
    fun inject(view: RadioPreferenceView)
    fun inject(view: QkEditText)
    fun inject(view: QkSwitch)
    fun inject(view: QkTextView)

}
