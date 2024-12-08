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

import java.util.Arrays;

public final class GuardGallivant {

    private static final char[] GUARD_SIGNS = new char[] {'^', '>', 'v', '<'};

    private final char[][] lab;
    private final int rowCount;
    private final int colCount;

    public GuardGallivant(@NotNull final String puzzleInput) {
        this.lab = puzzleInput
                .lines()
                .filter(line -> !line.isBlank())
                .map(String::toCharArray)
                .toArray(char[][]::new);
        this.rowCount = this.lab.length;
        this.colCount = this.lab[0].length;
    }

    public int part1() {
        var guardPosition = getGuardPosition();
        while (guardPosition.row >= 0 && guardPosition.col >= 0) {
            guardPosition = moveGuard(guardPosition, false);
        }
        return Arrays.stream(lab)
                .mapToInt(this::count)
                .sum();
    }

    private int count(final char[] chars) {
        int count = 0;
        for (char ch : chars) {
            if (ch == 'X') {
                count++;
            }
        }
        return count;
    }

    private Position getGuardPosition() {
        for (int x = 0; x < lab.length; x++) {
            for (int y = 0; y < lab[x].length; y++) {
                final var sign = lab[x][y];
                if (isGuard(sign)) {
                    return new Position(x, y);
                }
            }
        }
        return new Position(-1, -1);
    }

    private boolean isGuard(final char sign) {
        for (char guardSign : GUARD_SIGNS) {
            if (guardSign == sign) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("java:S3776")
    private Position moveGuard(final Position position, final boolean track) {
        final var guard = lab[position.row][position.col];

        if ((guard == '^' && position.row == 0)
                || (guard == '>' && position.col == colCount - 1)
                || (guard == 'v' && position.row == rowCount - 1)
                || (guard == '<' && position.col == 0)) {
            lab[position.row][position.col] = markPosition(guard, track);
            return new Position(-1, -1);
        }

        if ((guard == '^' && lab[position.row - 1][position.col] == '#')
                || (guard == '>' && lab[position.row][position.col + 1] == '#')
                || (guard == 'v' && lab[position.row + 1][position.col] == '#')
                || (guard == '<' && lab[position.row][position.col - 1] == '#')) {
            lab[position.row][position.col] = turnGuard(guard);
            return position;
        }

        if (guard == '^') {
            lab[position.row - 1][position.col] = guard;
            lab[position.row][position.col] = 'X';
            return new Position(position.row - 1, position.col);
        }

        if (guard == '>') {
            lab[position.row][position.col + 1] = guard;
            lab[position.row][position.col] = 'X';
            return new Position(position.row, position.col + 1);
        }

        if (guard == 'v') {
            lab[position.row + 1][position.col] = guard;
            lab[position.row][position.col] = 'X';
            return new Position(position.row + 1, position.col);
        }

        if (guard == '<') {
            lab[position.row][position.col - 1] = guard;
            lab[position.row][position.col] = 'X';
            return new Position(position.row, position.col - 1);
        }

        throw new IllegalStateException("Error moving guard");
    }

    private char markPosition(final char guard, final boolean track) {
        if (!track) {
            return 'X';
        }
        return switch (guard) {
            case '^', 'v' -> '|';
            case '>', '<' -> '-';
            default -> throw new IllegalStateException("Unexpected value: " + guard);
        };
    }

    private char turnGuard(final char guard) {
        return switch (guard) {
            case '^' -> '>';
            case '>' -> 'v';
            case 'v' -> '<';
            case '<' -> '^';
            default -> throw new IllegalStateException("Unexpected value: " + guard);
        };
    }

    record Position(int row, int col) { }
}
