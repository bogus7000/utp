package eu.glowacki.utp.assignment10.repositories;

import java.sql.Connection;
import java.sql.SQLException;

import eu.glowacki.utp.assignment10.dtos.DTOBase;

public interface IRepository<TDTO extends DTOBase> {

	Connection getConnection() throws SQLException;

	void add(TDTO dto);

	void update(TDTO dto);
	
	void addOrUpdate(TDTO dto);

	void delete(TDTO dto);

	TDTO findById(int id);

	void beginTransaction(Connection conn);

	void commitTransaction(Connection conn);

	void rollbackTransaction(Connection conn);
	
	int getCount();
	
	boolean exists(TDTO dto);
}