java -cp /home/lastware/.m2/repository/com/h2database/h2/1.3.176/h2-1.3.176.jar org.h2.tools.Script \
-url "jdbc:h2:~/data/sample/sample;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MVCC=TRUE" -user sa \
-script src/main/setup/sql/tomcat-jpa-sample-schema-generated.ddl
