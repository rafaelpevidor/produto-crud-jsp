/**
 * 
 */
package br.com.psystems.crud.infra;

/**
 * @author developer
 *
 */
public enum EnviromentTypeEnum {
	PROD("prod.db.driver","proprod.db.database","prod.db.host","prod.db.port","prod.db.url","prod.db.user","prod.db.password","prod.maxconnections"),
	HML("hml.db.db.driver","hml.db.database","hml.db.host","hml.db.port","hml.db.url","hml.db.user","hml.db.password","hml.db.maxconnections"),
	TEST("test.db.driver","test.db.database","test.db.host","test.db.port","test.db.url","test.db.user","test.db.password","test.db.maxconnections"),
	DEV("dev.db.driver","dev.db.database","dev.db.host","dev.db.port","dev.db.url","dev.db.user","dev.db.password","dev.db.maxconnections")
	;

	private EnviromentTypeEnum(String driver, String database, String host, String port, String url, String user, String password, String maxConnections) {
		this.driver = driver;
		this.database = database;
		this.url = url;
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.maxConnections = maxConnections;
	}

	private String driver;
	private String database;
	private String url;
	private String host;
	private String port;
	private String user;
	private String password;
	private String maxConnections;
	
	protected String getDriver() {
		return driver;
	}
	protected void setDriver(String driver) {
		this.driver = driver;
	}
	protected String getDatabase() {
		return database;
	}
	protected void setDatabase(String database) {
		this.database = database;
	}
	protected String getUrl() {
		return url;
	}
	protected void setUrl(String url) {
		this.url = url;
	}
	protected String getHost() {
		return host;
	}
	protected void setHost(String host) {
		this.host = host;
	}
	protected String getPort() {
		return port;
	}
	protected void setPort(String port) {
		this.port = port;
	}
	protected String getUser() {
		return user;
	}
	protected void setUser(String user) {
		this.user = user;
	}
	protected String getPassword() {
		return password;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	protected String getMaxConnections() {
		return maxConnections;
	}
	protected void setMaxConnections(String maxConnections) {
		this.maxConnections = maxConnections;
	}
}
