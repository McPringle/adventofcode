/*
 * Advent of Code - An Advent calendar of small programming puzzles
 * Copyright (C) Marcus Fihlon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package swiss.fihlon.aoc2024.day06;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Day06 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day06.class);

    public static void main(@NotNull final String... args) throws IOException {
        LOGGER.info("Advent of Code 2024 Day 6: Guard Gallivant");

        final var puzzleInput = Files.readString(Path.of("src/main/resources/2024/day06.txt"), StandardCharsets.UTF_8);

        final var guardGallivant = new GuardGallivant(puzzleInput);
        LOGGER.info("part 1: {}", guardGallivant.part1());
    }

    private Day06() {
        throw new IllegalStateException("This class can't be instantiated!");
    }

}
