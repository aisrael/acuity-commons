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
 * Created May 18, 2009
 */
package com.acuityph.commons.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit test for {@link Compute}.
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public final class ComputeTest {

    /**
     * Test method for {@link Compute#hashCode(java.lang.Object[])}.
     */
    @Test
    public void testComputeHashCode() {
        assertEquals(-1130372823, Compute.hashCode("apple", "banana", 123));
    }

}
