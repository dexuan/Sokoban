#!/bin/bash

rm -rf bin test.jar &&\
    mkdir bin &&\
    javac -cp ./bin/ -d ./bin/ src/myPackage/Constant.java &&\
    javac -cp ./bin/ -d ./bin/ src/myPackage/Tools.java &&\
    javac -cp ./bin/ -d ./bin/ src/myPackage/Sokoban.java &&\
    javac -cp ./bin/ -d ./bin/ src/myPackage/Coastline.java &&\
    javac -cp ./bin/ -d ./bin/ src/myPackage/myClass.java &&\
    echo "Main-Class: myPackage.myClass" > bin/manifest.mf &&\
    cd bin &&\
    jar -cvfm test.jar manifest.mf myPackage/ &&\
    cd ../ &&\
    ln -s bin/test.jar &&\
    java -jar test.jar


