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

/**
 * An immutable holder for named constructor or method parameters (because Java
 * reflection doesn't reveal parameter names).
 *
 * @param <T>
 *        a type
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public class NamedParameter<T> {

    private final String name;

    private final Class<T> type;

    /**
     * @param name
     *        the parameter name
     * @param type
     *        the parameter type
     */
    public NamedParameter(final String name, final Class<T> type) {
        super();
        this.name = name;
        this.type = type;
    }

    /**
     * @param <T>
     *        a type
     * @param name
     *        the parameter name
     * @param type
     *        the class of type &lt;T&gt;
     * @return {@link NamedParameter}
     */
    public static <T> NamedParameter<T> parameter(final String name, final Class<T> type) {
        return new NamedParameter<T>(name, type);
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public final Class<T> getType() {
        return type;
    }

}
