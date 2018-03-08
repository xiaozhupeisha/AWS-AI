package com.nuanyou.rekognition.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用jdk自带内省操作Bean
 */
public class BeanUtils {

    private static final Logger _LOGGER = LoggerFactory.getLogger(BeanUtils.class.getSimpleName());

    private static volatile Map<Class<?>, BeanInfo> beanInfoCache = new HashMap<>(32);

    private static final List<String> ignores = new ArrayList<>();

    static {
        ignores.add("class");
        ignores.add("hibernateLazyInitializer");
        ignores.add("handler");
        ignores.add("fieldHandler");
    }

    public static <T> T copyBean(Object source, T target) {
        return copyBean(source, target, null);
    }

    public static <T> T copyBean(Object source, T target, String ignore) {
        Map<String, Object> map = toMap(source, ignore);
        return copyBean(map, target);
    }

    public static <T> T copyBeanNotNull(Object source, T target) {
        Map<String, Object> map = toMapNotNull(source);
        return copyBeanNotNull(map, target);
    }

    public static <T> T copyBean(Map<String, Object> source, T target) {
        BeanInfo beanInfo = getBeanInfo(target.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName();
            if (ignores.contains(name) || !source.containsKey(name))
                continue;
            Object value = source.get(name);
            setValue(target, pd.getWriteMethod(), value);
        }
        return target;
    }

    public static <T> T copyBeanNotNull(Map<String, Object> source, T target) {
        BeanInfo beanInfo = getBeanInfo(target.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (ignores.contains(pd.getName())) {
                continue;
            }
            Object value = source.get(pd.getName());
            if (value != null) {
                setValue(target, pd.getWriteMethod(), value);
            }
        }
        return target;
    }

    public static void cleanEmpty(Object obj) {
        BeanInfo beanInfo = getBeanInfo(obj.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (ignores.contains(pd.getName()))
                continue;
            Object value = getValue(obj, pd.getReadMethod());
            if ("".equals(value)) {
                setValue(obj, pd.getWriteMethod(), null);
            }
        }
    }

    public static Map<String, Object> toMap(Object obj, String ignore) {
        if (obj == null)
            return null;
        Map<String, Object> result = new HashMap<>();

        BeanInfo beanInfo = getBeanInfo(obj.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName();
            if (ignores.contains(name))
                continue;
            if (ignore != null && ignore.contains(name))
                continue;

            Object value = getValue(obj, pd.getReadMethod());
            result.put(name, value);
        }
        return result;
    }

    public static Map<String, Object> toMapNotNull(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> result = new HashMap<>();
        BeanInfo beanInfo = getBeanInfo(obj.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName();
            if (ignores.contains(name)) {
                continue;
            }
            Object value = getValue(obj, pd.getReadMethod());
            if (value != null) {
                result.put(name, value);
            }
        }
        return result;
    }

    public static Object getValue(Object obj, String name) {
        if (obj == null) {
            return null;
        }
        BeanInfo beanInfo = getBeanInfo(obj.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (!name.equals(pd.getName())) {
                continue;
            }
            return getValue(obj, pd.getReadMethod());
        }
        return null;
    }

    public static Object getValue(Object obj, Method getter) {
        if (getter != null)
            try {
                return getter.invoke(obj);
            } catch (Exception e) {
                _LOGGER.error("Failed to get object values!", e);
            }
        return null;
    }

    public static void setValue(Object obj, String name, Object value) {
        if (obj == null) {
            return;
        }
        BeanInfo beanInfo = getBeanInfo(obj.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (!name.equals(pd.getName())) {
                continue;
            }
            setValue(obj, pd.getWriteMethod(), value);
            break;
        }
    }

    public static void setValue(Object obj, Method setter, Object value) {

        if (setter == null) {
            return;
        }
        if (value == null || setter.getParameterTypes()[0].isAssignableFrom(value.getClass())) {
            try {
                setter.invoke(obj, value);
            } catch (Exception e) {
                _LOGGER.error("Failed to get object values!", e);
            }
        }
    }

    public static BeanInfo getBeanInfo(Class<?> key) {
        BeanInfo beanInfo = beanInfoCache.get(key);
        if (beanInfo == null) {
            synchronized (beanInfoCache) {
                beanInfo = beanInfoCache.get(key);
                if (beanInfo == null) {
                    try {
                        beanInfo = Introspector.getBeanInfo(key);
                    } catch (IntrospectionException e) {
                        _LOGGER.error("Failed to get Class info!", e);
                        return null;
                    }
                    beanInfoCache.put(key, beanInfo);
                    Introspector.flushCaches();
                }
            }
        }
        return beanInfo;
    }
}