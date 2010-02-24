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
 * Created May 19, 2009
 */
package com.acuityph.commons.jdbc;

import java.sql.Types;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A Java 5 typesafe enum for SQL types based on {@link Types}.
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public enum SqlType {

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>BIT</code>.
     *
     * @see Types#BIT
     */
    BIT(Types.BIT),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>TINYINT</code>.
     *
     * @see Types#TINYINT
     */
    TINYINT(Types.TINYINT),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>SMALLINT</code>.
     *
     * @see Types#SMALLINT
     */
    SMALLINT(Types.SMALLINT),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>INTEGER</code>.
     *
     * @see Types#INTEGER
     */
    INTEGER(Types.INTEGER),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>BIGINT</code>.
     *
     * @see Types#BIGINT
     */
    BIGINT(Types.BIGINT),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>FLOAT</code>.
     *
     * @see Types#FLOAT
     */
    FLOAT(Types.FLOAT),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>REAL</code>.
     *
     * @see Types#REAL
     */
    REAL(Types.REAL),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>DOUBLE</code>.
     *
     * @see Types#DOUBLE
     */
    DOUBLE(Types.DOUBLE),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>NUMERIC</code>.
     *
     * @see Types#NUMERIC
     */
    NUMERIC(Types.NUMERIC),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>DECIMAL</code>.
     *
     * @see Types#DECIMAL
     */
    DECIMAL(Types.DECIMAL),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>CHAR</code>.
     *
     * @see Types#CHAR
     */
    CHAR(Types.CHAR),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>VARCHAR</code>.
     *
     * @see Types#VARCHAR
     */
    VARCHAR(Types.VARCHAR),

    /**
     * <P>
     * The type code that identifies the generic SQL type
     * <code>LONGVARCHAR</code>.
     *
     * @see Types#LONGVARBINARY
     */
    LONGVARCHAR(Types.LONGVARCHAR),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>DATE</code>.
     *
     * @see Types#DATE
     */
    DATE(Types.DATE),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>TIME</code>.
     *
     * @see Types#TIME
     */
    TIME(Types.TIME),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>TIMESTAMP</code>.
     *
     * @see Types#TIMESTAMP
     */
    TIMESTAMP(Types.TIMESTAMP),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>BINARY</code>.
     *
     * @see Types#BINARY
     */
    BINARY(Types.BINARY),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>VARBINARY</code>.
     *
     * @see Types#VARBINARY
     */
    VARBINARY(Types.VARBINARY),

    /**
     * <P>
     * The type code that identifies the generic SQL type
     * <code>LONGVARBINARY</code>.
     *
     * @see Types#LONGVARBINARY
     */
    LONGVARBINARY(Types.LONGVARBINARY),

    /**
     * <P>
     * The type code that identifies the generic SQL type <code>NULL</code>.
     *
     * @see Types#NULL
     */
    NULL(Types.NULL),

    /**
     * The type code indicates that the SQL type is database-specific and gets
     * mapped to a Java object that can be accessed via the methods
     * <code>getObject</code> and <code>setObject</code>.
     *
     * @see Types#OTHER
     */
    OTHER(Types.OTHER),

    /**
     * The type code that identifies the generic SQL type
     * <code>JAVA_OBJECT</code>.
     *
     * @see Types#JAVA_OBJECT
     */
    JAVA_OBJECT(Types.JAVA_OBJECT),

    /**
     * The type code that identifies the generic SQL type <code>DISTINCT</code>.
     *
     * @see Types#DISTINCT
     */
    DISTINCT(Types.DISTINCT),

    /**
     * The type code that identifies the generic SQL type <code>STRUCT</code>.
     *
     * @see Types#STRUCT
     */
    STRUCT(Types.STRUCT),

    /**
     * The type code that identifies the generic SQL type <code>ARRAY</code>.
     *
     * @see Types#ARRAY
     */
    ARRAY(Types.ARRAY),

    /**
     * The type code that identifies the generic SQL type <code>BLOB</code>.
     *
     * @see Types#BLOB
     */
    BLOB(Types.BLOB),

    /**
     * The type code that identifies the generic SQL type <code>CLOB</code>.
     *
     * @see Types#CLOB
     */
    CLOB(Types.CLOB),

    /**
     * The type code that identifies the generic SQL type <code>REF</code>.
     *
     * @see Types#REF
     */
    REF(Types.REF),

    /**
     * The type code that identifies the generic SQL type <code>DATALINK</code>.
     *
     * @see Types#DATALINK
     */
    DATALINK(Types.DATALINK),

    /**
     * The type code that identifies the generic SQL type <code>BOOLEAN</code>.
     *
     * @see Types#BOOLEAN
     */
    BOOLEAN(Types.BOOLEAN);

    private static final Logger logger = Logger.getLogger(SqlType.class.getName());

    private final int value;

    /**
     * @param value
     *        the native value
     */
    private SqlType(final int value) {
        this.value = value;
    }

    /**
     * @return the native int value
     * @see Types
     */
    public final int getValue() {
        return value;
    }

    // Create the lookup map from native integer Type to SqlType
    private static final Map<Integer, SqlType> LOOKUP_MAP =
            new Hashtable<Integer, SqlType>(values().length);
    static {
        for (final SqlType sqlType : values()) {
            if (LOOKUP_MAP.containsKey(sqlType.value)) {
                logger.severe("An existing SqlType for the value " + sqlType.value
                        + " is already mapped!");
            }
            LOOKUP_MAP.put(Integer.valueOf(sqlType.value), sqlType);
        }
    }

    /**
     * @param type
     *        the native int type
     * @return the corresponding {@link SqlType}
     */
    public static SqlType lookup(final int type) {
        if (LOOKUP_MAP.containsKey(type)) {
            return LOOKUP_MAP.get(type);
        }
        logger.warning("Unable to find SqlType with value " + type
                + ", returning SqlType.OTHER");
        return SqlType.OTHER;
    }
}
