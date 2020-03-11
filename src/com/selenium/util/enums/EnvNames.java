package com.selenium.util.enums;

public enum EnvNames {

	QA("qa"), PROD("Prod"), STAGE("Stage"), DEMO("demo"),
	DEV("dev");

	private String envNames;

	public String toString() {
		return envNames;
	}

	EnvNames(String envNames) {
		this.envNames = envNames;
	}

}
