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

import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.Test;

/**
 * JUnit test for {@link SimpleFormatter}.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class SimpleFormatterTest {

    /**
     * Test method for {@link SimpleFormatter#format(LogRecord)}.
     */
    @Test
    public void testFormat() {
        final String message = "All your base are belong to us.";
        final LogRecord logRecord = new LogRecord(Level.INFO, message);
        logRecord.setSourceClassName(SimpleFormatterTest.class.getName());
        logRecord.setSourceMethodName("testFormat");
        final String formatted = new SimpleFormatter().format(logRecord);
        assertTrue(formatted.startsWith("[INFO] "));
        assertTrue(formatted.endsWith("SimpleFormatterTest.testFormat: " + message + "\n"));
    }

}
