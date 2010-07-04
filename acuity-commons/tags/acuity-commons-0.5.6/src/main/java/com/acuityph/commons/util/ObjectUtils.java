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
 * Created Oct 7, 2009
 */
package com.acuityph.commons.util;

/**
 *
 * @author Alistair A. Israel
 */
public final class ObjectUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private ObjectUtils() {
        // noop
    }

    /**
     * Determine if the given objects are equal, returning <code>true</code> if
     * both are <code>null</code> or <code>false</code> if only one is
     * <code>null</code>.
     *
     * @param o1
     *        first Object to compare
     * @param o2
     *        second Object to compare
     * @return whether the given objects are equal
     */
    public static boolean nullSafeEquals(final Object o1, final Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (o1.equals(o2)) {
            return true;
        }
        return false;
    }

}
