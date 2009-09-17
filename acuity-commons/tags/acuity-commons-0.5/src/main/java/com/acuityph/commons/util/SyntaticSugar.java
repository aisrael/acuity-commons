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
 * Created May 19, 2009
 */
package com.acuityph.commons.util;

/**
 * Provides syntatic sugar.
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class SyntaticSugar {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private SyntaticSugar() {
        // noop
    }

    /**
     * Returns obj if obj is not null, or val.
     *
     * @param <T>
     *        a type
     * @param obj
     *        the object to test
     * @param valIfNull
     *        the value to return if obj is null
     * @return val if obj is null, or obj
     */
    public static <T> T ifNull(final T obj, final T valIfNull) {
        return ifNull(obj, valIfNull, obj);
    }

    /**
     * Returns obj if obj is not null, or val.
     *
     * @param <T>
     *        a type
     * @param obj
     *        the object to test
     * @param valIfNull
     *        the value to return if obj is null
     * @param valIfNotNull
     *        the value to return if obj is not null
     * @return valIfNull if obj is null, or valIfNotNull
     */
    public static <T> T ifNull(final T obj, final T valIfNull, final T valIfNotNull) {
        if (obj == null) {
            return valIfNull;
        }
        return valIfNotNull;
    }

    /**
     * Returns the first non-null expression among its arguments.
     *
     * @param <T>
     *        a type (can be Object)
     * @param args
     *        the arguments (varargs)
     * @return the first non-null expression among the arguments, or null if all
     *         are null
     */
    public static <T> T coalesce(final T... args) {
        for (final T arg : args) {
            if (arg != null) {
                return arg;
            }
        }
        return null;
    }
}
