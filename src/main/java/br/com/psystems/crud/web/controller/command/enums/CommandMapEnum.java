/**
 * 
 */
package br.com.psystems.crud.web.controller.command.enums;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.model.dao.impl.ProductDAO;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAO;
import br.com.psystems.crud.model.dao.impl.UserDAO;
import br.com.psystems.crud.model.dao.impl.VendorDAO;
import br.com.psystems.crud.service.impl.ProductServiceImpl;
import br.com.psystems.crud.service.impl.UnitMeasurementServiceImpl;
import br.com.psystems.crud.service.impl.UserServiceImpl;
import br.com.psystems.crud.service.impl.VendorServiceImpl;
import br.com.psystems.crud.web.controller.Controllable;
import br.com.psystems.crud.web.controller.command.AddProductCommand;
import br.com.psystems.crud.web.controller.command.AddUnitMeasurementCommand;
import br.com.psystems.crud.web.controller.command.AddUserCommand;
import br.com.psystems.crud.web.controller.command.AddVendorCommand;
import br.com.psystems.crud.web.controller.command.DeleteProductCommand;
import br.com.psystems.crud.web.controller.command.DeleteUnitMeasutementCommand;
import br.com.psystems.crud.web.controller.command.DeleteUserCommand;
import br.com.psystems.crud.web.controller.command.DeleteVendorCommand;
import br.com.psystems.crud.web.controller.command.FindProductCommand;
import br.com.psystems.crud.web.controller.command.FindUnitMeasurementCommand;
import br.com.psystems.crud.web.controller.command.FindUserCommand;
import br.com.psystems.crud.web.controller.command.FindVendorCommand;
import br.com.psystems.crud.web.controller.command.ListProductCommand;
import br.com.psystems.crud.web.controller.command.ListUnitMeasurementCommand;
import br.com.psystems.crud.web.controller.command.ListUserCommand;
import br.com.psystems.crud.web.controller.command.ListVendorCommand;
import br.com.psystems.crud.web.controller.command.SearchProductCommand;
import br.com.psystems.crud.web.controller.command.SearchUnitMeasurementCommand;
import br.com.psystems.crud.web.controller.command.SearchUserCommand;
import br.com.psystems.crud.web.controller.command.SearchVendorCommand;
import br.com.psystems.crud.web.controller.command.UpdateProductCommand;
import br.com.psystems.crud.web.controller.command.UpdateUnitMeasurementCommand;
import br.com.psystems.crud.web.controller.command.UpdateUserCommand;
import br.com.psystems.crud.web.controller.command.UpdateVendorCommand;

/**
 * @author developer
 *
 */
public enum CommandMapEnum {

	ADD_VENDOR("1","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new AddVendorCommand(new VendorServiceImpl(new VendorDAO()));
		}
	},
	RECOVER_VENDOR("2","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new FindVendorCommand(new VendorServiceImpl(new VendorDAO()));
		}
	},
	UPDATE_VENDOR("3","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new UpdateVendorCommand(new VendorServiceImpl(new VendorDAO()));
		}
	},
	DELETE_VENDOR("4","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new DeleteVendorCommand(new VendorServiceImpl(new VendorDAO()));
		}
	},
	SEARCH_VENDOR("5","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new SearchVendorCommand(new VendorServiceImpl(new VendorDAO()));
		}
	},
	LIST_VENDOR("6","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new ListVendorCommand(new VendorServiceImpl(new VendorDAO()));
		}
	},
	
	ADD_PRODUCT("7","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new AddProductCommand(new ProductServiceImpl(new ProductDAO()));
		}
	},
	RECOVER_PRODUCT("8","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new FindProductCommand(new ProductServiceImpl(new ProductDAO()));
		}
	},
	UPDATE_PRODUCT("9","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new UpdateProductCommand(new ProductServiceImpl(new ProductDAO()));
		}
	},
	DELETE_PRODUCT("10","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new DeleteProductCommand(new ProductServiceImpl(new ProductDAO()));
		}
	},
	SEARCH_PRODUCT("11","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new SearchProductCommand(new ProductServiceImpl(new ProductDAO()));
		}
	},
	LIST_PRODUCT("12","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new ListProductCommand(new ProductServiceImpl(new ProductDAO()));
		}
	},
	
	ADD_USER("13","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new AddUserCommand(new UserServiceImpl(new UserDAO()));
		}
	},
	RECOVER_USER("14","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new FindUserCommand(new UserServiceImpl(new UserDAO()));
		}
	},
	UPDATE_USER("15","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new UpdateUserCommand(new UserServiceImpl(new UserDAO()));
		}
	},
	DELETE_USER("16","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new DeleteUserCommand(new UserServiceImpl(new UserDAO()));
		}
	},
	SEARCH_USER("17","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new SearchUserCommand(new UserServiceImpl(new UserDAO()));
		}
	},
	LIST_USER("18","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new ListUserCommand(new UserServiceImpl(new UserDAO()));
		}
	},
	
	ADD_UNIT("19","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new AddUnitMeasurementCommand(new UnitMeasurementServiceImpl(new UnitMeasurementDAO()));
		}
	},
	RECOVER_UNIT("20","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new FindUnitMeasurementCommand(new UnitMeasurementServiceImpl(new UnitMeasurementDAO()));
		}
	},
	UPDATE_UNIT("21","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new UpdateUnitMeasurementCommand(new UnitMeasurementServiceImpl(new UnitMeasurementDAO()));
		}
	},
	DELETE_UNIT("22","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new DeleteUnitMeasutementCommand(new UnitMeasurementServiceImpl(new UnitMeasurementDAO()));
		}
	},
	SEARCH_UNIT("23","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new SearchUnitMeasurementCommand(new UnitMeasurementServiceImpl(new UnitMeasurementDAO()));
		}
	},
	LIST_UNIT("24","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new ListUnitMeasurementCommand(new UnitMeasurementServiceImpl(new UnitMeasurementDAO()));
		}
	}
	;
	
	private CommandMapEnum(String id, String key) {
		this.id = id;
		this.key = key;
	}

	private String id;
	private String key;
	
	public String getId() {
		return id;
	}

	public String getKey() {
		return key;
	}
	
	public static CommandMapEnum getById(String id) {
		for (CommandMapEnum cmd : CommandMapEnum.values()) {
			if (id.equals(cmd.id))
				return cmd;
		}
		return null;
	}
	
	public static CommandMapEnum getByKey(String key) {
		for (CommandMapEnum cmd : CommandMapEnum.values()) {
			if (key.equals(cmd.key))
				return cmd;
		}
		return null;
	}
	
	public abstract Controllable getInstance() throws DAOException;
}
