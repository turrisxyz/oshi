/*
 * MIT License
 *
 * Copyright (c) 2022 The OSHI Project Contributors: https://github.com/oshi/oshi/graphs/contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package oshi.driver.windows.registry;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@EnabledOnOs(OS.WINDOWS)
class RegistryDriversTest {

    @Test
    void testProcessPerformanceData() {
        Map<Integer, ProcessPerformanceData.PerfCounterBlock> processMap = ProcessPerformanceData
                .buildProcessMapFromRegistry(null);
        assertNotNull(processMap);
        assertThat("Process map should not be empty", processMap, is(not(anEmptyMap())));
    }

    @Test
    void testThreadPerformanceData() {
        Map<Integer, ThreadPerformanceData.PerfCounterBlock> threadMap = ThreadPerformanceData
                .buildThreadMapFromRegistry(null);
        assertNotNull(threadMap);
        assertThat("Thread map should not be empty", threadMap, is(not(anEmptyMap())));
    }

    @Test
    void testSessionWtsData() {
        assertThat("Sessions list from registry should not be empty", HkeyUserData.queryUserSessions(),
                is(not(empty())));
        assertDoesNotThrow(SessionWtsData::queryUserSessions);
        assertDoesNotThrow(NetSessionData::queryUserSessions);
    }

    @Test
    void testProcessWtsData() {
        assertThat("Process WTS map should not be empty", ProcessWtsData.queryProcessWtsMap(null),
                is(not(anEmptyMap())));
    }
}
