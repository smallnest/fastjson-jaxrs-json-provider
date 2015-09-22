fastjson-jaxrs-json-provider
============================

A JAX-RS entity provider for [fastjson](https://github.com/alibaba/fastjson)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.colobu/fastjson-jaxrs-json-provider/badge.svg)](https://github.com/smallnest/fastjson-jaxrs-json-provider)


### What is JAXRS provider?
Entity payload, if present in an received HTTP message, is passed to JAX-RS container as an input stream. The stream may, for example, contain data represented as a plain text, XML or JSON document. 
However, in many JAX-RS components that process these inbound data, such as resource methods or client responses, the JAX-RS API user can access the inbound entity as an arbitrary Java object that is created from the content of the input stream based on the representation type information. 
For example, an entity created from an input stream that contains data represented as a XML document, can be converted to a custom JAXB bean. 
Similar concept is supported for the outbound entities. An entity returned from the resource method in the form of an arbitrary Java object can be serialized by JAX-RS container into a container output stream as a specified representation. 
Of course, while JAX-RS implementations do provide default support for most common combinations of Java type and it's respective on-the-wire representation formats, 
JAX-RS implementations do not support the conversion described above for any arbitrary Java type and any arbitrary representation format by default. 
Instead, a generic extension concept is exposed in JAX-RS API to allow application-level customizations of this JAX-RS runtime to support for entity conversions. 
The JAX-RS extension API components that provide the user-level extensibility are typically referred to by several terms with the same meaning, such as entity providers, message body providers, message body workers or message body readers and writers. 
You may find all these terms used interchangeably throughout the user guide and they all refer to the same concept. 

In JAX-RS extension API (or SPI - service provider interface, if you like) the concept is captured in 2 interfaces. 
One for handling inbound entity representation-to-Java de-serialization - MessageBodyReader<T> and the other one for handling the outbound entity Java-to-representation serialization - MessageBodyWriter<T>. 
A MessageBodyReader<T>, as the name suggests, is an extension that supports reading the message body representation from an input stream and converting the data into an instance of a specific Java type. 
A MessageBodyWriter<T> is then responsible for converting a message payload from an instance of a specific Java type into a specific representation format that is sent over the wire to the other party as part of an HTTP message exchange. 
Both of these providers can be used to provide message payload serialization and de-serialization support on the server as well as the client side. 
A message body reader or writer is always used whenever a HTTP request or response contains an entity and the entity is either requested by the application code (e.g. injected as a parameter of JAX-RS resource method or a response entity read on the client from a Response) or has to be serialized and sent to the other party (e.g. an instance returned from a JAX-RS resource method or a request entity sent by a JAX-RS client). 

please read <https://jersey.java.net/documentation/latest/message-body-workers.html> to learn how providers work.

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
*stable version: 0.3.0*

```
<dependency>
	<groupId>com.colobu</groupId>
	<artifactId>fastjson-jaxrs-json-provider</artifactId>
	<version>0.3.0</version>
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