Jackson Example for Wildfly
==========================================

## The Purpose of this Project

Demonstrate the usage of a Jackson annotation and add support fot JSR310 and Joda DateTime data types for Camunda Platform applications deployed to a specific versions of the Camunda Platform for Wildfly distribution.

Wildfly adds implicitly to each new deployment, which uses JAX-RS annotations, the JAX-RS subsystem that includes Jackson dependencies (see [here][WFLY-23]).
The version of the implicitly added Jackson dependencies do not match with the version of the Jackson dependency,
which is used by Camunda Spin. This leads to problems with the usage of variable serialization and the usage of Jackson annotations (like `@JsonIgnore` for example).

This example demonstrates how to configure the  application together with Wildfly to use Jackson annotations, DateTime data types,
and json serialization.

## How to run it

1. Checkout the git repository.
2. Build the project with maven.
3. Deploy the created war on the Camunda Platform 7.15.8-ee for Wildfly distribution.
4. Cope the contents of the `module-definitions` directory to the `{camunda-bpm-ee-wildfly-7.15.8-ee}/server/wildfly-22.0.1.Final/modules` directory.
5. Start the Wildfly server.
6. Login to the Tasklist webapp (demo/demo).
7. Create an instance of the 'waitingProcess' process.
8. Open Cockpit and look at the variable called `variable` of 'waitingProcess' process instance:
   1. Only the properties `property1` and `property3` are shown.
   2. The JSR310 and Joda DateTime properties are available.

## How it works

The important parts are:

1. the `jboss-deployment-structure.xml` with the following content:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<jboss-deployment-structure>
    <deployment>
        <exclude-subsystems>
            <!-- exclude jaxrs subsystem, which are added implicit by Wildfly -->
            <subsystem name="jaxrs"/>
        </exclude-subsystems>
        <dependencies>
            <module name="com.fasterxml.jackson.core.jackson-databind" slot="2.12.1" />
            <module name="com.fasterxml.jackson.datatype.jackson-datatype-joda" slot="2.12.1" />
            <module name="com.fasterxml.jackson.datatype.jackson-datatype-jsr310" slot="2.12.1" />
            <module name="joda-time.joda-time" slot="2.10.8" />
            <module name="org.camunda.spin.camunda-spin-dataformat-json-jackson" services="import" />
        </dependencies>
    </deployment>
</jboss-deployment-structure>
```

It excludes the JAX-RS subsystem and adds the Jackson dependencies with the correct versions.
These versions correspond to the versions which are used by Camunda Spin for (de)serialization.

See the [Camunda forum post](https://forum.camunda.org/t/camunda-json-marshalling-and-jsonignore/271/19)
and the [documentation](https://docs.camunda.org/manual/7.16/installation/full/jboss/manual/#problems-with-jackson-annotations) for more information.

2. The modules added to Wildfly needed for (de)serialization of the DateTime data types.

[WFLY-23]: https://docs.Wildfly.org/23/Developer_Guide.html#Implicit_module_dependencies_for_deployments
