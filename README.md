# RecordParserApp
A Java application to parse a multiline String and provide required informations

download/clone the repo and keep it in local system

JDK11 or above must be installed in local to compile the files and then run the main and tests

Steps to run - 
1. open the main diretory in termnal

2. run command to compile all java files in App (check for correct commands if local system varies from windows to unix or linux

" javac -d out .\src\app\main\Main.java .\src\app\model\Record.java .\src\app\parser\DataParser.java .\src\app\parser\DataparserInterface.java .\src\app\processor\BuildDataProcessor.java .\src\app\processor\DataProcessorInterface.java .\src\app\report\BuildReportGenerator.java .\src\app\report\ReportGeneratorInterface.java
"

3. run command for runnnig the Main 

"java -cp out app.main.Main"

4. run command to compile all classes including tests classes

"javac -d out -cp junit-platform-console-standalone-1.9.2.jar .\src\app\main\Main.java .\src\app\model\Record.java .\src\app\parser\DataParser.java .\src\app\parser\DataparserInterface.java .\src\app\processor\BuildDataProcessor.java .\src\app\processor\DataProcessorInterface.java .\src\app\report\BuildReportGenerator.java .\src\app\report\ReportGeneratorInterface.java .\src\tests\parser\DataParserTest.java .\src\tests\processor\BuildDataProcessorTest.java .\src\tests\report\BuildReportGeneratorTest.java"

5. run command to run the tests files

"java -jar junit-platform-console-standalone-1.9.2.jar --classpath out --scan-classpath"


