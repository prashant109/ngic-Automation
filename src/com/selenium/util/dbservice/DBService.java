package com.selenium.util.dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.selenium.util.CommonUtil;
import com.selenium.util.KEY;

public class DBService {

	protected String driver;
	protected String DB_URL;
	protected Properties props = null;
	protected Connection conn = null;
	protected Statement stmt;
	protected ResultSet rs = null;

	CommonUtil common;

	public enum SqlServer {
		PATOLUS, PLATFORM, PLATFORM_NEW, PAYMENTS_DB_URL, STAGE_SANDBOX
	}

	public DBService(CommonUtil common) throws Exception {
		this.common = common;
		driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		DB_URL = CommonUtil.CONFIG.getProperty(KEY.PLATFORM_NEW_DB_URL);
		// Properties for creating connection to database
		props = new Properties();
		// STEP 1: Register JDBC driver
		Class.forName(driver);
		// Step 2: Get Connection to DB
		common.log("Connecting to a selected Database");
		conn = DriverManager.getConnection(DB_URL, props);
		common.log("Connected to database successfully");
		stmt = conn.createStatement();
	}

	public DBService(CommonUtil common, SqlServer server) throws Exception {
		this.common = common;
		driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		if (server == SqlServer.PLATFORM) {
			DB_URL = CommonUtil.CONFIG.getProperty(KEY.PLATFORM_DB_URL);
		} else if (server == SqlServer.PLATFORM_NEW) {
			DB_URL = CommonUtil.CONFIG.getProperty(KEY.PLATFORM_NEW_DB_URL);
			common.log("DB_URL : " + DB_URL);
		} else if (server == SqlServer.PAYMENTS_DB_URL) {
			DB_URL = CommonUtil.CONFIG.getProperty(KEY.PAYMENTS_DB_URL);
			common.log("DB_URL : " + DB_URL);
		} else if (server == SqlServer.STAGE_SANDBOX) {
			DB_URL = CommonUtil.CONFIG.getProperty(KEY.STAGE_SANDBOX_DB);
			common.log("DB_URL : " + DB_URL);

		} else {
			DB_URL = CommonUtil.CONFIG.getProperty(KEY.DATABASE_URL);
		}
		// Properties for creating connection to database
		props = new Properties();
		// STEP 1: Register JDBC driver
		Class.forName(driver);
		// Step 2: Get Connection to DB
		common.log("Connecting to a selected Database");
		conn = DriverManager.getConnection(DB_URL, props);
		common.log("Connected to database successfully");
		stmt = conn.createStatement();
	}

	public ResultSet executeSelectQuery(String query) throws Exception {
		rs = stmt.executeQuery(query);
		return rs;
	}

	public void verifyTableNotEmpty(String tableName) throws Exception {
		common.log("Verify table shouldn't be empty");
		for (int i = 0; i < 10; i++) {
			rs = stmt.executeQuery("select * from " + tableName);
			if (rs != null) {
				common.log("Table is not empty. Moving ahead !!!");
				break;
			}
			common.log("Table : " + tableName + " is empty. Waiting for data to display in table");
			Thread.sleep(3000);
		}
	}

	public void executeUpdateQuery(String query) throws Exception {
		common.log("query : " + query);
		stmt.executeUpdate(query);
	}

	public void getColumnNames(String query) throws SQLException {
		// SELECT * FROM [dbo].[Associates]
		rs = stmt.executeQuery(query);
		if (rs == null) {
			return;
		}
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int numberOfColumns = rsMetaData.getColumnCount();

		// get the column names; column indexes start from 1
		for (int i = 1; i < numberOfColumns + 1; i++) {
			String columnName = rsMetaData.getColumnName(i);
			// Get the name of the column's table name
			String tableName = rsMetaData.getTableName(i);
			common.log("column name=" + columnName + " table=" + tableName + "");
		}
	}

	public int getRowCount(ResultSet resultSet) {
		int size = 0;
		try {
			while (resultSet.next()) {
				size++;
			}
		} catch (Exception ex) {
			common.log("------------------Tablerize.getRowCount-----------------");
			common.log("Cannot get resultSet row count: " + ex);
			common.log("--------------------------------------------------------");
		}
		return size;
	}

}
