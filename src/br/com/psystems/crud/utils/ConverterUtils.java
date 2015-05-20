/**
 * 
 */
package br.com.psystems.crud.utils;

import org.apache.log4j.Logger;

import br.com.psystems.crud.exception.ConverterException;

/**
 * @author rafael.saldanha
 *
 */
public class ConverterUtils {
	
	private static Logger logger = Logger.getLogger(ConverterUtils.class);
	
	private static final String DOUBLE_CONVERT_ERROR_MSG = "Houve um erro ao fazer a conversão de valores do tipo double.";
	private static final String INTEGER_CONVERT_ERROR_MSG = "Houve um erro ao fazer a conversão de valores do tipo int.";

	public static Double convertStringToDouble(String value) throws ConverterException {
		try {
			if (null != value) {
				return Double.parseDouble(value.replace(",", "."));//FIXME Fazer uso do pacote Apache Commons Lang
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			logger.error(DOUBLE_CONVERT_ERROR_MSG, e);
			throw new ConverterException(DOUBLE_CONVERT_ERROR_MSG, e);
		}
	}
	
	public static Integer convertStringToInteger(String value) throws ConverterException {
		try {
			if (null != value) {
				return Integer.parseInt(value);//FIXME Fazer uso do pacote Apache Commons Lang
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			logger.error(INTEGER_CONVERT_ERROR_MSG, e);
			throw new ConverterException(INTEGER_CONVERT_ERROR_MSG, e);
		}
	}
}
