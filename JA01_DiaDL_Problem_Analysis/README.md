#generate simple java maven project
> mvn archetype:generate -DgroupId=com.yourcompany -DartifactId=myproject -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4

# Using "Maven Quickstart Archetype" as stated in https://maven.apache.org/archetypes/maven-archetype-quickstart/
#
value for property 'groupId': coin.sarvatech.diadl
value for property 'artifactId': diadl_foundation
value for property 'version' 1.0-SNAPSHOT: : 1.0
value for property 'package' coin.sarvatech.diadl: : coin.sarvatech.glowingBulbs
#
## Maven Commands
mvn compile
mvn install
mvn clean install
mvn package
mvn -Dmaven.test.skip=true package
# Maven Run
mvn exec:java -Dexec.mainClass=coin.sarvatech.glowingBulbs.GlowingBulbK_Finder1
> mvn exec:java -Dexec.mainClass=com.mycompany.App -Dexec.args="foo bar"
> java -cp target/diadl_foundation-1.0.jar  coin.sarvatech.glowingBulbs.GlowingBulbK_Finder1
#
mvn test
> mvn test -Dtest=com.mycompany.AppTest#testMethod
