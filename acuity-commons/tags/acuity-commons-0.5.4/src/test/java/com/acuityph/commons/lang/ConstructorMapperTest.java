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
 * Created May 27, 2009
 */
package com.acuityph.commons.lang;

import static com.acuityph.commons.lang.ConstructorMapper.construct;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import test.MyEntity;
import test.MyImmutablePojo;

/**
 * JUnit test for {@link ConstructorMapper}.
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public class ConstructorMapperTest {

    /**
     * Test method for {@link ConstructorMapper#map(java.lang.Object)} .
     */
    @Test
    public final void testMap() {
        final Mapper<MyEntity, MyImmutablePojo> mapper =
                construct(MyImmutablePojo.class).from(MyEntity.class).using("name");

        final MyEntity myEntity = new MyEntity();
        myEntity.setId(1);
        myEntity.setName("name");

        final MyImmutablePojo pojo = mapper.map(myEntity);
        assertNotNull(pojo);
    }
}
