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
 * Created Feb 18, 2008
 */
package com.acuityph.commons.csv;

import java.util.ArrayList;

/**
 * @author Alistair A. Israel
 * @since 0.1.3
 */
public final class CsvUtility {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private CsvUtility() {
        // noop
    }

    /**
     * Parses a single line (String) of comma-separated values.
     *
     * @param line
     *        The CSV line of text
     * @return An array of Strings
     */
    public static String[] parse(final String line) {
        return parse(line, false);
    }

    /**
     * Parses a single line (String) of comma-separated values.
     *
     * @param line
     *        The CSV line of text
     * @param trimSpaces
     *        whether to trim any leading/trailing spaces
     * @return An array of Strings
     */
    public static String[] parse(final String line, final boolean trimSpaces) {
        final ArrayList<String> results = new ArrayList<String>();

        int i = 0;
        final int length = line.length();
        while (i < length) {
            final StringBuilder sb = new StringBuilder();
            if (trimSpaces) {
                while (i < length && Character.isWhitespace(line.charAt(i))) {
                    ++i;
                }
            }
            while (i < length && line.charAt(i) != ',') {
                sb.append(line.charAt(i));
                ++i;
            }
            // at this point, should be a comma
            ++i;
            final String s = trimIfNeeded(sb, trimSpaces);
            results.add(s);
        }

        return results.toArray(new String[results.size()]);
    }

    /**
     * @param sb
     *        the {@link StringBuilder}
     * @param trimSpaces
     *        true if we should trim spaces from the string we're building
     * @return sb.toString(), trimmed if trimSpaces is true
     */
    private static String trimIfNeeded(final StringBuilder sb, final boolean trimSpaces) {
        final String s;
        if (trimSpaces) {
            s = sb.toString().trim();
        } else {
            s = sb.toString();
        }
        return s;
    }
}
