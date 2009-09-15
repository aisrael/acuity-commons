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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * Md5 is a utility class that provides static utility methods for computing MD5
 * hashes.
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class Md5 {

    /**
     * The MD5 message digest name.
     */
    public static final String MD5 = "MD5";

    /**
     * The UTF-8 character set.
     */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * Use an 8KB buffer.
     */
    private static final int BUF_SIZE = 8 * 1024;

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Md5() {
        // noop
    }

    /**
     * @param bytes
     *        the bytes to digest
     * @return the MD5 hash of the given bytes
     * @throws GeneralSecurityException
     *         on exception
     */
    public static byte[] hash(final byte[] bytes) throws GeneralSecurityException {
        final MessageDigest digest = MessageDigest.getInstance(MD5);
        return digest.digest(bytes);
    }

    /**
     * @param s
     *        a String
     * @return the MD5 hash of the string's UTF-8 representation
     * @throws GeneralSecurityException
     *         on exception
     */
    public static byte[] hash(final String s) throws GeneralSecurityException {
        return hash(s.getBytes(UTF8));
    }

    /**
     * Compute the MD5 hash of a given {@link InputStream}. Will attempt to read
     * bytes until the end of the stream has been reached.
     *
     * @param is
     *        the InputStream to read bytes from
     * @return the MD5 digest
     * @throws GeneralSecurityException
     *         on exception
     * @throws IOException
     *         on exception
     */
    public static byte[] hash(final InputStream is) throws GeneralSecurityException,
            IOException {
        final MessageDigest digest = MessageDigest.getInstance(MD5);
        final byte[] buf = new byte[BUF_SIZE];
        int read = is.read(buf);
        while (read > 0) {
            digest.update(buf, 0, read);
            read = is.read(buf);
        }
        return digest.digest();
    }

    /**
     * Read a file and compute the MD5 hash of its contents.
     *
     * @param file
     *        the file to compute the MD5 hash for
     * @return the MD5 digest
     * @throws IOException
     *         on exception
     * @throws GeneralSecurityException
     *         on exception
     */
    public static byte[] hash(final File file) throws IOException, GeneralSecurityException {
        final FileInputStream is = new FileInputStream(file);
        try {
            return hash(is);
        } finally {
            is.close();
        }
    }
}
