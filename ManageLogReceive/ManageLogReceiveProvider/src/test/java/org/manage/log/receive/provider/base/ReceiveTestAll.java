package org.manage.log.receive.provider.base;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.manage.log.base.test.BaseTest;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/6 20:37
 */
@Suite
@SelectPackages({"org.manage.log.receive.provider"})
@ExcludePackages({"org.manage.log.receive.provider.base"})
public class ReceiveTestAll extends BaseTest {
}
