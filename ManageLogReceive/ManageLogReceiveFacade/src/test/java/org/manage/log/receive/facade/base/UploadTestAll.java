package org.manage.log.receive.facade.base;

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
@SelectPackages({"org.manage.log.receive.facade"})
@ExcludePackages({"org.manage.log.receive.facade.base"})
public class UploadTestAll extends BaseTest {
}
