#!/bin/bash

cd /Users/jjarczyk/eclipse-workspace/se355/scripts/

java -jar ./jars/plantuml/plantuml-dependency-cli-1.4.0-jar-with-dependencies.jar -b ./../src/main/java/ -i **/*.java -e **/*Test*.java  -o ./artifacts/deps.txt 

java -jar ./jars/plantuml/plantuml.jar ./artifacts/deps.txt 

open ./artifacts/deps.png