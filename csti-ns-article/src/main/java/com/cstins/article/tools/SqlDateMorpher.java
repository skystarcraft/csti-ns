package com.cstins.article.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Locale;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;

/**
 * 本类是为了让JSONObject支持将日期字符串（如1997-6-1）转换为java.sql.Date（官方版本只支持java.util.Date）。<br>
 * 我们主要修改了原有ezmorph-1.0.6.jar包中，的“public Object morph(Object value)”方法。<br>
 * 让它在其中将解析字符串准备返回的util.Date转换为sql.Date。<br>
 * 另外，我们把本类中原有导入的“java.util.Date”修改为了“java.sql.Date”！
 */

public final class SqlDateMorpher extends AbstractObjectMorpher {
    private Date defaultValue;
    private String[] formats;
    private boolean lenient;
    private Locale locale;

    /**
     * @param formats
     *            a list of formats this morpher supports.
     */
    public SqlDateMorpher(String[] formats) {
        this(formats, Locale.getDefault(), false);
    }

    /**
     * @param formats
     *            a list of formats this morpher supports.
     * @param lenient
     *            if the parsing should be lenient or not.
     */
    public SqlDateMorpher(String[] formats, boolean lenient) {
        this(formats, Locale.getDefault(), lenient);
    }

    /**
     * @param formats
     *            a list of formats this morpher supports.
     * @param defaultValue
     *            return value if the value to be morphed is null.
     */
    public SqlDateMorpher(String[] formats, Date defaultValue) {
        this(formats, defaultValue, Locale.getDefault(), false);
    }

    /**
     * @param formats
     *            a list of formats this morpher supports.
     * @param defaultValue
     *            return value if the value to be morphed is null.
     * @param locale
     *            the Locale used to parse each format.
     * @param lenient
     *            if the parsing should be lenient or not.
     */
    public SqlDateMorpher(String[] formats, Date defaultValue, Locale locale, boolean lenient) {
        super(true);
        if (formats == null || formats.length == 0) {
            throw new MorphException("invalid array of formats");
        }
        // should use defensive copying ?
        this.formats = formats;

        if (locale == null) {
            this.locale = Locale.getDefault();
        } else {
            this.locale = locale;
        }

        this.lenient = lenient;
        setDefaultValue(defaultValue);
    }

    /**
     * @param formats
     *            a list of formats this morpher supports.
     * @param locale
     *            the Locale used to parse each format.
     */
    public SqlDateMorpher(String[] formats, Locale locale) {
        this(formats, locale, false);
    }

    /**
     * @param formats
     *            a list of formats this morpher supports.
     * @param locale
     *            the Locale used to parse each format.
     * @param lenient
     *            if the parsing should be lenient or not.
     */
    public SqlDateMorpher(String[] formats, Locale locale, boolean lenient) {
        if (formats == null || formats.length == 0) {
            throw new MorphException("invalid array of formats");
        }
        // should use defensive copying ?
        this.formats = formats;

        if (locale == null) {
            this.locale = Locale.getDefault();
        } else {
            this.locale = locale;
        }

        this.lenient = lenient;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof SqlDateMorpher)) {
            return false;
        }

        SqlDateMorpher other = (SqlDateMorpher) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(formats, other.formats);
        builder.append(locale, other.locale);
        builder.append(lenient, other.lenient);
        if (isUseDefault() && other.isUseDefault()) {
            builder.append(getDefaultValue(), other.getDefaultValue());
            return builder.isEquals();
        } else if (!isUseDefault() && !other.isUseDefault()) {
            return builder.isEquals();
        } else {
            return false;
        }
    }

    /**
     * Returns the default value for this Morpher.
     */
    public Date getDefaultValue() {
        return (Date) defaultValue.clone();
    }

    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(formats);
        builder.append(locale);
        builder.append(lenient);
        if (isUseDefault()) {
            builder.append(getDefaultValue());
        }
        return builder.toHashCode();
    }

    //我们主要修改了此方法，以支持sql.Date
    @Override
    public Object morph(Object value) {
        if (value == null) {
            return null;
        }

        //准确匹配要转换的类型为java.sql.Date
//		if (Date.class.isAssignableFrom(value.getClass()))
        if (Date.class == value.getClass())
        {
            return (Date) value;
        }

        if (!supports(value.getClass())) {
            throw new MorphException(value.getClass() + " is not supported");
        }

        String strValue = (String) value;
        SimpleDateFormat dateParser = null;

        for (int i = 0; i < formats.length; i++) {
            if (dateParser == null) {
                dateParser = new SimpleDateFormat(formats[i], locale);
            } else {
                dateParser.applyPattern(formats[i]);
            }
            dateParser.setLenient(lenient);
            try {
                //return new dateParser.parse(strValue.toLowerCase());//原有代码返回java.util.Date
                //我们在此将它包装为java.sql.Date
                return new Date(dateParser.parse(strValue.toLowerCase()).getTime());
            } catch (ParseException pe) {
                // ignore exception, try the next format
            }
        }

        // unable to parse the date
        if (isUseDefault()) {
            return defaultValue;
        } else {
            throw new MorphException("Unable to parse the date " + value);
        }
    }

    public Class morphsTo() {
        return Date.class;
    }

    /**
     * Sets the defaultValue to use if the value to be morphed is null.
     *
     * @param defaultValue
     *            return value if the value to be morphed is null
     */
    public void setDefaultValue(Date defaultValue) {
        this.defaultValue = (Date) defaultValue.clone();
    }

    public boolean supports(Class clazz) {
        return String.class.isAssignableFrom(clazz);
    }
}
