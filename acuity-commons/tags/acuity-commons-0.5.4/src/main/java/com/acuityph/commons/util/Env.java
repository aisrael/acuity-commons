/**
 * Copyright (c) 2008-2010 Acuity Technologies, Inc.
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
 * Created Jan 26, 2010
 */
package com.acuityph.commons.util;

import java.util.logging.Logger;

/**
 * A utility class for getting system properties with defaults.
 *
 * @author Alistair A. Israel
 */
public final class Env {

    private static final Logger logger = Logger.getLogger(Env.class.getName());

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Env() {
        // noop
    }

    /**
     * Return a system property string value, or the default if the system
     * property is not set (i.e., <tt>System.getProperty(key)</tt> returns
     * {@code null}).
     *
     * @param key
     *        the system property key
     * @param def
     *        the default value to return if the system property is not set
     * @return the system property value, or the default if the system property
     *         is not set
     */
    public static String get(final String key, final String def) {
        final String value = System.getProperty(key);
        if (null != value) {
            return value;
        }
        return def;
    }

    /**
     * Return a system property value as an int, or the default if the system
     * property is not set (i.e., <tt>System.getProperty(key)</tt> returns
     * {@code null}) or cannot be converted properly to an {@code int} (using
     * {@link Integer#parseInt(String)}).
     *
     * @param key
     *        the system property key
     * @param def
     *        the default value to return if the system property is not set
     * @return the system property value, or the default if the system property
     *         is not set
     */
    public static int get(final String key, final int def) {
        final String s = System.getProperty(key);
        if (null != s) {
            try {
                return Integer.parseInt(s);
            } catch (final NumberFormatException e) {
                logger.finest("NumberFormatException converting value of system property \"" + key
                        + "\": \"" + s + "\" to an int");
            }
        }
        return def;
    }

    /**
     * Return a system property value as an {@code boolean}, or the default if
     * the system property is not set (i.e., <tt>System.getProperty(key)</tt>
     * returns {@code null}) or is not one of <tt>"true|false|yes|no|0|1"</tt>
     * (case-insensitive).
     *
     * @param key
     *        the system property key
     * @param def
     *        the default value to return if the system property is not set
     * @return the system property value, or the default if the system property
     *         is not set
     */
    public static boolean get(final String key, final boolean def) {
        final String s = System.getProperty(key);
        if (null == s) {
            return def;
        }
        if (s.equalsIgnoreCase("TRUE") || s.equalsIgnoreCase("YES") || s.equals("1")) {
            return true;
        } else if (s.equalsIgnoreCase("FALSE") || s.equalsIgnoreCase("NO") || s.equals("0")) {
            return false;
        }
        return def;
    }
}
