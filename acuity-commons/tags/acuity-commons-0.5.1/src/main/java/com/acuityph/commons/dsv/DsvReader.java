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
 * Created Oct 9, 2009
 */
package com.acuityph.commons.dsv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author Alistair A. Israel
 */
public class DsvReader extends DsvParser {

    private final BufferedReader in;

    /**
     * @param delimiter
     *        the delimiter character to use
     * @param in
     *        the Reader
     */
    public DsvReader(final char delimiter, final Reader in) {
        super(delimiter);
        if (in instanceof BufferedReader) {
            this.in = (BufferedReader) in;
        } else {
            this.in = new BufferedReader(in);
        }
    }

    /**
     * Reads a single delimited line and returns its values as a String array.
     * Returns null if the end of file is reached.
     *
     * @return the delimited text as a String array, or null if EOF is reached
     * @throws IOException
     *         on exception
     */
    public final String[] readLine() throws IOException {
        final String line = in.readLine();
        if (line != null) {
            return parse(line);
        }
        return null;
    }

}
