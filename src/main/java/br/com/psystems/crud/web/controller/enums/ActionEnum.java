/**
 * 
 */
package br.com.psystems.crud.web.controller.enums;

import br.com.psystems.crud.exception.DAOException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.model.dao.impl.DashboardDAOImpl;
import br.com.psystems.crud.model.dao.impl.ProductDAOImpl;
import br.com.psystems.crud.model.dao.impl.UnitMeasurementDAOImpl;
import br.com.psystems.crud.model.dao.impl.VendorDAOImpl;
import br.com.psystems.crud.service.impl.DashboardServiceImpl;
import br.com.psystems.crud.service.impl.ProductServiceImpl;
import br.com.psystems.crud.service.impl.UnitMeasurementServiceImpl;
import br.com.psystems.crud.service.impl.VendorServiceImpl;
import br.com.psystems.crud.web.controller.Controllable;
import br.com.psystems.crud.web.controller.action.AddProductAction;
import br.com.psystems.crud.web.controller.action.AddUnitMeasurementAction;
import br.com.psystems.crud.web.controller.action.AddVendorAction;
import br.com.psystems.crud.web.controller.action.DashboardAction;
import br.com.psystems.crud.web.controller.action.DeleteProductAction;
import br.com.psystems.crud.web.controller.action.DeleteUnitMeasutementAction;
import br.com.psystems.crud.web.controller.action.DeleteVendorAction;
import br.com.psystems.crud.web.controller.action.FindProductAction;
import br.com.psystems.crud.web.controller.action.FindUnitMeasurementAction;
import br.com.psystems.crud.web.controller.action.FindVendorAction;
import br.com.psystems.crud.web.controller.action.ListProductAction;
import br.com.psystems.crud.web.controller.action.ListUnitMeasurementAction;
import br.com.psystems.crud.web.controller.action.ListVendorAction;
import br.com.psystems.crud.web.controller.action.LoginAction;
import br.com.psystems.crud.web.controller.action.SearchProductAction;
import br.com.psystems.crud.web.controller.action.SearchUnitMeasurementAction;
import br.com.psystems.crud.web.controller.action.SearchVendorAction;
import br.com.psystems.crud.web.controller.action.UpdateProductAction;
import br.com.psystems.crud.web.controller.action.UpdateUnitMeasurementAction;
import br.com.psystems.crud.web.controller.action.UpdateVendorAction;

/**
 * @author developer
 *
 */
public enum ActionEnum {
	
	LOGIN("0","/login"){
		@Override
		public Controllable getInstance() throws DAOException {
			return new LoginAction();
		}
	},
	DASHBOARD("0","/app/dashboard"){
		@Override
		public Controllable getInstance() throws DAOException {
			return new DashboardAction(new DashboardServiceImpl(new DashboardDAOImpl()));
		}
	},
	ADD_VENDOR("1","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new AddVendorAction(new VendorServiceImpl(new VendorDAOImpl()));
		}
	},
	RECOVER_VENDOR("2","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new FindVendorAction(new VendorServiceImpl(new VendorDAOImpl()));
		}
	},
	UPDATE_VENDOR("3","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new UpdateVendorAction(new VendorServiceImpl(new VendorDAOImpl()));
		}
	},
	DELETE_VENDOR("4","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new DeleteVendorAction(new VendorServiceImpl(new VendorDAOImpl()));
		}
	},
	SEARCH_VENDOR("5","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new SearchVendorAction(new VendorServiceImpl(new VendorDAOImpl()));
		}
	},
	LIST_VENDOR("6","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new ListVendorAction(new VendorServiceImpl(new VendorDAOImpl()));
		}
	},
	
	PRODUCT_CREATE("7","/app/product/create") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new AddProductAction(new ProductServiceImpl(new ProductDAOImpl()));
		}
	},
	PRODUCT_READ("8","/app/product/read") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new FindProductAction(new ProductServiceImpl(new ProductDAOImpl()));
		}
	},
	PRODUCT_UPDATE("9","/app/product/update") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new UpdateProductAction(new ProductServiceImpl(new ProductDAOImpl()));
		}
	},
	PRODUCT_DELETE("10","/app/product/delete") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new DeleteProductAction(new ProductServiceImpl(new ProductDAOImpl()));
		}
	},
	PRODUCT_ADD("12","/app/product/add", Constants.PAGE_PRODUCT_FORM) {
		@Override
		public Controllable getInstance() throws DAOException {
			return null;
		}
	},
	PRODUCT_EDIT("12","/app/product/edit", "/app/product/read") {
		@Override
		public Controllable getInstance() throws DAOException {
			return null;
		}
	},
	PRODUCT_LIST("12","/app/product/list", Constants.PAGE_PRODUCT_LIST) {
		@Override
		public Controllable getInstance() throws DAOException {
			return new ListProductAction(new ProductServiceImpl(new ProductDAOImpl()));
		}
	},
	PRODUCT_SEARCH("11","/app/product/search") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new SearchProductAction(new ProductServiceImpl(new ProductDAOImpl()));
		}
	},
	
	ADD_UNIT("19","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new AddUnitMeasurementAction(new UnitMeasurementServiceImpl(new UnitMeasurementDAOImpl()));
		}
	},
	RECOVER_UNIT("20","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new FindUnitMeasurementAction(new UnitMeasurementServiceImpl(new UnitMeasurementDAOImpl()));
		}
	},
	UPDATE_UNIT("21","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new UpdateUnitMeasurementAction(new UnitMeasurementServiceImpl(new UnitMeasurementDAOImpl()));
		}
	},
	DELETE_UNIT("22","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new DeleteUnitMeasutementAction(new UnitMeasurementServiceImpl(new UnitMeasurementDAOImpl()));
		}
	},
	SEARCH_UNIT("23","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new SearchUnitMeasurementAction(new UnitMeasurementServiceImpl(new UnitMeasurementDAOImpl()));
		}
	},
	LIST_UNIT("24","") {
		@Override
		public Controllable getInstance() throws DAOException {
			return new ListUnitMeasurementAction(new UnitMeasurementServiceImpl(new UnitMeasurementDAOImpl()));
		}
	}
	;
	
	private ActionEnum(String id, String path) {
		this.id = id;
		this.path = path;
	}
	
	private ActionEnum(String id, String path, String page) {
		this.id = id;
		this.path = path;
		this.page = page;
	}

	private String id;
	private String path;
	private String page;
	
	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}
	
	public String getPage() {
		return page;
	}

	public static ActionEnum getById(String id) {
		for (ActionEnum cmd : ActionEnum.values()) {
			if (id.equals(cmd.id))
				return cmd;
		}
		return null;
	}
	
	public static ActionEnum getByPath(String path) {
		for (ActionEnum cmd : ActionEnum.values()) {
			if (path.equals(cmd.path))
				return cmd;
		}
		return null;
	}
	
	public abstract Controllable getInstance() throws DAOException;
}
