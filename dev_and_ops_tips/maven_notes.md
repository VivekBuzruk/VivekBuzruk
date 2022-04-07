## Generate simple java maven project

### Using "Maven Quickstart Archetype" as stated in
> https://maven.apache.org/archetypes/maven-archetype-quickstart/

### Typical commands
> mvn archetype:generate -DgroupId=com.yourcompany -DartifactId=myproject -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

or

> mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4


- Example of value for property 'groupId': coin.sarvatech.diadl
- Example of value for property 'artifactId': diadl_foundation
- Example of value for property 'version' 1.0-SNAPSHOT: : 0.9
- Example of value for property 'package' coin.sarvatech.diadl: : coin.sarvatech.glowingBulbs

## Maven Commands
mvn compile
mvn install
mvn clean install
mvn package
mvn -Dmaven.test.skip=true package

## Maven Run
mvn exec:java -Dexec.mainClass=coin.sarvatech.glowingBulbs.GlowingBulbK_Finder1
> mvn exec:java -Dexec.mainClass=com.mycompany.App -Dexec.args="foo bar"
> java -cp target/diadl_foundation-1.0.jar  coin.sarvatech.glowingBulbs.GlowingBulbK_Finder1

### mvn test
> mvn test -Dtest=com.mycompany.AppTest#testMethod

