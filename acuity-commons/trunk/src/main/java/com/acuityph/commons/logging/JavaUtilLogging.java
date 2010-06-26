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
 * Created Jun 25, 2010
 */
package com.acuityph.commons.logging;

import java.util.logging.Logger;

/**
 * Right now only provides one utility method {@link #getLogger(Class)}.
 *
 * @author Alistair A. Israel
 */
public final class JavaUtilLogging {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private JavaUtilLogging() {
        // noop
    }

    /**
     * @param clazz
     *        the class
     * @return the Logger
     */
    public static Logger getLogger(final Class<?> clazz) {
        return Logger.getLogger(clazz.getName());
    }
}
