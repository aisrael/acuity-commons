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
 * Created Nov 18, 2009
 */
package com.acuityph.commons.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 * JUnit test for {@link ArrayUtils}.
 *
 * @author Alistair A. Israel
 */
public final class ArrayUtilsTest {

    private static final Boolean[] NIL = null;

    private static final Boolean[] EMPTY = {};

    private static final Boolean[] ONE = { Boolean.TRUE };

    /**
     * Test for {@link ArrayUtils#hasElements(T[])}.
     */
    @Test
    public void testHasElements() {
        assertFalse(ArrayUtils.hasElements(NIL));
        assertFalse(ArrayUtils.hasElements(EMPTY));
        assertTrue(ArrayUtils.hasElements(ONE));
    }

    /**
     * Test for {@link ArrayUtils#isNullOrEmpty(T[])}.
     */
    @Test
    public void testIsNullOrEmpty() {
        assertTrue(ArrayUtils.isNullOrEmpty(NIL));
        assertTrue(ArrayUtils.isNullOrEmpty(EMPTY));
        assertFalse(ArrayUtils.isNullOrEmpty(ONE));
    }

    /**
     * Test for {@link ArrayUtils#box(char[])}.
     */
    @Test
    public void testBoxCharArray() {
        final char[] array = { 'a', 'b', 'c' };
        final Character[] boxed = ArrayUtils.box(array);
        for (int i = 0; i < array.length; ++i) {
            final Character element = boxed[i];
            assertEquals(array[i], element.charValue());
        }
    }

    /**
     * Test for {@link ArrayUtils#box(int[])}.
     */
    @Test
    public void testBoxIntArray() {
        final int[] array = { 4, 2 };
        final Integer[] boxed = ArrayUtils.box(array);
        for (int i = 0; i < array.length; ++i) {
            final Integer element = boxed[i];
            assertEquals(array[i], element.intValue());
        }
    }

    /**
     * Test for {@link ArrayUtils#unbox(java.lang.Integer[])}.
     */
    @Test
    public void testUnbox() {
        final Integer[] array = { Integer.valueOf(4), Integer.valueOf(2) };
        final int[] unboxed = ArrayUtils.unbox(array);
        for (int i = 0; i < unboxed.length; ++i) {
            assertEquals(array[i].intValue(), unboxed[i]);
        }
    }

    /**
     * Test for {@link ArrayUtils#asList(char[])}.
     */
    @Test
    public void testAsListCharArray() {
        final char[] array = { 'a', 'b', 'c' };
        final List<Character> list = ArrayUtils.asList(array);
        for (int i = 0; i < array.length; ++i) {
            final Character element = list.get(i);
            assertEquals(array[i], element.charValue());
        }
    }

    /**
     * Test for {@link ArrayUtils#asList(int[])}.
     */
    @Test
    public void testAsListIntArray() {
        final int[] array = { 4, 2 };
        final List<Integer> list = ArrayUtils.asList(array);
        for (int i = 0; i < array.length; ++i) {
            final Integer element = list.get(i);
            assertEquals(array[i], element.intValue());
        }
    }

    /**
     * Test for {@link ArrayUtils#compact(T[])} .
     */
    @Test
    public void testCompact() {
        final String[] expected = { "a", "b", "c" };
        assertArrayEquals(expected, ArrayUtils.compact(null, "a", "b", null, "c", null));
    }

}
