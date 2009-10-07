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
 * Created Feb 19, 2008
 */
package com.acuityph.commons.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author Alistair A. Israel
 * @since 0.1.3
 */
public class CsvReader {

    private final BufferedReader in;

    private boolean trimSpaces;

    /**
     * Constructs a CsvReader around a given {@link Reader} which trims spaces
     * (or not) based on the trimSpaces parameter.
     *
     * @param in
     *        The input Reader
     * @param trimSpaces
     *        indicates whether this CsvReader should trim spaces or not
     */
    public CsvReader(final Reader in, final boolean trimSpaces) {
        super();
        if (in instanceof BufferedReader) {
            this.in = (BufferedReader) in;
        } else {
            this.in = new BufferedReader(in);
        }
        this.trimSpaces = trimSpaces;
    }

    /**
     * Constructs a CsvReader around a given {@link Reader}
     *
     * @param in
     *        The input Reader
     */
    public CsvReader(final Reader in) {
        this(in, true);
    }

    /**
     * @return the trimSpaces
     */
    public final boolean isTrimSpaces() {
        return this.trimSpaces;
    }

    /**
     * @param trimSpaces
     *        the trimSpaces to set
     */
    public final void setTrimSpaces(final boolean trimSpaces) {
        this.trimSpaces = trimSpaces;
    }

    /**
     * Reads a single CSV line and returns it as a String array. Returns null if
     * the end of file is reached.
     *
     * @return the CSV line as a String array, or null if EOF is reached
     * @throws IOException
     *         on exception
     */
    public final String[] readLine() throws IOException {
        final String line = in.readLine();
        if (line != null) {
            return CsvUtility.parse(line, trimSpaces);
        }
        return null;
    }
}
