#!/bin/bash

# Change these variables as needed
WORKSPACE="testing-server"
MC_VERSION="1.16.5"
PAPER_BUILD="776"

# Copy plugin jar file to test server plugins folder
cp target/*.jar $WORKSPACE/plugins/plugin.jar

## ============== DO NOT EDIT THE SCRIPT BELOW UNLESS YOU KNOW WHAT YOU ARE DOING ============== ##

#cd || exit # Moving to the user folder or exit if it fails.

# Checking the workspace folder availability.
if [ ! -d $WORKSPACE ]; then
  # Create the workspace folder.
  mkdir $WORKSPACE
fi

cd $WORKSPACE || exit # Moving to the workspace fodler or exit if it fails.

# Check for the paper executable
PAPER_JAR="paper-$MC_VERSION-$PAPER_BUILD.jar"
PAPER_LNK="https://papermc.io/api/v1/paper/$MC_VERSION/$PAPER_BUILD/download"

if [ ! -f $PAPER_JAR ]; then
  wget -O $PAPER_JAR $PAPER_LNK
fi

java -jar $PAPER_JAR