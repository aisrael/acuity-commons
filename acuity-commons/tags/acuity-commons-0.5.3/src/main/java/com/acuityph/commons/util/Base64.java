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

/**
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class Base64 {

    private static final char[] BASE64_ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    private static final char EOF = '=';

    private static final byte[] BASE64_LOOKUPTABLE = new byte[256];
    static {
        for (int i = 0; i < 256; i++) {
            BASE64_LOOKUPTABLE[i] = -1;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            BASE64_LOOKUPTABLE[i] = (byte) (i - 'A');
        }
        for (int i = 'a'; i <= 'z'; i++) {
            BASE64_LOOKUPTABLE[i] = (byte) (26 + i - 'a');
        }
        for (int i = '0'; i <= '9'; i++) {
            BASE64_LOOKUPTABLE[i] = (byte) (52 + i - '0');
        }
        BASE64_LOOKUPTABLE['+'] = 62;
        BASE64_LOOKUPTABLE['/'] = 63;
    }

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Base64() {
        // noop
    }

    /**
     * Encode a String to Base64. Simply delegates to {@link #encode(byte[])}.
     *
     * @param s
     *        The String to encode
     * @return The Base64 representation of the given String
     */
    public static String encode(final String s) {
        return encode(s.getBytes());
    }

    /**
     * Encode an array of bytes to Base64.
     *
     * @param bytes
     *        The byte array to encode
     * @return The Base64 representation of the given bytes.
     */
    public static String encode(final byte[] bytes) {
        final int targetLen = ((bytes.length + 2) / 3) * 4;
        final char[] out = new char[targetLen];
        out[targetLen - 1] = EOF;
        out[targetLen - 2] = EOF;

        for (int i = 0, index = 0; i < bytes.length; i += 3, index += 4) {
            final boolean trip = (i + 1) < bytes.length;
            final boolean quad = (i + 2) < bytes.length;

            int val = (0xFF & bytes[i]);
            val <<= 8;
            if (trip) {
                val |= (0xFF & bytes[i + 1]);
            }
            val <<= 8;
            if (quad) {
                val |= (0xFF & bytes[i + 2]);
                out[index + 3] = BASE64_ALPHABET[val & 0x3F];
            }
            val >>= 6;
            if (trip) {
                out[index + 2] = BASE64_ALPHABET[val & 0x3F];
            }
            val >>= 6;
            out[index + 1] = BASE64_ALPHABET[val & 0x3F];
            val >>= 6;
            out[index + 0] = BASE64_ALPHABET[val & 0x3F];
        }
        return new String(out);
    }

    /**
     * @param data
     *        char[]
     * @return calculated length of decoded string
     */
    private static int calcDecodedBase64Len(final char[] data) {
        // as our input could contain non-BASE64 data (newlines,
        // whitespace of any sort, whatever) we must first adjust
        // our count of USABLE data so that...
        // (a) we don't misallocate the output array, and
        // (b) think that we miscalculated our data length
        // just because of extraneous throw-away junk
        int tempLen = data.length;
        for (int ix = 0; ix < data.length; ix++) {
            if ((data[ix] > 255) || BASE64_LOOKUPTABLE[data[ix]] < 0) {
                --tempLen; // ignore non-valid chars and padding
            }
        }

        // calculate required length:
        // -- 3 bytes for every 4 valid base64 chars
        // -- plus 2 bytes if there are 3 extra base64 chars,
        // or plus 1 byte if there are 2 extra.
        int len = (tempLen / 4) * 3;
        if ((tempLen % 4) == 3) {
            len += 2;
        } else if ((tempLen % 4) == 2) {
            len += 1;
        }
        return len;
    }

    /**
     * <p>
     * Decode a Base64 encoded string into a byte array.
     * </p>
     * <p>
     * NOTE: throws IllegalArgumentException if the input string length isn't
     * divisible by four.
     * </p>
     *
     * @param s
     *        Base64 encoded string
     * @return The decoded byte array
     */
    public static byte[] decode(final String s) {
        if (s.length() % 4 != 0) {
            throw new IllegalArgumentException("Input string length not a multiple of four!");
        }

        final char[] data = s.toCharArray();
        final int len = calcDecodedBase64Len(data);

        final byte[] out = new byte[len];

        int shift = 0; // # of excess bits stored in accum
        int accum = 0; // excess bits
        int index = 0;

        // we now go through the entire array (NOT using the 'tempLen' value)
        for (int i = 0; i < data.length; i++) {
            final int value;
            if (data[i] > 255) {
                value = -1;
            } else {
                value = BASE64_LOOKUPTABLE[data[i]];
            }

            // skip over non-code
            if (value >= 0) {
                accum <<= 6; // bits shift up by 6 each time thru
                shift += 6; // loop, with new bits being put in
                accum |= value; // at the bottom.
                // whenever there are 8 or more shifted in,
                if (shift >= 8) {
                    shift -= 8; // write them out (from the top, leaving any
                    out[index++] = // excess at the bottom for next iteration.
                            (byte) ((accum >> shift) & 0xFF);
                }
            }
            // we will also have skipped processing a padding null byte ('=')
            // here; these are used ONLY for padding to an even length and do
            // not legally occur as encoded data. for this reason we can ignore
            // the fact that no index++ operation occurs in that special case:
            // the out[] array is initialized to all-zero bytes to start with
            // and that works to our advantage in this combination.
        }

        // if there is STILL something wrong we just have to throw up now!
        if (index != out.length) {
            throw new Error("Miscalculated data length (wrote " + index + " instead of "
                    + out.length + ")");
        }

        return out;
    }
}
