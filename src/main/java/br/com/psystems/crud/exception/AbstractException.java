/**
 * 
 */
package br.com.psystems.crud.exception;

/**
 * @author developer
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractException extends Exception {

	public AbstractException() {
		super();
	}

	public AbstractException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractException(String message) {
		super(message);
	}

	public AbstractException(Throwable cause) {
		super(cause);
	}
	
	//field
	
	public AbstractException(Field field) {
		super();
	}

	public AbstractException(String message, Throwable cause, Field field) {
		super(message, cause);
	}

	public AbstractException(String message, Field field) {
		super(message);
	}

	public AbstractException(Throwable cause, Field field) {
		super(cause);
	}

	private Field field;
	
	public Field getField() {
		return field;
	}

	protected void setField(Field field) {
		this.field = field;
	}
	
	public class Field {
		public Field(String fieldName, String cssClass) {
			super();
			this.fieldName = fieldName;
			this.cssClass = cssClass;
		}

		private String fieldName;
		private String cssClass;
		
		public String getFieldName() {
			return fieldName;
		}
		
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		
		public String getCssClass() {
			return cssClass;
		}
		
		public void setCssClass(String cssClass) {
			this.cssClass = cssClass;
		}
	}
	
}
