package org.manage.log.upload.base;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 16:12
 */
@Suite
@SelectPackages({"org.manage.log.upload", "org.manage.log.common"})
@ExcludePackages({"org.manage.log.upload.base"})
public class UploadTestAll extends BaseTest {
}
