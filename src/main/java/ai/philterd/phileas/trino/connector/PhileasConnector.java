/*
 *     Copyright 2024 Philterd, LLC @ https://www.philterd.ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.philterd.phileas.trino.connector;

import io.airlift.bootstrap.LifeCycleManager;
import io.trino.spi.connector.Connector;

import javax.inject.Inject;

import static java.util.Objects.requireNonNull;

public class PhileasConnector implements Connector {

    @Inject
    public PhileasConnector(LifeCycleManager lcm, PhileasConfig config) {
        this.lcm = requireNonNull(lcm, "lcm is null");
        PhileasFunctions.use(requireNonNull(config, "config is null"));
    }

    @Override
    public final void shutdown() {
        lcm.stop();
    }

    private final LifeCycleManager lcm;

}
