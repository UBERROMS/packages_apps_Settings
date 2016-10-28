/*
 * Copyright (C) 2016 Benzo Rom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.advanced;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.content.Context;
import android.media.AudioSystem;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.ListPreference;
import android.support.v14.preference.SwitchPreference;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import org.omnirom.omnigears.preference.SystemCheckBoxPreference;
import com.android.internal.logging.MetricsProto.MetricsEvent;

import static android.provider.Settings.System.VOLUME_BUTTON_WAKE;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

import java.util.List;

public class AdvancedSettings extends SettingsPreferenceFragment implements OnPreferenceChangeListener {
    private static final String BUTTON_VOLUME_WAKE = "button_volume_wake_screen";
    private static final String SWAP_VOLUME_BUTTONS = "swap_volume_buttons";

    private CheckBoxPreference mVolumeWake;
    private CheckBoxPreference mSwapVolumeButtons;

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.TESTING;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ContentResolver resolver = getContentResolver();

        addPreferencesFromResource(R.xml.advanced_settings);

        mVolumeWake = (CheckBoxPreference) findPreference(BUTTON_VOLUME_WAKE);
        mVolumeWake.setChecked(Settings.System.getInt(resolver,
            Settings.System.VOLUME_BUTTON_WAKE, 0) != 0);

        mSwapVolumeButtons = (CheckBoxPreference) findPreference(SWAP_VOLUME_BUTTONS);
        mSwapVolumeButtons.setChecked(Settings.System.getInt(resolver,
            Settings.System.SWAP_VOLUME_BUTTONS, 0) != 0);
    }

    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == mVolumeWake) {
            boolean checked = ((CheckBoxPreference)preference).isChecked();
            Settings.System.putInt(getContentResolver(),
                    Settings.System.VOLUME_BUTTON_WAKE, checked ? 1:0);
            return true;
        } else if (preference == mSwapVolumeButtons) {
            boolean checked = ((CheckBoxPreference)preference).isChecked();
            Settings.System.putInt(getContentResolver(),
                    Settings.System.SWAP_VOLUME_BUTTONS, checked ? 1:0);
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        // Hello
        return true;
    }
}
