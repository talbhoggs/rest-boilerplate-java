Set up environment

Step 0
create maven.config file and add the repo path (if you don't follow the default)
.mvn/maven.config
-Dmaven.repo.local=/.m2/repository2


Step 1: Install Multiple JDKs with SDKMAN
sdk install java 17.0.10-tem
sdk install java 21.0.2-tem

Step 2: Switch Versions in Your Shell
sdk use java 21.0.2-tem
or
source setup.sh

Step 3
Configure VS Code Runtime
Open Command Palette → Java: Configure Java Runtime.
Add both JDK paths if they don’t show up:

This will insure that if run the program using the IDE Run Java it will run the correct JDK





