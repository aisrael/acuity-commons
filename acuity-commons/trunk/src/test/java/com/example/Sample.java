/**
 * Copyright (c) 2008-2010 Acuity Technologies, Inc.
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
 * Created Nov 9, 2010
 */
package com.example;

import java.util.Date;

/**
 * The Sample class.
 */
// CHECKSTYLE:OFF
@SuppressWarnings("unused")
public final class Sample {

    /**
     * The created at.
     */
    private Date createdAt;

    /**
     * The is active.
     */
    private boolean isActive;

    /**
     * The is expired.
     */
    private boolean isExpired;

    /**
     * A private method.
     */
    private void privateMethod() {
    }

    /**
     * A protected method.
     */
    protected void protectedMethod() {
    }

    /**
     * A public method.
     */
    public void publicMethod() {
    }

    /**
     * A public method with one parameter.
     */
    public void publicMethodWithOneParameter(final String s) {
    }

    /**
     * A static method.
     */
    public static void staticMethod() {
    }

    /**
     * Public, returns void, "$set[A-Z]", one parameter, but static
     */
    public static void setFactory(final Object o) {
    }

    /**
     * Public, returns void, "$get[A-Z]", one parameter, but static
     */
    public static Object getFactory() {
        return null;
    }

    /**
     * Gets the created at.
     *
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the created at.
     *
     * @param createdAt
     *            the createdAt to set
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Public, returns something, "$get[A-Z]" but expects parameter
     */
    public Character getCharacterAt(final int index) {
        return null;
    }

    /**
     * Checks if is active.
     *
     * @return the isActive
     */
    private boolean isActive() {
        return isActive;
    }

    /**
     *
     */
    private void setActive(final boolean isActive) {
        this.isActive = isActive;
    }

    /**
     *
     */
    public boolean isExpired() {
        return isExpired;
    }

    /**
     *
     */
    public void setExpired() {
        this.isExpired = true;
    }

}
