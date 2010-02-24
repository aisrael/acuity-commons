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

import org.springframework.util.ObjectUtils;

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
     * Utility classes should not have a public or default constructor.
     */
    private Compute() {
        // noop
    }

    /**
     * Merely a delegate to {@link ObjectUtils#nullSafeHashCode(Object[])}.
     *
     * @param args
     *        the fields to include in hashcode computation
     * @return the hashcode
     * @see ObjectUtils#nullSafeHashCode(Object[])
     */
    public static int hashCode(final Object... args) {
        return ObjectUtils.nullSafeHashCode(args);
    }
}
