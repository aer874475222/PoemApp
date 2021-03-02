package com.luis.poem.bean;

import org.greenrobot.greendao.converter.PropertyConverter;

public class OidConverter implements PropertyConverter<IDbean, String> {
    @Override
    public IDbean convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        IDbean iDbean =  new IDbean();
        iDbean.set$oid(databaseValue);
        return iDbean;
    }

    @Override
    public String convertToDatabaseValue(IDbean arrays) {
        if (arrays == null) {
            return null;
        } else {
            return  arrays.get$oid();

        }
    }
}