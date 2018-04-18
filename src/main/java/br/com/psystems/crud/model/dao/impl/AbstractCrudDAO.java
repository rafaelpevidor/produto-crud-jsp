package br.com.psystems.crud.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.infra.ConnectionManager;
import br.com.psystems.crud.model.BaseEntity;

public abstract class AbstractCrudDAO extends AbstractBaseDAO {

	public AbstractCrudDAO(ConnectionManager connectionManager) throws DAOException {
		super(connectionManager);
	}
	public AbstractCrudDAO() throws DAOException {
		super();
	}
	protected abstract List<? extends BaseEntity> getEntityList(final ResultSet rs) throws SQLException;
	protected abstract BaseEntity getEntity(final ResultSet rs) throws SQLException, DAOException;
	protected abstract BaseEntity createEntity(final ResultSet rs) throws SQLException;
}