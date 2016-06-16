/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.android.tools.idea.monitor.ui.visual.data;

import com.android.tools.adtui.model.SeriesData;
import gnu.trove.TLongArrayList;

public class MemoryTestDataGenerator extends TestDataGenerator<Long> {

  private TLongArrayList mData = new TLongArrayList();
  private boolean mUseFreeMemory;
  private Runtime mRuntime;

  public MemoryTestDataGenerator(boolean useFreeMemory) {
    mUseFreeMemory = useFreeMemory;
    mRuntime = Runtime.getRuntime();
  }

  @Override
  public SeriesData<Long> get(int index) {
    SeriesData<Long> data = new SeriesData<>();
    data.x = mTime.get(index) - mStartTime;
    data.value = mData.get(index);
    return data;
  }

  @Override
  public void generateData() {
    mTime.add(System.currentTimeMillis());
    if (mUseFreeMemory) {
      mData.add(mRuntime.freeMemory());
    } else {
      long usedMem = mRuntime.totalMemory() - mRuntime.freeMemory();
      mData.add(usedMem);
    }
  }
}