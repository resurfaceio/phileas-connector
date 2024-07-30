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

import io.airlift.log.Logger;
import io.airlift.slice.Slice;
import io.trino.spi.function.Description;
import io.trino.spi.function.ScalarFunction;
import io.trino.spi.function.SqlNullable;
import io.trino.spi.function.SqlType;

import static io.airlift.slice.Slices.utf8Slice;

public final class PhileasFunctions {

    @SqlNullable
    @Description("Redact input string using system policy")
    @ScalarFunction("phileas_redact")
    @SqlType("varchar")
    public static Slice redact(@SqlType("varchar") Slice s) {
        if (config != null) log.info("redacting with policy.file=" + config.getPolicyFile());
        return (s == null) ? null : utf8Slice(s.toStringUtf8().replaceAll("secret", "******"));  // todo replace with filter
    }

    static void use(PhileasConfig config) {
        if (config != null) log.info("using phileas config: policy.file=" + config.getPolicyFile());
        PhileasFunctions.config = config;
        // todo initialize PhileasFilterService
    }

    private static PhileasConfig config;

    private static final Logger log = Logger.get(PhileasFunctions.class);

}
