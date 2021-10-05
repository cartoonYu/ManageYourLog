package org.manageyourlogtest;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 17:25
 */
@RunWith(JUnitPlatform.class)
@SelectPackages("org.manageyourlogtest")
@SuiteDisplayName("Test all")
public class TestAll {
}
