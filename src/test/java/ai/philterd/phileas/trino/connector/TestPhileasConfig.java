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

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.*;

public class TestPhileasConfig {

    @Test
    public void testDefaults() {
        assertRecordedDefaults(recordDefaults(PhileasConfig.class).setPolicyFile(null));
    }

    @Test
    public void testExplicitPropertyMappings() throws IOException {
        Path path = Files.createTempFile(null, null);
        Map<String, String> properties = new ImmutableMap.Builder<String, String>().put("phileas.policy.file", path.toString()).build();
        PhileasConfig expected = new PhileasConfig().setPolicyFile(path.toString());
        assertFullMapping(properties, expected);
    }

}
