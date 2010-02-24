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
 * Created Sep 26, 2009
 */
package com.acuityph.commons.util;

/**
 *
 * @author Alistair A. Israel
 */
public final class Platform {

    /**
     *
     */
    private static final String OS_NAME = System.getProperty("os.name");

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Platform() {
        // noop
    }

    /**
     * @return {@link #OS_NAME}.startsWith("Windows")
     */
    public static boolean isWindows() {
        return OS_NAME.startsWith("Windows");
    }

    /**
     * @return {@link #OS_NAME}.equals("Mac OS X")
     */
    public static boolean isMacOsX() {
        return OS_NAME.equals("Mac OS X");
    }

}
