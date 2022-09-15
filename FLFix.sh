#!/bin/bash

# The path of containing Defects4J bugs.
d4jData=$1

# The path of Defects4J git repository.
d4jPath=$2

# The fault localization metric used to calculate suspiciousness value of each code line.
metric=Ochiai

# The buggy project ID: e.g., Chart_1
bugId=$3
datasetCommandAndCompilePath=$5

java -cp "target/dependency/*" edu.lu.uni.serval.main.Main $d4jData $d4jPath $bugId $metric $datasetCommandAndCompilePath