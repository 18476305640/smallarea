package com.zjazn.common.baseUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FetchColumn<T> {
    public T[] by(List<Object> obj_arr, String column) {
        List<T> ts = new ArrayList<>();
        for (int i = 0; i < obj_arr.size(); i++) {
            Map<String, Object> map = FetchColumn.convertBeanToMap(obj_arr.get(i));
            T t = (T) map.get(column);
            ts.add(t);
        }
        return (T[]) ts.toArray();
    }


    public static Map<String, Object> convertBeanToMap(Object object) {
        if (object == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (!key.equals("class")) {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(object);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;

    }

}
