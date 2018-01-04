/**
 * 
 */
package br.com.psystems.crud.infra;

/**
 * @author developer
 *
 */
public enum EnviromentTypeEnum {
	PROD("prod.driver","prod.database","prod.host","prod.port","prod.url","prod.user","prod.password","prod.maxconnections"),
	HML("hml.driver","hml.database","hml.host","hml.port","html.url","html.user","html.password","html.maxconnections"),
	TEST("test.driver","test.database","test.host","test.port","test.url","test.user","test.password","test.maxconnections"),
	DEV("dev.driver","dev.database","dev.host","dev.port","dev.url","dev.user","dev.password","dev.maxconnections")
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
