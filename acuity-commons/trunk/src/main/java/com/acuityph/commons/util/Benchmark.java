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
 * Created May 19, 2009
 */
package com.acuityph.commons.util;

import java.util.concurrent.Callable;

import org.springframework.util.StopWatch;

/**
 * Extends {@link StopWatch} and provides convenience methods that take in
 * (closure-like) call backs (such as {@link Runnable}) and benchmarks those.
 *
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public class Benchmark extends StopWatch {

    /**
     * Benchmark a named {@link Runnable} and return the results.
     *
     * @param taskName
     *        the task name
     * @param runnable
     *        the {@link Runnable} to benchmark
     * @return the {@link Benchmark} object containing the results
     */
    public static Benchmark measure(final String taskName, final Runnable runnable) {
        final Benchmark bm = new Benchmark(taskName);
        bm.run(taskName, runnable);
        return bm;
    }

    /**
     * Benchmark a {@link Runnable} and return the results.
     *
     * @param runnable
     *        the {@link Runnable} to benchmark
     * @return the {@link Benchmark} object containing the results
     */
    public static Benchmark measure(final Runnable runnable) {
        final Benchmark bm = new Benchmark();
        bm.run(runnable);
        return bm;
    }

    /**
     * Construct a Benchmark object around a {@link StopWatch} with the given
     * id.
     *
     * @param id
     *        identifier for the underlying stop watch. Handy when we have
     *        output from multiple benchmarks and need to distinguish between
     *        them.
     */
    public Benchmark(final String id) {
        super(id);
    }

    /**
     * Default constructor, creates a Benchmark around an anonymous
     * {@link StopWatch}.
     */
    public Benchmark() {
        // noop
    }

    /**
     * Run and benchmark a named {@link Runnable} task.
     *
     * @param taskName
     *        the task name
     * @param runnable
     *        the {@link Runnable} to run
     */
    public final void run(final String taskName, final Runnable runnable) {
        start(taskName);
        try {
            runnable.run();
        } finally {
            stop();
        }
    }

    /**
     * Run and benchmark a {@link Runnable} task.
     *
     * @param runnable
     *        the {@link Runnable} to run
     */
    public final void run(final Runnable runnable) {
        start();
        try {
            runnable.run();
        } finally {
            stop();
        }
    }

    /**
     * Run and benchmark a named {@link Callable} and return its result.
     *
     * @param <T>
     *        a type
     * @param taskName
     *        the task name
     * @param callable
     *        the {@link Callable} to call
     * @return the result of the call
     * @throws Exception
     *         on exception
     */
    public final <T> T call(final String taskName, final Callable<T> callable) throws Exception {
        start(taskName);
        T result = null;
        try {
            result = callable.call();
        } finally {
            stop();
        }
        return result;
    }

    /**
     * Run and benchmark a {@link Callable} and return its result.
     *
     * @param <T>
     *        a type
     * @param callable
     *        the {@link Callable} to call
     * @return the result of the call
     * @throws Exception
     *         on exception
     */
    public final <T> T call(final Callable<T> callable) throws Exception {
        start();
        T result = null;
        try {
            result = callable.call();
        } finally {
            stop();
        }
        return result;
    }
}
