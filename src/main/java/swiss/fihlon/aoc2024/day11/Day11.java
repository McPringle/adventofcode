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
package swiss.fihlon.aoc2024.day11;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Day11 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day11.class);

    public static void main(@NotNull final String... args) throws IOException {
        LOGGER.info("Advent of Code 2024 Day 11: Plutonian Pebbles");

        final var puzzleInput = Files.readAllLines(Path.of("src/main/resources/2024/day11.txt"), StandardCharsets.UTF_8).getFirst();

        final var plutonianPebbles = new PlutonianPebbles(puzzleInput);
        LOGGER.info("part 1: {}", plutonianPebbles.solve(25));
        LOGGER.info("part 2: {}", plutonianPebbles.solve(75));
    }

    private Day11() {
        throw new IllegalStateException("This class can't be instantiated!");
    }

}
