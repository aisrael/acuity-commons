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
package test;

/**
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public class MyImmutablePojo {

    private final String name;

    /**
     * @param name
     *        the name
     */
    public MyImmutablePojo(final String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

}
