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
package swiss.fihlon.aoc2024.day04;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public final class CeresSearch {

    private final char[][] puzzle;

    public CeresSearch(@NotNull final String puzzleInput) {
        this.puzzle = puzzleInput
                .toLowerCase(Locale.getDefault())
                .lines()
                .filter(line -> !line.isBlank())
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    @SuppressWarnings("java:S3776")
    public int wordCount() {
        int count = 0;

        final var rowCount = puzzle.length;
        final var colCount = puzzle[0].length;

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                final var x = puzzle[row][col];
                if (x != 'x') {
                    continue;
                }

                // horizontal forward
                if (col + 3 < colCount
                        && puzzle[row][col + 1] == 'm'
                        && puzzle[row][col + 2] == 'a'
                        && puzzle[row][col + 3] == 's') {
                    count++;
                }

                // horizontal backward
                if (col >= 3
                        && puzzle[row][col - 1] == 'm'
                        && puzzle[row][col - 2] == 'a'
                        && puzzle[row][col - 3] == 's') {
                    count++;
                }

                // vertical up
                if (row >= 3
                        && puzzle[row - 1][col] == 'm'
                        && puzzle[row - 2][col] == 'a'
                        && puzzle[row - 3][col] == 's') {
                    count++;
                }

                // vertical down
                if (row + 3 < rowCount
                        && puzzle[row + 1][col] == 'm'
                        && puzzle[row + 2][col] == 'a'
                        && puzzle[row + 3][col] == 's') {
                    count++;
                }

                // diagonal down right
                if (row + 3 < rowCount
                        && col + 3 < colCount
                        && puzzle[row + 1][col + 1] == 'm'
                        && puzzle[row + 2][col + 2] == 'a'
                        && puzzle[row + 3][col + 3] == 's') {
                    count++;
                }

                // diagonal down left
                if (row + 3 < rowCount
                        && col >= 3
                        && puzzle[row + 1][col - 1] == 'm'
                        && puzzle[row + 2][col - 2] == 'a'
                        && puzzle[row + 3][col - 3] == 's') {
                    count++;
                }

                // diagonal up right
                if (row >= 3
                        && col + 3 < colCount
                        && puzzle[row - 1][col + 1] == 'm'
                        && puzzle[row - 2][col + 2] == 'a'
                        && puzzle[row - 3][col + 3] == 's') {
                    count++;
                }

                // diagonal up left
                if (row >= 3
                        && col >= 3
                        && puzzle[row - 1][col - 1] == 'm'
                        && puzzle[row - 2][col - 2] == 'a'
                        && puzzle[row - 3][col - 3] == 's') {
                    count++;
                }

            }
        }

        return count;
    }

    public int masCount() {
        int count = 0;

        final var rowCount = puzzle.length;
        final var colCount = puzzle[0].length;

        for (int row = 1; row < rowCount - 1; row++) {
            for (int col = 1; col < colCount - 1; col++) {
                final var a = puzzle[row][col];
                if (a != 'a') {
                    continue;
                }

                if (((puzzle[row - 1][col - 1] == 'm' && puzzle[row + 1][col + 1] == 's')
                        || (puzzle[row - 1][col - 1] == 's' && puzzle[row + 1][col + 1] == 'm')
                ) && ((puzzle[row + 1][col - 1] == 'm' && puzzle[row - 1][col + 1] == 's')
                        || (puzzle[row + 1][col - 1] == 's' && puzzle[row - 1][col + 1] == 'm')
                )) {
                    count++;
                }
            }
        }

        return count;
    }
}
