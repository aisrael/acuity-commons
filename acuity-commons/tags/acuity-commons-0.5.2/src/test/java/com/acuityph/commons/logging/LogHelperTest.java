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
 * Created Jan 21, 2008
 */
package com.acuityph.commons.logging;

import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import junit.framework.TestCase;

/**
 * JUnit test case for {@link LogHelper}.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class LogHelperTest extends TestCase {

    /**
     *
     */
    private static final String TEST_LOGGER_NAME = "com.foo.Bar";

    static {
        final Logger testLogger = Logger.getLogger(TEST_LOGGER_NAME);
        testLogger.setUseParentHandlers(false);
    }

    /**
     *
     */
    private static final String MESSAGE = "Message";
    private LogRecord lastLogRecord;

    /**
     *
     */
    private final Handler testHandler = new HandlerAdapter() {

        /**
         * {@inheritDoc}
         *
         * @see com.acuityph.commons.logging.HandlerAdapter#publish(java.util.logging.LogRecord)
         */
        @Override
        public void publish(final LogRecord logRecord) {
            lastLogRecord = logRecord;
        }

    };

    private LogHelper logHelper;

    /**
     * {@inheritDoc}
     *
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        logHelper = LogHelper.getLogHelper(TEST_LOGGER_NAME);
        logHelper.addHandler(testHandler);
        logHelper.setLevel(Level.ALL);
    }

    /**
     * {@inheritDoc}
     *
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        logHelper.removeHandler(testHandler);
        super.tearDown();
    }

    /**
     * Test method for {@link LogHelper#getLogHelper(java.lang.String)}.
     */
    public void testGetLoggerString() {
        final LogHelper actual = LogHelper.getLogHelper(TEST_LOGGER_NAME);
        assertSame(this.logHelper, actual);
    }

    /**
     * Test method for {@link LogHelper#getLogHelper(java.lang.Class)}.
     */
    public void testGetLoggerClass() {
        final LogHelper first = LogHelper.getLogHelper(LogHelperTest.class.getName());
        final LogHelper second = LogHelper.getLogHelper(LogHelperTest.class);
        assertSame(first, second);
    }

    /**
     * Test method for {@link LogHelper#log(Level, String, Throwable)}.
     */
    public void testLogLevelStringThrowable() {
        final Throwable t = new Throwable("This is a throwable");
        logHelper.log(Level.INFO, MESSAGE, t);
        assertEquals(MESSAGE, lastLogRecord.getMessage());
        // assertEquals("com.foo.Bar", lastLogRecord.getSourceClassName());
        // assertEquals("testLogLevelStringThrowable",
        // lastLogRecord.getSourceMethodName());
        assertEquals(Level.INFO, lastLogRecord.getLevel());
        assertEquals(t, lastLogRecord.getThrown());
    }

    /**
     * Test method for {@link LogHelper#log(Level, String)}.
     */
    public void testLogLevelString() {
        logHelper.log(Level.INFO, MESSAGE);
        assertEquals(MESSAGE, lastLogRecord.getMessage());
        // assertEquals(this.getClass().getName(),
        // lastLogRecord.getSourceClassName());
        // assertEquals("testLogLevelString",
        // lastLogRecord.getSourceMethodName());
        assertEquals(Level.INFO, lastLogRecord.getLevel());
    }

    /**
     * Test method for {@link LogHelper#severe(String)}.
     */
    public void testSevereString() {
        logHelper.severe(MESSAGE);
        assertEquals(Level.SEVERE, lastLogRecord.getLevel());
        assertEquals(MESSAGE, lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#severe(Throwable)}.
     */
    public void testSevereThrowable() {
        final Throwable t = new Throwable("This is a throwable");
        logHelper.severe(t);
        assertEquals(Level.SEVERE, lastLogRecord.getLevel());
        assertEquals(t, lastLogRecord.getThrown());
        assertEquals(t.getLocalizedMessage(), lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#warn(String)}.
     */
    public void testWarnString() {
        logHelper.warn(MESSAGE);
        assertEquals(Level.WARNING, lastLogRecord.getLevel());
        assertEquals(MESSAGE, lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#warn(Throwable)}.
     */
    public void testWarnThrowable() {
        final Throwable t = new Throwable("This is a throwable");
        logHelper.warn(t);
        assertEquals(Level.WARNING, lastLogRecord.getLevel());
        assertEquals(t, lastLogRecord.getThrown());
        assertEquals(t.getLocalizedMessage(), lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#fine(String)}.
     */
    public void testInfo() {
        logHelper.info(MESSAGE);
        assertEquals(Level.INFO, lastLogRecord.getLevel());
        assertEquals(MESSAGE, lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#fine(String)}.
     */
    public void testFine() {
        logHelper.fine(MESSAGE);
        assertEquals(Level.FINE, lastLogRecord.getLevel());
        assertEquals(MESSAGE, lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#finer(String)}.
     */
    public void testFiner() {
        logHelper.finer(MESSAGE);
        assertEquals(Level.FINER, lastLogRecord.getLevel());
        assertEquals(MESSAGE, lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#finest(String)}.
     */
    public void testFinestString() {
        logHelper.finest(MESSAGE);
        assertEquals(Level.FINEST, lastLogRecord.getLevel());
        assertEquals(MESSAGE, lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#finest(String, Object[])}.
     */
    public void testFinestStringObjectArray() {
        final int l = 1234;
        logHelper.finest("Debug (%d): %s", l, "Test");
        assertEquals(Level.FINEST, lastLogRecord.getLevel());
        assertEquals("Debug (1234): Test", lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#finest(Throwable)}.
     */
    public void testFinestThrowable() {
        final Throwable t = new Throwable("This is a throwable");
        logHelper.finest(t);
        assertEquals(Level.FINEST, lastLogRecord.getLevel());
        assertEquals(t, lastLogRecord.getThrown());
        assertEquals(t.getLocalizedMessage(), lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#throwing(Throwable)}.
     */
    public void testThrowing() {
        final Throwable t = new Throwable("This is a throwable");
        logHelper.throwing(t);
        assertEquals(Level.FINER, lastLogRecord.getLevel());
        assertEquals("THROW", lastLogRecord.getMessage());
    }

    /**
     * Test method for {@link LogHelper#isLoggable(Level)}.
     */
    public void testIsLoggable() {
        final Level[] levels =
                { Level.FINE, Level.FINER, Level.FINEST, Level.INFO, Level.SEVERE,
                        Level.WARNING };
        logHelper.setLevel(Level.ALL);
        for (final Level level : levels) {
            assertTrue(logHelper.isLoggable(level));
        }
        logHelper.setLevel(Level.OFF);
        for (final Level level : levels) {
            assertFalse(logHelper.isLoggable(level));
        }
    }

    /**
     * Test method for {@link LogHelper#isFine()}.
     */
    public void testIsFine() {
        logHelper.setLevel(Level.ALL);
        assertTrue(logHelper.isFine());
        logHelper.setLevel(Level.OFF);
        assertFalse(logHelper.isFine());
    }

    /**
     * Test method for {@link LogHelper#isFiner()}.
     */
    public void testIsFiner() {
        logHelper.setLevel(Level.ALL);
        assertTrue(logHelper.isFiner());
        logHelper.setLevel(Level.OFF);
        assertFalse(logHelper.isFiner());
    }

    /**
     * Test method for {@link LogHelper#isFinest()}.
     */
    public void testIsFinest() {
        logHelper.setLevel(Level.ALL);
        assertTrue(logHelper.isFinest());
        logHelper.setLevel(Level.OFF);
        assertFalse(logHelper.isFinest());
    }

    /**
     * Test method for {@link LogHelper#debug(String, Object)}.
     */
    public void testDebug() {
        final Date now = new Date();
        logHelper.debug("now", now);
        assertEquals("now: " + now.toString() + " (java.util.Date)", lastLogRecord.getMessage());
    }

    /**
     * Test to catch NPE.
     */
    public void testDebugWithNull() {
        final Date now = null;
        logHelper.debug("now", now);
        assertEquals("now: null (null)", lastLogRecord.getMessage());
    }
}
