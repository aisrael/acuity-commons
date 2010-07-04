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
 * Created Jan 1, 2008
 */
package com.acuityph.commons.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

/**
 * JUnit test case for {@link StringUtility}.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class StringUtilityTest {

    /**
     * Test for {@link StringUtility#escape(String)}.
     */
    @Test
    public void testEscape() {
        final String[] strings = { "normal", "\"", "\n\t\b\f\r\"\'\\" };
        final String[] expected = { "normal", "\\\"", "\\n\\t\\b\\f\\r\\\"\\\'\\\\" };
        for (int i = 0; i < expected.length; ++i) {
            final String actual = StringUtility.escape(strings[i]);
            assertEquals(expected[i], actual);
        }
    }

    /**
     * Test for {@link StringUtility#unescape(String)}.
     */
    @Test
    public void testUnescape() {
        final String[] strings = { "normal", "\\\"", "\\n\\t\\b\\f\\r\\\"\\\'\\\\" };
        final String[] expected = { "normal", "\"", "\n\t\b\f\r\"\'\\" };
        for (int i = 0; i < expected.length; ++i) {
            final String actual = StringUtility.unescape(strings[i]);
            assertEquals(expected[i], actual);
        }
    }

    /**
     *
     */
    private static final String BACKSLASH = "\\";

    /**
     *
     */
    private static final String DOUBLE_QUOTE = String.valueOf('"');

    /**
     * Test for {@link StringUtility#squeezeSpaces(String)}
     */
    @Test
    public void testSqueezeSpaces() {
        final String[] strings = { "camel  case", "Lorem  ipsum    dolor" };
        final String[] expected = { "camel case", "Lorem ipsum dolor", };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtility.squeezeSpaces(strings[i]));
        }
    }

    /**
     * Test for {@link StringUtility#encodeHex(byte[])}.
     */
    @Test
    public void testEncodeHex() {
        final String expected = "010a10a0f0ff";
        final byte[] bytes = { 0x01, 0x0a, 0x10, (byte) 0xa0, (byte) 0xf0, (byte) 0xff };
        assertEquals(expected, StringUtility.encodeHex(bytes));
    }

    /**
     * Test for {@link StringUtility#decodeHex(byte[])}.
     */
    @Test
    public void testDecodeHex() {
        final String string = "010a1023A0f0fF";
        final byte[] expected =
                { 0x01, 0x0a, 0x10, 0x23, (byte) (0xa0), (byte) 0xf0, (byte) 0xff };
        assertArrayEquals(expected, StringUtility.decodeHex(string));
    }

    /**
     * Test for {@link StringUtility#escapeQuotes(String)}.
     */
    @Test
    public void testEscapeQuotes() {
        final String[] expected =
                { BACKSLASH + DOUBLE_QUOTE, BACKSLASH + BACKSLASH,
                        BACKSLASH + BACKSLASH + BACKSLASH + DOUBLE_QUOTE };
        final String[] strings = { DOUBLE_QUOTE, BACKSLASH, BACKSLASH + DOUBLE_QUOTE };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtility.escapeQuotes(strings[i]));
        }
    }

    /**
     * Test for {@link StringUtility#unescapeQuotes(String)}.
     */
    @Test
    public void testUnescapeQuotes() {
        final String[] strings =
                { BACKSLASH + DOUBLE_QUOTE, BACKSLASH + BACKSLASH,
                        BACKSLASH + BACKSLASH + BACKSLASH + DOUBLE_QUOTE };
        final String[] expected = { DOUBLE_QUOTE, BACKSLASH, BACKSLASH + DOUBLE_QUOTE };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtility.unescapeQuotes(strings[i]));
        }
    }

    /**
     * Test for {@link StringUtility#splitCamelCase(String)}.
     */
    @Test
    public void testSplitCamelCase() {
        final String[] strings =
                { "", "word", "Word", "camelCase", "CamelCase", "AbstractSQLParser" };
        final String[][] expected =
                { new String[0], { "word" }, { "Word" }, { "camel", "Case" },
                        { "Camel", "Case" }, { "Abstract", "SQLParser" }, };
        for (int i = 0; i < expected.length; ++i) {
            final String[] actual = StringUtility.splitCamelCase(strings[i]);
            assertArrayEquals(expected[i], actual);
        }
    }

    /**
     * Test for {@link StringUtility#underscore(String)}.
     */
    @Test
    public void testUnderscore() {
        final String[] strings =
                { "", "word", "Word", "camelCase", "CamelCase", "AbstractSQLParser" };
        final String[] expected =
                { "", "word", "word", "camel_case", "camel_case", "abstract_sqlparser" };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtility.underscore(strings[i]));
        }
    }

    /**
     * Test for {@link StringUtility#join(char, Object...)}.
     */
    @Test
    public void testJoinVarargs() {
        assertEquals("camel_case", StringUtility.join('_', "camel", "case"));
        assertEquals("apple:123", StringUtility.join(":", "apple", 123));
    }

    /**
     * Test for {@link StringUtility#join(Collection, char)}.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testJoinCollectionChar() {
        final Collection[] collections =
                { Arrays.asList(new String[] { "camel", "case" }),
                        Arrays.asList(new String[] { "Lorem", "ipsum", "dolor" }), };
        final char[] separators = { '_', ',' };
        final String[] expected = { "camel_case", "Lorem,ipsum,dolor", };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtility.join(collections[i], separators[i]));
        }
    }

    /**
     * Test for {@link StringUtility#join(Object[], char)}
     */
    @Test
    public void testJoinArrayChar() {
        final String[][] arrays =
                { new String[] { "camel", "case" }, new String[] { "Lorem", "ipsum", "dolor" }, };
        final char[] separators = { '_', ',' };
        final String[] expected = { "camel_case", "Lorem,ipsum,dolor", };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], StringUtility.join(arrays[i], separators[i]));
        }
    }

}
