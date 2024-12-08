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
package swiss.fihlon.aoc2024.day07;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BridgeRepairTest {

    private static String puzzleInput;

    @BeforeAll
    static void setUp() {
        puzzleInput = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20
                """;
    }

    @Test
    void part1() {
        final BridgeRepair bridgeRepair = new BridgeRepair(puzzleInput);
        assertEquals(3749, bridgeRepair.part1());
    }

    @Test
    void part2() {
        final BridgeRepair bridgeRepair = new BridgeRepair(puzzleInput);
        assertEquals(11387, bridgeRepair.part2());
    }

}
