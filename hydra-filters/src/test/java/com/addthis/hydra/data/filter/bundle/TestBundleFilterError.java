/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.addthis.hydra.data.filter.bundle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBundleFilterError {

    @Test
    public void testMessage() {
        boolean success = false;
        BundleFilterError filter = new BundleFilterError().setMessage("hello world");
        filter.open();
        try {
            filter.filter(null);
        } catch (Exception ex) {
            success = true;
            assertEquals("hello world", ex.getMessage());
        }
        assertTrue(success);
    }

    @Test
    public void testType() {
        boolean success = false;
        BundleFilterError filter = new BundleFilterError()
                .setMessage("hello world").setType("java.lang.NullPointerException");
        filter.open();
        try {
            filter.filter(null);
        } catch (Exception ex) {
            success = true;
            assertEquals("hello world", ex.getMessage());
            assertEquals("java.lang.NullPointerException", ex.getClass().getCanonicalName());
        }
        assertTrue(success);
    }

    @Test
    public void testBogusType() {
        boolean success = false;
        BundleFilterError filter = new BundleFilterError()
                .setMessage("hello world").setType("blahblah").setSuppressLogging(true);
        filter.open();
        try {
            filter.filter(null);
        } catch (Exception ex) {
            success = true;
            assertEquals("hello world", ex.getMessage());
            assertEquals("java.lang.RuntimeException", ex.getClass().getCanonicalName());
        }
        assertTrue(success);
    }

}
