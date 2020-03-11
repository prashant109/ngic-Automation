package com.selenium.web.page.common;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class NotificationPreferencePage {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public NotificationPreferencePage() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	public static String NOTIFICATION_INFO = "Notifications are for changes or updates to the status of your Treatment Summary. Patolus will never send you marketing or advertising messages.";
}
