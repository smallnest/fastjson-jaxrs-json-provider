package com.colobu.fastjson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * Annotate a class that can be serialized/deserialized if FastJsonProvider 
 * is configured with Annotation scan.
 * @author smallnest
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface FastJsonType {

}
