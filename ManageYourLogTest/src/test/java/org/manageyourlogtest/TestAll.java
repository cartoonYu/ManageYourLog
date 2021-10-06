package org.manageyourlogtest;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;
import org.manageyourlogtest.base.BaseTest;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 16:12
 */
@RunWith(JUnitPlatform.class)
@SelectPackages({"org.manageyourlogtest"})
public class TestAll extends BaseTest {
}
