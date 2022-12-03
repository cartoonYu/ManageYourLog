package org.manage.log.receive.base;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.manage.log.base.test.BaseTest;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 16:12
 */
@Suite
@SelectPackages({"org.manage.log.receive"})
@ExcludePackages({"org.manage.log.receive.base"})
public class ReceiveTestAll extends BaseTest {
}
