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
 * Created Apr 3, 2007
 */
package com.acuityph.commons.logging;

import static java.lang.String.format;
import static java.util.logging.Level.FINE;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * A decorator around {@link Logger} that provides various convenience methods
 * using Java 5 idioms.
 * </p>
 * <p>
 * <em>Note:</em> Consider using <a
 * href="http://commons.apache.org/logging/">Commons Logging</a> or <a
 * href="http://www.slf4j.org/">SLF4J</a> instead!
 * </p>
 *
 * @see java.util.Logger
 * @author Alistair A. Israel
 * @since 0.1
 */
public class LogHelper extends Logger {

    /**
     *
     */
    private static final String LOGHELPER_CLASS_NAME = LogHelper.class.getName();

    /**
     *
     */
    private static final String THREAD_CLASS_NAME = Thread.class.getName();

    private static final ConcurrentHashMap<String, LogHelper> LOG_HELPERS_MAP =
            new ConcurrentHashMap<String, LogHelper>();

    /**
     * @param name
     *        A name for the logger. This should be a dot-separated name and
     *        should normally be based on the package name or class name of the
     *        subsystem, such as java.net or javax.swing. It may be null for
     *        anonymous Loggers.
     */
    protected LogHelper(final String name) {
        super(name, null);
    }

    /**
     * Find or create a LogHelper for a named subsystem. If a LogHelper has
     * already been created with the given name it is returned. Otherwise a new
     * LogHelper is created.
     *
     * @param name
     *        A name for the logger. This should be a dot-separated name and
     *        should normally be based on the package name or class name of the
     *        subsystem, such as java.net or javax.swing
     * @return a suitable LogHelper
     * @return
     */
    public static final LogHelper getLogHelper(final String name) {
        if (LOG_HELPERS_MAP.containsKey(name)) {
            return LOG_HELPERS_MAP.get(name);
        }
        final LogHelper newLogHelper = new LogHelper(name);
        LogHelper logHelper = LOG_HELPERS_MAP.putIfAbsent(name, newLogHelper);
        if (logHelper == null) {
            logHelper = newLogHelper;
        }
        return logHelper;
    }

    /**
     * Find or create a LogHelper for the given class. Uses the fully-qualified
     * class name as the logger subsystem name.
     *
     * @param klazz
     *        A class
     * @return a suitable LogHelper
     * @see LogHelper#getLogHelper(String)
     */
    public static final LogHelper getLogHelper(final Class<?> klazz) {
        return getLogHelper(klazz.getName());
    }

    /**
     * Unwind the stack trace past Thread and LogHelper to find the calling
     * class.
     *
     * @return StackTraceElement
     */
    private StackTraceElement unwindStackTrace() {
        final StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        int offset = 0;
        while (offset < stes.length
                && (stes[offset].getClassName().equals(THREAD_CLASS_NAME) || stes[offset]
                        .getClassName().equals(LOGHELPER_CLASS_NAME))) {
            ++offset;
        }
        return stes[offset];
    }

    /**
     * Log a Throwable as a SEVERE log message.
     *
     * @param t
     *        Throwable
     */
    public final void severe(final Throwable t) {
        severe(t.getLocalizedMessage(), t);
    }

    /**
     * Log a SEVERE message with associated Throwable.
     *
     * @param msg
     *        The string message (or a key in the message catalog)
     * @param t
     *        Throwable
     */
    public final void severe(final String msg, final Throwable t) {
        log(SEVERE, msg, t);
    }

    /**
     * Log a WARNING message.
     *
     * @param msg
     *        The string message (or a key in the message catalog)
     */
    public final void warn(final String msg) {
        log(WARNING, msg);
    }

    /**
     * Log a Throwable as a WARNING message.
     *
     * @param t
     *        Throwable
     */
    public final void warn(final Throwable t) {
        log(WARNING, t.getLocalizedMessage(), t);
    }

    /**
     * Log a formatted {@link Level#INFO} message.
     *
     * @param s
     *        The format string
     * @param args
     *        Object...
     */
    public final void info(final String s, final Object... args) {
        if (isInfo()) {
            info(format(s, args));
        }
    }

    /**
     * Log a formatted {@link Level#FINE} message.
     *
     * @param s
     *        The format string
     * @param args
     *        Object...
     */
    public final void fine(final String s, final Object... args) {
        if (isFine()) {
            fine(format(s, args));
        }
    }

    /**
     * Log a throwable as a FINE message.
     *
     * @param t
     *        Throwable
     */
    public final void fine(final Throwable t) {
        if (isFine()) {
            log(FINE, t.getLocalizedMessage(), t);
        }
    }

    /**
     * Log a formatted {@link Level#FINER} message.
     *
     * @param s
     *        The format string
     * @param args
     *        Object...
     */
    public final void finer(final String s, final Object... args) {
        if (isFiner()) {
            finer(format(s, args));
        }
    }

    /**
     * Log a formatted {@link Level#FINEST} message.
     *
     * @param s
     *        The format string
     * @param args
     *        Object...
     */
    public final void finest(final String s, final Object... args) {
        if (isFinest()) {
            finest(format(s, args));
        }
    }

    /**
     * Log a throwable at FINEST level.
     *
     * @param t
     *        Throwable
     */
    public final void finest(final Throwable t) {
        log(Level.FINEST, t.getLocalizedMessage(), t);
    }

    /**
     * Convenience method for debug logging of a variable's name, value and
     * class. Logs at FINEST level.
     *
     * @param name
     *        The variable name
     * @param value
     *        The variable value
     */
    public final void debug(final String name, final Object value) {
        final String klazz;
        if (value == null) {
            klazz = null;
        } else {
            klazz = value.getClass().getName();
        }
        finest("%s: %s (%s)", name, value, klazz);
    }

    /**
     * Log throwing an exception.
     *
     * @param t
     *        Throwable
     */
    public final void throwing(final Throwable t) {
        final StackTraceElement ste = unwindStackTrace();
        throwing(ste.getClassName(), ste.getMethodName(), t);
    }

    /**
     * @return true if {@link Level#INFO} would actually be logged by this
     *         logger.
     */
    private boolean isInfo() {
        return isLoggable(Level.INFO);
    }

    /**
     * @return true if {@link Level#FINE} would actually be logged by this
     *         logger.
     */
    public final boolean isFine() {
        return isLoggable(Level.FINE);
    }

    /**
     * @return true if {@link Level#FINER} would actually be logged by this
     *         logger.
     */
    public final boolean isFiner() {
        return isLoggable(Level.FINER);
    }

    /**
     * @return true if {@link Level#FINEST} would actually be logged by this
     *         logger.
     */
    public final boolean isFinest() {
        return isLoggable(Level.FINEST);
    }
}
