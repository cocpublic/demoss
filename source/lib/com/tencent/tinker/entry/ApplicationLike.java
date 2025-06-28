/*
 * Tencent is pleased to support the open source community by making Tinker available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tencent.tinker.entry;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme; // Added import for Theme

import com.tencent.tinker.anno.Keep;

/**
 * Represents the ApplicationLike object in Tinker.
 * This class is responsible for managing the application lifecycle
 * and providing access to application resources in a Tinker-enabled application.
 *
 * Based on the official Tinker source code.
 */
@Keep
public abstract class ApplicationLike implements ApplicationLifeCycle {
    private final Application application;
    private final Intent tinkerResultIntent;
    private final long applicationStartElapsedTime;
    private final long applicationStartMillisTime;
    private final int tinkerFlags;
    private final boolean tinkerLoadVerifyFlag;

    public ApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
                           long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super();
        this.application = application;
        this.tinkerFlags = tinkerFlags;
        this.tinkerLoadVerifyFlag = tinkerLoadVerifyFlag;
        this.applicationStartElapsedTime = applicationStartElapsedTime;
        this.applicationStartMillisTime = applicationStartMillisTime;
        this.tinkerResultIntent = tinkerResultIntent;
    }

    @Keep
    public Application getApplication() {
        return this.application;
    }

    /**
     * @return The intent containing the result of the Tinker patch process.
     */
    public final Intent getTinkerResultIntent() {
        return this.tinkerResultIntent;
    }

    /**
     * @return The Tinker flags used for initialization.
     */
    public final int getTinkerFlags() {
        return this.tinkerFlags;
    }

    /**
     * @return A boolean indicating if Tinker's load verification is enabled.
     */
    public final boolean getTinkerLoadVerifyFlag() {
        return this.tinkerLoadVerifyFlag;
    }

    /**
     * @return The time elapsed since the application started, in milliseconds.
     */
    public long getApplicationStartElapsedTime() {
        return applicationStartElapsedTime;
    }

    /**
     * @return The wall-clock time when the application started, in milliseconds.
     */
    public long getApplicationStartMillisTime() {
        return applicationStartMillisTime;
    }

    // Lifecycle methods from ApplicationLifeCycle interface
    @Override
    public void onCreate() {
        // Default empty implementation
    }

    @Override
    public void onLowMemory() {
        // Default empty implementation
    }

    @Override
    public void onTrimMemory(int level) {
        // Default empty implementation
    }

    @Override
    public void onTerminate() {
        // Default empty implementation
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Default empty implementation
    }

    @Override
    public void onBaseContextAttached(Context base) {
        // Default empty implementation
    }

    // Resource access methods, often overridden by Tinker
    @Keep
    public Resources getResources(Resources resources) {
        return resources;
    }

    @Keep
    public ClassLoader getClassLoader(ClassLoader classLoader) {
        return classLoader;
    }

    @Keep
    public AssetManager getAssets(AssetManager assetManager) {
       return assetManager;
    }

    @Keep
    public Object getSystemService(String name, Object service) {
        return service;
    }

    @Keep
    public Context getBaseContext(Context base) {
        return base;
    }

    @Keep
    public Theme getTheme(Theme theme) { // Corrected from Resources$Theme to Resources.Theme
        return theme;
    }

    /**
     * Related to Meizu's night mode. Tinker retains this for compatibility.
     * @return Default value is 1.
     */
    @Keep
    public int mzNightModeUseOf() {
        // Return 1 for default according to MeiZu's announcement.
        return 1;
    }
}
