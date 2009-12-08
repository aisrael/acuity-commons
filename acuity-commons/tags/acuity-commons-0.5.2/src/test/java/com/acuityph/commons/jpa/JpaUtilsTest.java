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

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import test.MyEntity;

/**
 * JUnit test for {@link JpaUtils}.
 *
 * @author Alistair A. Israel
 * @since 0.3.1
 */
public final class JpaUtilsTest {

    /**
     * Test for {@link JpaUtils#constructSelectAllQuery(java.lang.String)} .
     */
    @Test
    public void testConstructSelectAllQuery() {
        final String[] entityNames = { "User", "BankAccount" };
        final String[] expected =
                { "SELECT user FROM User user",
                        "SELECT bankAccount FROM BankAccount bankAccount" };
        for (int i = 0; i < expected.length; ++i) {
            final String actual = JpaUtils.constructSelectAllQuery(entityNames[i]);
            assertEquals(expected[i], actual);
        }
    }

    /**
     * Test for {@link JpaUtils#determineIdField(Class)}.
     */
    @Test
    public void testDetermineIdField() {
        final Field idField = JpaUtils.determineIdField(MyEntity.class);
        assertNotNull(idField);
        assertEquals("id", idField.getName());
    }

}
