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

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * A convenience base class for implementing one's own {@link Handler}s.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public abstract class HandlerAdapter extends Handler {

    /**
     * {@inheritDoc}
     *
     * @see java.util.logging.Handler#close()
     */
    @Override
    public void close() throws SecurityException {
        // noop
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.logging.Handler#flush()
     */
    @Override
    public void flush() {
        // noop
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.logging.Handler#publish(java.util.logging.LogRecord)
     */
    @Override
    public void publish(@SuppressWarnings("unused") final LogRecord logRecord) {
        // noop
    }

}
