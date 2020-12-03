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

package org.observertc.webrtc.observer.repositories.hazelcast;

import java.util.UUID;
import javax.inject.Singleton;
import org.observertc.webrtc.observer.ObserverHazelcast;
import org.observertc.webrtc.observer.models.SynchronizationSourceEntity;

@Singleton
public class SynchronizationSourcesRepository extends MapRepositoryAbstract<String, SynchronizationSourceEntity> {

	private static final String HAZELCAST_IMAP_NAME = "WebRTCObserverSynchronizationSources";

	public static String getKey(UUID serviceUUID, Long SSRC) {
		return String.format("%s-%d", serviceUUID.toString(), SSRC);
	}

	public SynchronizationSourcesRepository(ObserverHazelcast observerHazelcast) {
		super(observerHazelcast, HAZELCAST_IMAP_NAME);
	}
}
