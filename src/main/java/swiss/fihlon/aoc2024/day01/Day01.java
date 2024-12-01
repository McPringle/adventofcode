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
package swiss.fihlon.aoc2024.day01;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public final class Day01 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Day01.class);

    public static void main(@NotNull final String... args) throws IOException {
        LOGGER.info("Advent of Code 2024 Day 1: Historian Hysteria");

        final var input = Files.readAllLines(Path.of("src/main/resources/2024/day01.txt"), StandardCharsets.UTF_8);
        final var leftList = new ArrayList<Integer>(input.size());
        final var rightList = new ArrayList<Integer>(input.size());

        input.forEach(line -> {
            if (!line.isBlank()) {
                final var numbers = line.split("\\s+");
                leftList.add(Integer.parseInt(numbers[0].trim()));
                rightList.add(Integer.parseInt(numbers[1].trim()));
            }
        });

        final var historianHysteria = new HistorianHysteria(leftList, rightList);
        LOGGER.info("total distance: {}", historianHysteria.totalDistance());
        LOGGER.info("similarity score: {}", historianHysteria.similarityScore());
    }

    private Day01() {
        throw new IllegalStateException("This class can't be instantiated!");
    }

}
