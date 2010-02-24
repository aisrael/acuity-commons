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
 * Created Feb 19, 2008
 */
package com.acuityph.commons.db.sql;

import java.util.Hashtable;
import java.util.Map;

import junit.framework.TestCase;

/**
 * JUnit test case for {@link SqlQueryBuilder}.
 *
 * @author Alistair A. Israel
 * @since 0.1.3
 */
public final class SqlQueryBuilderTest extends TestCase {

    /**
     *
     */
    private static final String TABLE = "users";
    /**
     *
     */
    private static final String[] COLUMNS = { "name", "password" };

    /**
     * Test for {@link SqlQueryBuilder#insertInto(String)}.
     */
    public void testInsertInto() {
        final SqlQueryBuilder builder = SqlQueryBuilder.insertInto(TABLE).columns(COLUMNS);
        final String sql = builder.build();
        assertEquals("INSERT INTO users (name, password) VALUES (?, ?)", sql);
    }

    /**
     * Test for {@link SqlQueryBuilder#update(String)}.
     */
    public void testUpdate() {
        final Map<Object, Object> map = new Hashtable<Object, Object>();
        map.put("password", "?");
        final SqlQueryBuilder builder = SqlQueryBuilder.update(TABLE).set(map);
        final String sql = builder.build();
        assertEquals("UPDATE users SET password = ?", sql);
    }
}
