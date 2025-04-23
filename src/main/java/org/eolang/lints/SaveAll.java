/*
 * SPDX-FileCopyrightText: Copyright (c) 2016-2025 Objectionary.com
 * SPDX-License-Identifier: MIT
 */
package org.eolang.lints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.cactoos.iterable.Joined;

public class SaveAll {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalStateException("First arg must exist and point to version");
        }
        Stream.Builder<String> names = Stream.builder();
        new Joined<Lint<?>>(
            new PkWpa(),
            new PkMono()
        ).forEach(lint -> {
            names.add(lint.name());
            try {
                Files.writeString(
                    Path.of(
                        "gh-pages",
                        args[0] + "-md",
                        lint.name() + ".md"
                    ),
                    lint.motive()
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Files.writeString(
            Path.of("gh-pages/README.md"),
            String.format(
                String.join(
                    "\n",
                    "Latest version: %s",
                    "",
                    "List of lints in this version:",
                    "",
                    names.build()
                        .map(
                            name -> String.format(
                                "- [%1$s](%2$s/%1$s.md)",
                                name,
                                args[0] + "-md"
                            )
                        )
                        .collect(Collectors.joining("\n")),
                    "",
                    String.format(
                        "Published on %s.",
                        ZonedDateTime.now(ZoneOffset.UTC).format(
                            DateTimeFormatter.ofPattern(
                                "EEE MMM dd HH:mm:ss zzz yyyy",
                                Locale.ENGLISH
                            )
                        )
                    )
                ),
                args[0]
            )
        );
    }
}
