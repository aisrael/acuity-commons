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
 * Created Jan 11, 2008
 */
package com.acuityph.commons.logging;

import static org.springframework.util.ClassUtils.getShortName;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * A simple log formatter.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public class SimpleFormatter extends Formatter {

    private static final Map<Level, String> LEVELS_MAP;
    static {
        final Map<Level, String> map = new HashMap<Level, String>(7);
        map.put(Level.CONFIG, "[CONFIG] ");
        map.put(Level.FINE, "[FINE] ");
        map.put(Level.FINER, "[FINER] ");
        map.put(Level.FINEST, "[FINEST] ");
        map.put(Level.INFO, "[INFO] ");
        map.put(Level.SEVERE, "[SEVERE] ");
        map.put(Level.WARNING, "[WARN] ");
        LEVELS_MAP = Collections.unmodifiableMap(map);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
     */
    @Override
    public final String format(final LogRecord logRecord) {
        final StringBuilder sb = new StringBuilder();
        sb.append(LEVELS_MAP.get(logRecord.getLevel()));
        final DateFormat sdf = DateFormat.getDateTimeInstance();
        sb.append(sdf.format(new Date(logRecord.getMillis()))).append(' ');
        sb.append(getShortName(logRecord.getSourceClassName()));
        sb.append('.').append(logRecord.getSourceMethodName()).append(": ");
        sb.append(logRecord.getMessage()).append("\n");
        return sb.toString();
    }
}
