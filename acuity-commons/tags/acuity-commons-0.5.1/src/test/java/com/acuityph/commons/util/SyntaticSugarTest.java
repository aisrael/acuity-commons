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
package com.acuityph.commons.util;

import static com.acuityph.commons.util.SyntaticSugar.coalesce;
import static com.acuityph.commons.util.SyntaticSugar.ifNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test for {@link SyntaticSugar}.
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class SyntaticSugarTest {

    /**
     * Test for {@link SyntaticSugar#ifNull(java.lang.Object, java.lang.Object)}
     * .
     */
    @Test
    public void testIfNull() {
        assertEquals("apple", ifNull(null, "apple"));
        assertEquals("banana", ifNull("banana", "apple"));
    }

    /**
     * Test for
     * {@link SyntaticSugar#ifNull(java.lang.Object, java.lang.Object, java.lang.Object)}
     * .
     */
    @Test
    public void testIfNullTTT() {
        assertEquals("banana", ifNull("orange", "apple", "banana"));
        assertEquals("apple", ifNull(null, "apple", "banana"));
    }

    /**
     * Test for {@link SyntaticSugar#coalesce(T[])}.
     */
    @Test
    public void testCoalesce() {
        final String n = null;
        final String a = "apple";
        final String b = "banana";
        final String c = "carrot";
        assertEquals(a, coalesce(a, b, c));
        assertEquals(b, coalesce(n, b, c));
        assertEquals(c, coalesce(n, n, c));
        assertEquals(a, coalesce(a, n, c));
    }
}
