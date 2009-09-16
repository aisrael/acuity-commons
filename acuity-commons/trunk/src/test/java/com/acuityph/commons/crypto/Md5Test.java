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
 * Created July 31, 2009
 */
package com.acuityph.commons.crypto;

import static junit.framework.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;

import com.acuityph.commons.util.StringUtility;

/**
 * JUnit test for {@link Md5}.
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class Md5Test {

    private static final String TEXT =
            "All your base are belong to us. The quick brown fox jumps over the lazy dog.";

    private static final byte[] BYTES;
    static {
        try {
            BYTES = TEXT.getBytes(Md5.UTF_8);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static final String EXPECTED_HASH = "efc0fb0569088c78b727f110f8444929";

    /**
     * Test for {@link Md5#hash(byte[])}.
     *
     * @throws Exception
     *         on any exception
     */
    @Test
    public void testHashByteArray() throws Exception {
        final byte[] hash = Md5.hash(BYTES);
        assertEquals("Md5.hash(" + Arrays.toString(BYTES) + ")", EXPECTED_HASH, StringUtility
                .encodeHex(hash));
    }

    /**
     * Test for {@link Md5#hash(String)}.
     *
     * @throws Exception
     *         on any exception
     */
    @Test
    public void testHashString() throws Exception {
        final byte[] hash = Md5.hash(TEXT);
        assertEquals("Md5.hash(\"" + TEXT + "\")", EXPECTED_HASH, StringUtility.encodeHex(hash));
    }

    /**
     * Test for {@link Md5#hash(java.io.InputStream))}.
     *
     * @throws Exception
     *         on any exception
     */
    @Test
    public void testHashInputStream() throws Exception {
        final ByteArrayInputStream is = new ByteArrayInputStream(BYTES);
        final byte[] hash = Md5.hash(is);
        assertEquals(EXPECTED_HASH, StringUtility.encodeHex(hash));
    }

    /**
     * @throws Exception
     *         on exception
     */
    @Test
    public void testHashFile() throws Exception {
        final File file = new File("src/test/resources/test.txt");
        final byte[] hash = Md5.hash(file);
        assertEquals(EXPECTED_HASH, StringUtility.encodeHex(hash));
    }

}
