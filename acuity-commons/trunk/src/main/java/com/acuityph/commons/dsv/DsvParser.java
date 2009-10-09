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

import java.util.regex.Pattern;

/**
 *
 * @author Alistair A. Israel
 */
public class DsvParser {

    private final char delimiter;

    private final String delimiterString;

    /**
     * @param delimiter
     *        the delimiter to parse against
     */
    public DsvParser(final char delimiter) {
        this.delimiter = delimiter;
        this.delimiterString = Pattern.quote(Character.toString(delimiter));
    }

    /**
     * @return the delimiter
     */
    public final char getDelimiter() {
        return delimiter;
    }

    /**
     * @param line
     *        the line to parse
     * @return the array of values
     */
    public final String[] parse(final String line) {
        return line.split(delimiterString);
    }

}
