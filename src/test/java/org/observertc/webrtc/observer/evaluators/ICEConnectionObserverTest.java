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

package org.observertc.webrtc.observer.evaluators;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.observertc.webrtc.observer.evaluators.mediaunits.ICEConnectionObserver;
import org.observertc.webrtc.observer.repositories.hazelcast.ICEConnectionsRepository;
import org.observertc.webrtc.observer.repositories.hazelcast.MediaUnitPeerConnectionsRepository;

import javax.inject.Inject;
import javax.inject.Provider;

@MicronautTest
public class ICEConnectionObserverTest {

	static TestInputsGenerator generator = TestInputsGenerator.builder().build();

	@Inject
	Provider<ICEConnectionObserver> subject;

	@Inject
	ICEConnectionsRepository iceConnectionsRepository;

	@Inject
	MediaUnitPeerConnectionsRepository mediaUnitPeerConnectionsRepository;

	@Test
	public void shouldUpdateExistingPeerConnections() {

	}


}