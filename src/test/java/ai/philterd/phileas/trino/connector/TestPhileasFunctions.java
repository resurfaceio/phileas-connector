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

import org.testng.annotations.Test;

import java.util.Objects;

import static ai.philterd.phileas.trino.connector.PhileasFunctions.redact;
import static ai.philterd.phileas.trino.connector.PhileasFunctions.slice;
import static org.testng.Assert.assertEquals;

public class TestPhileasFunctions {

    @Test
    public void testDefaultRedaction() {
        PhileasFunctions.use(new PhileasConfig().setPolicyFile(null));
        assertEquals(Objects.requireNonNull(redact(slice("my word is bond"))).toStringUtf8(), "my word is bond");
        assertEquals(Objects.requireNonNull(redact(slice("my email is rik@resurfacd.io"))).toStringUtf8(), "my email is ****************");
    }

}
