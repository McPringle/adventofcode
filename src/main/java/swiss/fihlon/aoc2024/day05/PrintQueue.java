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
package swiss.fihlon.aoc2024.day05;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static java.util.function.Predicate.not;

public final class PrintQueue {

    private final @NotNull List<Integer[]> pageOrderingRules;
    private final @NotNull List<Integer[]> pagesToUpdate;

    public PrintQueue(@NotNull final String puzzleInput) {
        final var puzzleInputParts = puzzleInput.split("\n\n");
        this.pageOrderingRules = puzzleInputParts[0].lines()
                .map(line -> Arrays.stream(line.split("\\|"))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .toArray(Integer[]::new))
                .toList();
        this.pagesToUpdate = puzzleInputParts[1].lines()
                .map(line -> Arrays.stream(line.split(","))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .toArray(Integer[]::new))
                .toList();
    }

    public int part1() {
        return pagesToUpdate.stream()
                .filter(this::isInCorrectOrder)
                .map(this::middlePageNumber)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int part2() {
        return pagesToUpdate.stream()
                .filter(not(this::isInCorrectOrder))
                .map(this::sortPages)
                .map(this::middlePageNumber)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Integer[] sortPages(@NotNull final Integer[] pages) {
        final var sortedPages = Arrays.copyOf(pages, pages.length);
        while (!isInCorrectOrder(sortedPages)) {
            for (int i = 0; i < sortedPages.length - 1; i++) {
                final var page1 = sortedPages[i];
                final var page2 = sortedPages[i + 1];
                boolean pageOrderOkay = false;
                for (final Integer[] rule : pageOrderingRules) {
                    if (rule[0].equals(page2) && rule[1].equals(page1)) {
                        sortedPages[i] = page2;
                        sortedPages[i + 1] = page1;
                        pageOrderOkay = true;
                        break;
                    }
                }
                if (pageOrderOkay) {
                    break;
                }
            }
        }
        return sortedPages;
    }

    private boolean isInCorrectOrder(@NotNull final Integer[] pages) {
        for (int i = 0; i < pages.length - 1; i++) {
            final var page1 = pages[i];
            final var page2 = pages[i + 1];
            boolean pageOrderOkay = false;
            for (final Integer[] rule : pageOrderingRules) {
                if (rule[0].equals(page1) && rule[1].equals(page2)) {
                    pageOrderOkay = true;
                    break;
                }
            }
            if (!pageOrderOkay) {
                return false;
            }
        }
        return true;
    }

    private int middlePageNumber(@NotNull final Integer[] pages) {
        final var middle = pages.length / 2;
        return pages[middle];
    }

}
