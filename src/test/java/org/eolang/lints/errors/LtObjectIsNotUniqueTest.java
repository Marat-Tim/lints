/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2024 Objectionary.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.eolang.lints.errors;

import com.jcabi.xml.XML;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.eolang.lints.ParsedEo;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link LtObjectIsNotUnique}.
 *
 * @since 0.0.30
 */
final class LtObjectIsNotUniqueTest {

    /**
     * Prefix to EO snippet objects.
     */
    private static final String EO_PREFIX = "org/eolang/lints/errors/object-is-not-unique/";

    @Test
    void catchesDuplicates() throws Exception {
        MatcherAssert.assertThat(
            "Defects are empty, but they should not",
            new LtObjectIsNotUnique().defects(
                new MapOf<String, XML>(
                    new MapEntry<>(
                        "foo",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "foo").value()
                    ),
                    new MapEntry<>(
                        "bar-with-foo",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "bar-with-foo").value()
                    )
                )
            ),
            Matchers.hasSize(Matchers.greaterThan(0))
        );
    }

    @Test
    void catchesDuplicatesAcrossMultipleObjects() throws Exception {
        MatcherAssert.assertThat(
            "Defects are empty, but they should not",
            new LtObjectIsNotUnique().defects(
                new MapOf<String, XML>(
                    new MapEntry<>(
                        "test-1",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "test-1").value()
                    ),
                    new MapEntry<>(
                        "test-2",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "test-2").value()
                    )
                )
            ),
            Matchers.hasSize(Matchers.greaterThan(0))
        );
    }

    @Test
    void allowsAllUnique() throws Exception {
        MatcherAssert.assertThat(
            "Defects aren't empty, but they should",
            new LtObjectIsNotUnique().defects(
                new MapOf<String, XML>(
                    new MapEntry<>(
                        "c",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "c").value()
                    ),
                    new MapEntry<>(
                        "e",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "e").value()
                    )
                )
            ),
            Matchers.emptyIterable()
        );
    }

    @Test
    void allowsNonUniqueInDifferentPackages() throws Exception {
        MatcherAssert.assertThat(
            "Defects aren't empty, but they should",
            new LtObjectIsNotUnique().defects(
                new MapOf<String, XML>(
                    new MapEntry<>(
                        "baz",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "baz").value()
                    ),
                    new MapEntry<>(
                        "baz-packaged",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "baz-packaged").value()
                    )
                )
            ),
            Matchers.emptyIterable()
        );
    }

    @Test
    void allowsNonUniqueMultipleObjectsInDifferentPackages() throws Exception {
        MatcherAssert.assertThat(
            "Defects aren't empty, but they should",
            new LtObjectIsNotUnique().defects(
                new MapOf<String, XML>(
                    new MapEntry<>(
                        "mul",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "mul").value()
                    ),
                    new MapEntry<>(
                        "mul-packaged",
                        new ParsedEo(LtObjectIsNotUniqueTest.EO_PREFIX, "mul-packaged").value()
                    )
                )
            ),
            Matchers.emptyIterable()
        );
    }
}
