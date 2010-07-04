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
package com.acuityph.commons.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test for {@link Base64}.
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class Base64Test {

    /**
     * Test {@link Base64#encode(String)}.
     */
    @Test
    public void testEncode() {
        final String[] expected =
                {
                        "VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZy4=",
                        "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==", };
        final String[] message =
                { "The quick brown fox jumps over the lazy dog.",
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/" };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals("encodeBase64() failed!", expected[i], Base64.encode(message[i]));
        }
    }

    /**
     * Test {@link Base64#decode(String)}.
     */
    @Test
    public void testDecode() {
        final String[] expected =
                { "89+/", "nop", "g.", "The quick brown fox jumps over the lazy dog.",
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/" };
        final String[] message =
                {
                        "ODkrLw==",
                        "bm9w",
                        "Zy4=",
                        "VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wcyBvdmVyIHRoZSBsYXp5IGRvZy4=",
                        "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==", };
        for (int i = 0; i < expected.length; ++i) {
            final String decoded = new String(Base64.decode(message[i]));
            assertEquals("decodeBase64() failed! [" + i + "]", expected[i].length(), decoded
                    .length());
            assertEquals("decodeBase64() failed! [" + i + "]", expected[i], decoded);
        }
    }

}
