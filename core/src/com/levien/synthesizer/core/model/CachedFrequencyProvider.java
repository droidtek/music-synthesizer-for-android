/*
 * Copyright 2010 Google Inc.
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

package com.levien.synthesizer.core.model;

/**
 * Convenient base class for any module that only wants to compute a new log frequency once at each
 * step of time.
 */
public abstract class CachedFrequencyProvider implements FrequencyProvider {
  public CachedFrequencyProvider() {
    cachedTime_ = -1;
  }

  /**
   * Caching implementation of the FrequencyProvider interface.
   * @param time - current synthesis time.
   */
  final public double getLogFrequency(SynthesisTime time) {
    if (time.getAbsoluteTime() != cachedTime_) {
      cachedTime_ = time.getAbsoluteTime();
      cachedValue_ = computeLogFrequency(time);
    }
    return cachedValue_;
  }

  /**
   * Method for subclasses to implement to compute a new value for the given time.
   */
  protected abstract double computeLogFrequency(SynthesisTime time);

  private double cachedTime_;
  private double cachedValue_;
}
