package com.art2app.server.utils;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.art2app.server.sql.MySqlSqlService;

public class DBHelper implements Closeable {
	private static Logger logger = LogManager.getLogger(DBHelper.class);
	
	private Connection con;
	private final List<PreparedStatement> statements = new ArrayList<>();

	public void init(MySqlSqlService mySqlSqlService) throws ClassNotFoundException, SQLException {
		final String url = mySqlSqlService.getJdbcMappingName();
		final String name = mySqlSqlService.getJdbcDriverName();
		final String user = mySqlSqlService.getUsername();
		final String password = mySqlSqlService.getPassword();
		con = null;
		Class.forName(name);
		con = DriverManager.getConnection(url, user, password);
	}

	public void prepareUpdate(String sql, String[] params) throws SQLException {
		if (con != null) {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setString(i + 1, params[i]);
			}
			statements.add(preparedStatement);
		}
	}

	/**
	 * Execute the prepared statements within a transaction.
	 * It transaction is rollbacked if one of the statement execution failed.
	 * @throws SQLException
	 */
	public void execute() throws SQLException {
		if (con != null) {
			try {
				con.setAutoCommit(false);
				for (PreparedStatement statement : statements) {
					statement.executeUpdate();
				}
				con.commit();
			} catch (SQLException e) {
				if (con != null) {
					try {
						logger.error("Transaction is being rolled back", e);
						con.rollback();
					} catch (SQLException excep) {
						logger.error("Transaction rolled back failed", e);
					}
				}
			} finally {
				closeStatements();
				con.setAutoCommit(true);
			}
		}
	}

	private void closeStatements() throws SQLException {
		for (PreparedStatement statement : statements) {
			statement.close();
		}
		statements.clear();
	}

	@Override	
	public void close() {
		try {
			closeStatements();
		} catch (SQLException e) {
			logger.throwing(e);
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				logger.throwing(e);
			}
		}
	}
}
