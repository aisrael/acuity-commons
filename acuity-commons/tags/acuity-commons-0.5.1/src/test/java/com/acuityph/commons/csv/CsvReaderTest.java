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
 * Created Feb 20, 2008
 */
package com.acuityph.commons.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * JUnit test case for {@link CsvReader}.
 *
 * @author Alistair A. Israel
 * @since 0.1.3
 */
public final class CsvReaderTest extends TestCase {

    /**
     * @throws IOException
     *         on exception
     */
    public void testCsvReader() throws IOException {
        final InputStream is = this.getClass().getResourceAsStream("test.csv");
        final InputStreamReader in = new InputStreamReader(is);
        final CsvReader csvReader = new CsvReader(in);
        final List<String[]> lines = new ArrayList<String[]>();
        String[] line = csvReader.readLine();
        while (line != null) {
            lines.add(line);
            for (final String s : line) {
                assertEquals(s.trim(), s);
            }
            line = csvReader.readLine();
        }
        final int expectedLines = 3;
        assertEquals(expectedLines, lines.size());
    }
}
