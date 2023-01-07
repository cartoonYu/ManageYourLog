package org.manage.log.query.provider.base;

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
@SelectPackages({"org.manage.log.query.provider"})
@ExcludePackages({"org.manage.log.query.base.provider"})
public class QueryTestAll extends BaseTest {
}
