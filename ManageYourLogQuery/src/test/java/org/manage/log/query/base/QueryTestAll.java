package org.manage.log.query.base;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 16:12
 */
@Suite
@SelectPackages({"org.manage.log.query"})
@ExcludePackages({"org.manage.log.query.base"})
public class QueryTestAll extends BaseTest {
}
