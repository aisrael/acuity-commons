/**
 * Copyright (c) 2008-2009 Acuity Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created May 18, 2009
 */
package com.acuityph.commons.util;

import java.util.Arrays;

/**
 * <p>
 * Lets use write hashCode functions as:
 * </p>
 *
 * <pre>
 *   public int hashCode() {
 *     return Compute.hashCode(field1, field2, ...);
 *   }
 * </pre>
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class Compute {

    /**
     *
     */
    private Compute() {
        // Utility classes should not have a public or default constructor.
    }

    /**
     * Acts like Spring's {@code ObjectUtils.nullSafeHashCode()} Delegates to
     * {@link Arrays#hashCode(Object[])} if more than one argument is provided.
     *
     * @param args
     *        the fields to include in hashcode computation
     * @return the hashcode
     * @see Arrays#hashCode(Object[])
     */
    public static int hashCode(final Object... args) {
        if (args == null || args.length == 0) {
            return 0;
        }
        if (args.length == 1) {
            final Object obj = args[0];
            if (obj == null) {
                return 0;
            }
            if (obj.getClass().isArray()) {
                return Arrays.hashCode((Object[]) obj);
            } else {
                return obj.hashCode();
            }
        }
        return Arrays.hashCode(args);
    }
}
