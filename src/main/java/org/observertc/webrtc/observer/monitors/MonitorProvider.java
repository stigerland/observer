/*
 * Copyright  2020 Balazs Kreith
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.observertc.webrtc.observer.monitors;

import io.micrometer.core.instrument.MeterRegistry;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class MonitorProvider {
	private static final String KLASS_TAG_NAME = "klass";

	@Inject
	Provider<CounterMonitorBuilder> counterMonitorBuilderProvider;

	@Inject
	MeterRegistry meterRegistry;

	public MonitorProvider(
			MeterRegistry meterRegistry
	) {
		this.meterRegistry = meterRegistry;
	}

	public FlawMonitor makeFlawMonitorFor(Class klass) {
		return new FlawMonitor(this.meterRegistry)
				.withName(FlawMonitor.class.getSimpleName())
				.withTag(KLASS_TAG_NAME, klass.getSimpleName());
	}

	public<T> CounterMonitorBuilder<T> makeCounterMonitorBuilder(String name) {
		CounterMonitorBuilder<T> result = this.counterMonitorBuilderProvider.get();
		return result.withName(name);
	}

}
