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

package org.observertc.webrtc.observer.models;

import java.util.UUID;

public class CallEntity {


	public static CallEntity of(
			UUID callUUID,
			UUID serviceUUID,
			String serviceName,
			Long initiated,
			String callName,
			String marker) {
		CallEntity result = new CallEntity();
		result.callUUID = callUUID;
		result.initiated = initiated;
		result.serviceUUID = serviceUUID;
		result.serviceName = serviceName;
		result.callName = callName;
		result.marker = marker;
		return result;
	}

	public UUID serviceUUID;
	public String serviceName;
	public Long initiated;
	public Long finished;
	public UUID callUUID;
	public String callName;
	public String marker;

}
