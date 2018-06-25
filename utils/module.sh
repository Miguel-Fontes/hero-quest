#!/bin/bash
# module.sh
#
# Creates maven modules based on a project.
#
# Changelog
# Version 0: Hardcoded group id, package data, archetype and others. Not ready for general use.
#
# Future Features
# - Force confirmation switch "-y"
# - Read project information from pom.xml, and change package, group id and other parameters accordingly
#
# Miguel Fontes, 06/2018

declare ARTIFACT_ID=$1
declare PACKAGE_MODIFIER=$2

declare TRUE=0
declare FALSE=1

declare USAGE_MESSAGE="
USAGE: $(basename $0) <ARTIFACT_ID> <PACKAGE-MODIFIER>

  ARTIFACT_ID:      The artifactId of the maven module to be generated
  PACKAGE-MODIFIER: Following a base domain, a modifier is used to
                    isolate the classes of the project. Example: br.com.miguelmf.<PACKAGE_MODIFIER>
"

isValid() {
    isValid="$TRUE"

    if [ -z "$ARTIFACT_ID" ]; then
        echo "ERROR: Artifact id not supplied"
        isValid="$FALSE"
    fi

    if [ -z "$PACKAGE_MODIFIER" ]; then
        echo "ERROR: Package modifier not supplied"
        isValid="$FALSE"
    fi

    return "$isValid";
}

module() {
    mvn archetype:generate                               \
      -DgroupId=br.com.miguelmf                          \
      -DartifactId="$ARTIFACT_ID"                        \
      -Dpackage=br.com.miguelmf."$PACKAGE_MODIFIER"      \
      -DarchetypeGroupId=br.com.miguelmf                 \
      -DarchetypeArtifactId=quickstart-junit5-archetype  \
      -DinteractiveMode=false
}

confirmation() {
    echo "Execution Parameters: "
    echo "    Artifact Id      : $ARTIFACT_ID"
    echo "    Package Modifier : $PACKAGE_MODIFIER"
    echo

    read -p "Confirm execution? (y/n) " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Yy]$ ]]
    then
        module
    fi
}

help() {
    echo "$USAGE_MESSAGE"
}

main() {
    if isValid; then
        confirmation
    else
        help
    fi
}

main