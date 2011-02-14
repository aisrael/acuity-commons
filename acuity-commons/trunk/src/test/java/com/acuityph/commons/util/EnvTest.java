/**
 * Copyright (c) 2008-2010 Acuity Technologies, Inc.
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
 * Created Jan 26, 2010
 */
package com.acuityph.commons.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test for {@link Env}.
 *
 * @author Alistair A. Israel
 */
public final class EnvTest {

    private static final String NOT_A_PROPERTY = "NOT A PROPERTY";

    private static final String SOME_PROPERTY = "some.property";

    /**
     * Test for {@link Env#get(String, String)}.
     */
    @Test
    public void testGetStringString() {
        assertNull(System.getProperty(NOT_A_PROPERTY));
        assertEquals("def", Env.get(NOT_A_PROPERTY, "def"));
        System.setProperty(SOME_PROPERTY, "value");
        assertEquals("value", Env.get(SOME_PROPERTY, "Not the value"));
    }

    /**
     * Test for {@link Env#get(String, int)}.
     */
    @Test
    public void testGetStringInt() {
        assertNull(System.getProperty(NOT_A_PROPERTY));
        assertEquals(123, Env.get(NOT_A_PROPERTY, 123));

        System.setProperty(SOME_PROPERTY, "true");
        assertEquals(456, Env.get(SOME_PROPERTY, 456));

        System.setProperty(SOME_PROPERTY, "789");
        assertEquals(789, Env.get(SOME_PROPERTY, 0));
    }

    /**
     * Test for {@link Env#get(String, boolean)}.
     */
    @Test
    public void testGetStringBoolean() {
        assertNull(System.getProperty(NOT_A_PROPERTY));
        assertTrue(Env.get(NOT_A_PROPERTY, true));

        System.setProperty(SOME_PROPERTY, "789");
        assertTrue(Env.get(SOME_PROPERTY, true));

        System.setProperty(SOME_PROPERTY, "false");
        assertFalse(Env.get(SOME_PROPERTY, true));
    }
}
