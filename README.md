fastjson-jaxrs-json-provider
============================

a JAX-RS entity provider for [fastjson](https://github.com/alibaba/fastjson)

### Introduction

`FastJsonProvider` is a standard entity provider that follows JAX-RS 2.0 spec. 
According to different JAX-RS implementations such as CXF, Jersey, maybe you use `FastJsonProvider` in appropriate styles.
`FastJsonProvider` can serialize/deserialize specific types including:
* all types: `public FastJsonProvider()` (default constructor)
* all type annotated with `FastJsonType`: `public FastJsonProvider(boolean annotated)`
* all type annotated in specific packages : `public FastJsonProvider(String[] scanpackages)`
* all type annotated in specific packages with `FastJsonType`: `public FastJsonProvider(String[] scanpackages, boolean annotated)`
* all type  in specific classes: `public FastJsonProvider(Class<?>[] clazzes)`

You can `init` this provider instance with a FastJsonConfig object which is used to configure FastJson features, SerializeConfig, ParserConfig and SerializeFilter. Any parameters can be null and will be default.
 
#### Maven
*stable version: 0.2.0*

```
<dependency>
	<groupId>com.colobu</groupId>
	<artifactId>fastjson-jaxrs-json-provider</artifactId>
	<version>0.2.0</version>
<dependency>
```

*snapshot version: 0.2.1-SNAPSHOT*
```
<dependency>
	<groupId>com.colobu</groupId>
	<artifactId>fastjson-jaxrs-json-provider</artifactId>
	<version>0.2.1-SNAPSHOT</version>
<dependency>
```


### Jersey configuration
Please check the test and maybe you need to create a FastJsonFeature to override MessageBodyReader and MessageBodyWriter.

### CXF confguration
You can use JAXRSServerFactoryBean.setProviders to add a `FastJsonProvider` instance.