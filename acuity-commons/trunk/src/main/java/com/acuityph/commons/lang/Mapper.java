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
 * A generic interface for class that map or transform an object from one class
 * to another object of another class.
 *
 * @param <S>
 *        the source type
 * @param <T>
 *        the target type
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public interface Mapper<S, T> {

    /**
     * Map the given object from its source type to the target type
     *
     * @param obj
     *        the source object
     * @return the target object
     */
    T map(final S obj);

    /**
     * An abstract base class for implementing concrete Mappers. Holds a
     * reference to the target class (needed to instantiate new instances) of
     * the class.
     *
     * @param <S>
     *        the source type
     * @param <T>
     *        the target type
     * @author Alistair A. Israel
     */
    abstract class Base<S, T> implements Mapper<S, T> {

        private final Class<T> targetClass;

        /**
         * @param targetClass
         *        the target class
         */
        protected Base(final Class<T> targetClass) {
            super();
            this.targetClass = targetClass;
        }

        /**
         * @return the targetClass
         */
        public final Class<T> getTargetClass() {
            return targetClass;
        }

    }
}
