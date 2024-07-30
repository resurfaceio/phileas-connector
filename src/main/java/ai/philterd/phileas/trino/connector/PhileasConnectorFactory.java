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

import com.google.inject.Injector;
import io.airlift.bootstrap.Bootstrap;
import io.trino.spi.connector.Connector;
import io.trino.spi.connector.ConnectorContext;
import io.trino.spi.connector.ConnectorFactory;

import java.util.Map;

import static java.util.Objects.requireNonNull;

public class PhileasConnectorFactory implements ConnectorFactory {

    public static final String CONNECTOR_NAME = "phileas";

    @Override
    public Connector create(String catalogName, Map<String, String> config, ConnectorContext context) {
        requireNonNull(config, "config is null");

        Injector injector = new Bootstrap(new PhileasModule())
                .doNotInitializeLogging()
                .setRequiredConfigurationProperties(config)
                .initialize();

        return injector.getInstance(PhileasConnector.class);
    }

    @Override
    public String getName() {
        return CONNECTOR_NAME;
    }

}
