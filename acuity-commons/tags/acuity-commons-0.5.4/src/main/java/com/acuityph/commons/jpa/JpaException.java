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
 * Created May 12, 2009
 */
package com.acuityph.commons.jpa;

import org.springframework.dao.DataAccessException;

/**
 * A concrete subclass of {@link DataAccessException} used throughout
 * {@link com.acuityph.commons.jpa}.
 *
 * @author Alistair A. Israel
 * @since 0.3.1
 */
public class JpaException extends DataAccessException {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 5087685125142586525L;

    /**
     * Constructor for JpaException.
     *
     * @param msg
     *        the detail message
     * @param cause
     *        the root cause (usually from using a underlying data access API
     *        such as JDBC)
     */
    public JpaException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructor for JpaException.
     *
     * @param msg
     *        the detail message
     */
    public JpaException(final String msg) {
        super(msg);
    }

}
