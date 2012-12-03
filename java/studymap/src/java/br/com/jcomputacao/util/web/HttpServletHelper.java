
package br.com.jcomputacao.util.web;

import br.com.jcomputacao.util.StringUtil;
import br.com.jcomputacao.util.TimeUtil;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * 24/11/2012 14:22:57
 * @author murilodemoraestuvani
 */
public abstract class HttpServletHelper extends HttpServlet {

    protected Date convertToDate(String value, HttpServletRequest request) throws ParseException {
        if (StringUtil.isNotNull(value)) {
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, request.getLocale());
            return df.parse(value);
        } else {
            return TimeUtil.getNow();
        }
    }

    protected Date convertToDateOrNull(String value, HttpServletRequest request) throws ParseException {
        if (StringUtil.isNotNull(value)) {
            return convertToDate(value, request);
        } else {
            return null;
        }
    }

    protected Integer convertToIntegerOrNull(String value, HttpServletRequest request) throws ParseException {
        if(StringUtil.isNotNull(value)) {
            return convertToInt(value, request);
        } else {
            return null;
        }
    }

    protected int convertToInt(String value, HttpServletRequest request) throws ParseException {
        if(StringUtil.isNotNull(value)) {
            NumberFormat nf = NumberFormat.getIntegerInstance(request.getLocale());
            return (Integer) nf.parse(value);
        } else {
            return 0;
        }
    }

    protected Double convertToDouble(String value, HttpServletRequest request) throws ParseException {
        if(StringUtil.isNotNull(value)) {
            NumberFormat nf = NumberFormat.getNumberInstance(request.getLocale());
            return (Double) nf.parse(value);
        } else {
            return 0d;
        }
        
    }
    
    protected Double convertToDoubleOrNull(String value, HttpServletRequest request) throws ParseException {
        if(StringUtil.isNotNull(value)) {
            return convertToDouble(value, request);
        } else {
            return null;
        }
    }
}
