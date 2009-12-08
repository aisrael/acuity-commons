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
 * Created Jan 1, 2008
 */
package com.acuityph.commons.util;

/**
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class SystemUtils {

    /**
     * Operating system name, as reported by the <code>"os.name"</code> system
     * property.
     */
    public static final String OS_NAME = System.getProperty("os.name");

    /**
     * File separator, as reported by the <code>"file.separator"</code> system
     * property (<code>"/"</code> on UNIX).
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Directory for temporary files, as reported by the
     * <code>"java.io.tmpdir"</code> system property.
     */
    public static final String JAVA_IO_TMPDIR = System.getProperty("java.io.tmpdir");

    /**
     * User's current working directory, as reported by the
     * <code>"user.home"</code> system property.
     */
    public static final String USER_HOME = System.getProperty("user.home");

    /**
     * User's current working directory, as reported by the
     * <code>"user.dir"</code> system property.
     */
    public static final String USER_DIR = System.getProperty("user.dir");

    /**
     * @return {@link #OS_NAME}.startsWith("Windows")
     * @deprecated
     * @see Platform#isWindows()
     */
    @Deprecated
    public static boolean isOsWindows() {
        return Platform.isWindows();
    }

    /**
     * Utility classes should not have a public or default constructor.
     */
    private SystemUtils() {
        // noop
    }
}
