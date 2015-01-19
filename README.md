# Tomcat JPA Sample

This is the readme file for the Tomcat JPA Sample application.

## Technology stack

[Tomcat](http://tomcat.apache.org/).

Tomcat Maven Plugin ([which](http://tomcat.apache.org/maven-plugin.html)), last version [2.2](http://tomcat.apache.org/maven-plugin-2.2/) implies Tomcat 7.

[Tomcat 7.0.57](http://tomcat.apache.org/download-70.cgi), which [implies](http://tomcat.apache.org/whichversion.html):
- Servlet Spec 3.0
- JSP Spec 2.2
- EL Spec 2.2
- Java 6 and later

Java 1.7.0_67.

H2 in its [latest stable version 1.3.176](http://www.h2database.com/html/download.html).

JPA provider [Hibernate](http://hibernate.org/orm/) in its [latest stable version 4.3.8.Final](http://hibernate.org/orm/downloads/),
which implies:
- JPA 2.1

Bean Validation provider [Hibernate Validator](http://hibernate.org/validator/)
in its [latest stable version 5.1.3.Final](http://hibernate.org/validator/downloads/).

CDI provider [Weld](http://weld.cdi-spec.org/) in its latest current version 2.2.8.Final.
which implies:
- CDI 1.2

[DeltaSpike](https://deltaspike.apache.org/) for CDI extensions:

- [JPA Module](https://deltaspike.apache.org/documentation/jpa.html) for <strong>declarative transactions</strong>.
- [Data Module](https://deltaspike.apache.org/documentation/data.html) as a <code>@Repository</code> pattern provider.

<!--
[Juplo Hibernate 4 Maven plugin](http://juplo.de/hibernate4-maven-plugin/) to <strong>generate create table sql script</strong>.
-->

## Running locally

1. Install standalone Tomcat.
2. Configure its <code>conf/tomcat-users.xml</code>:
<pre>
  &lt;role rolename="manager-gui"/>
  &lt;role rolename="manager-script"/>
  &lt;user username="admin" password="" roles="manager-gui,manager-script"/>
</pre>
3. Copy the h2-1.3.176.jar to <code>lib/</code> directory.
4. Configure the <code>conf/server.xml</code>, find the <code>&lt;GlobalNamingResources></code> and insert into it:
<pre>
    &lt;!-- sample h2 database -->
    &lt;Resource name="jdbc_sample" auth="Container" type="javax.sql.DataSource" username="sa"
        password="" driverClassName="org.h2.Driver" 
        url="jdbc:h2:~/data/sample/sample;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MVCC=TRUE"
        maxActive="10" maxIdle="4" maxWait="10000" />
</pre>
5. Start Tomcat <kbd>startup.sh</kbd>.
6. Verify the datasource is bound to the global JNDI <code>jdbc_sample</code>: [Tomcat global JNDI resources](http://localhost:8080/manager/text/resources).
7. Execute <kbd>mvn</kbd> to build the war and deploy / re-deploy it to the running Tomcat server.
8. Access the [home page](http://localhost:8080/tomcat-jpa-sample/).
9. Refresh the home page repeatedly to get new sample values generated.

## Database create table sql script generation

Shutdown Tomcat <kbd>shutdown.sh</kbd> and execute <kbd>db-save.sh</kbd> to backup the database
into the <code>setup/sql/tomcat-jpa-sample-schema-generated.ddl</code> file.

Tweak the contents as required. (Remove inserts, name the primary key constraints, etc.)

<!--
Execute <kbd>mvn -Pdb</kbd> to (re-)generate create table sql script in <code>setup/sql/tomcat-jpa-sample-schema-generated.ddl</code> file.
-->
