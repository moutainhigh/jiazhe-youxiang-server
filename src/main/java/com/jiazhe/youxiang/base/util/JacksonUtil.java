package com.jiazhe.youxiang.base.util;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class JacksonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    private JacksonUtil() {
    }

    public static String toJSon(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception var2) {
            throw new RuntimeException("not able to convert object to json");
        }
    }

  

    public static <T> List<T> readValue2List(String jsonStr, final Class<T> tClass) {
        try {
            TypeReference<List<T>> typeRef = new TypeReference<List<T>>() {
                public Type getType() {
                    return ParameterizedTypeImpl.make(List.class, new Type[]{tClass}, (Type)null);
                }
            };
            return (List)mapper.readValue(jsonStr, typeRef);
        } catch (Exception var3) {
            throw new RuntimeException("not able to convert json string:" + jsonStr);
        }
    }

    public static <K, V> Map<K, V> readValue2Map(String jsonStr, final Class<K> keyClass, final Class<V> valueClass) {
        try {
            TypeReference<Map<K, V>> typeRef = new TypeReference<Map<K, V>>() {
                public Type getType() {
                    return ParameterizedTypeImpl.make(Map.class, new Type[]{keyClass, valueClass}, (Type)null);
                }
            };
            return (Map)mapper.readValue(jsonStr, typeRef);
        } catch (Exception var4) {
            throw new RuntimeException("not able to convert json string:" + jsonStr);
        }
    }

    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception var3) {
            throw new RuntimeException("not able to convert json string:" + jsonStr);
        }
    }

    public static <T> T readValue(String jsonStr, Class<T> tClass) {
        try {
            return mapper.readValue(jsonStr, tClass);
        } catch (Exception var3) {
            throw new RuntimeException("not able to convert json string:" + jsonStr);
        }
    }

    static {
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        mapper.configure(MapperFeature.AUTO_DETECT_FIELDS, false);
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        private final Type[] actualTypeArguments;
        private final Class<?> rawType;
        private final Type ownerType;

        private ParameterizedTypeImpl(Class<?> var1, Type[] var2, Type var3) {
            this.actualTypeArguments = var2;
            this.rawType = var1;
            this.ownerType = (Type)(var3 != null ? var3 : var1.getDeclaringClass());
            this.validateConstructorArguments();
        }

        private void validateConstructorArguments() {
            TypeVariable[] var1 = this.rawType.getTypeParameters();
            if (var1.length != this.actualTypeArguments.length) {
                throw new MalformedParameterizedTypeException();
            }
        }

        public static ParameterizedTypeImpl make(Class<?> var0, Type[] var1, Type var2) {
            return new ParameterizedTypeImpl(var0, var1, var2);
        }

        public Type[] getActualTypeArguments() {
            return (Type[])((Type[])this.actualTypeArguments.clone());
        }

        public Class<?> getRawType() {
            return this.rawType;
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        public boolean equals(Object var1) {
            if (var1 instanceof ParameterizedType) {
                ParameterizedType var2 = (ParameterizedType)var1;
                if (this == var2) {
                    return true;
                } else {
                    Type var3 = var2.getOwnerType();
                    Type var4 = var2.getRawType();
                    return Objects.equals(this.ownerType, var3) && Objects.equals(this.rawType, var4) && Arrays.equals(this.actualTypeArguments, var2.getActualTypeArguments());
                }
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Arrays.hashCode(this.actualTypeArguments) ^ Objects.hashCode(this.ownerType) ^ Objects.hashCode(this.rawType);
        }

        public String toString() {
            StringBuilder var1 = new StringBuilder();
            if (this.ownerType != null) {
                if (this.ownerType instanceof Class) {
                    var1.append(((Class)this.ownerType).getName());
                } else {
                    var1.append(this.ownerType.toString());
                }

                var1.append(".");
                if (this.ownerType instanceof ParameterizedTypeImpl) {
                    var1.append(this.rawType.getName().replace(((ParameterizedTypeImpl)this.ownerType).rawType.getName() + "$", ""));
                } else {
                    var1.append(this.rawType.getName());
                }
            } else {
                var1.append(this.rawType.getName());
            }

            if (this.actualTypeArguments != null && this.actualTypeArguments.length > 0) {
                var1.append("<");
                boolean var2 = true;
                Type[] var3 = this.actualTypeArguments;
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    Type var6 = var3[var5];
                    if (!var2) {
                        var1.append(", ");
                    }

                    var1.append(var6.getTypeName());
                    var2 = false;
                }

                var1.append(">");
            }

            return var1.toString();
        }
    }
}
