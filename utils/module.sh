#!/bin/bash
# module.sh
#
# Creates maven modules based on a project.
#
# Changelog
# Version 0: - Hardcoded group id, package data, archetype and others. Not ready for general use.
# Version 1: - Fetches the Group Id from pom.xml.
#            - Constans are now read-only (added -r switch)
#
# Future Features
# - [ ] Force confirmation switch "-y"
# - [ ] Read project information from pom.xml, and change package, group id and other parameters accordingly
# - [ ] Parameters that constants for a user (my preferred default group id) should be externalized in some sort of configuration
# - [ ] Add option to enter the group id manually, if it is desired
# - [ ] Validate if working directory has a pom.xml, otherwise stop execution with a discriptive message
#
# Miguel Fontes, 06/2018

# Command Line Parameters
declare -r ARTIFACT_ID=$1
declare -r PACKAGE_MODIFIER=$2

# Constants
declare -r TRUE=0
declare -r FALSE=1

# External Parameters (maybe load from environment variables of a configuration file)
declare PROJECT_GROUP_ID
declare ARCHETYPE_ARTIFACT_ID="quickstart-junit5-archetype"
declare ARCHETYPE_GROUP_ID="br.com.miguelmf"

# Others
declare -r USAGE_MESSAGE="
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

fechGroupIdFromPom() {
    PROJECT_GROUP_ID=$(grep -oPm1 "(?<=<groupId>)[^<]+" pom.xml)
}


module() {
    mvn archetype:generate                               \
      -DgroupId=$PROJECT_GROUP_ID                        \
      -DartifactId="$ARTIFACT_ID"                        \
      -Dpackage="$DEFAULT_PACKAGE"."$PACKAGE_MODIFIER"   \
      -DarchetypeGroupId=$ARCHETYPE_GROUP_ID             \
      -DarchetypeArtifactId=$ARCHETYPE_ARTIFACT_ID       \
      -DinteractiveMode=false
}

confirmation() {
    echo "Maven Execution Parameters: "
    echo "    Artifact Id           : $ARTIFACT_ID"
    echo "    Group Id              : $PROJECT_GROUP_ID"
    echo "    Package               : $PROJECT_GROUP_ID.$PACKAGE_MODIFIER"
    echo "    Archetype Artifact Id : $ARCHETYPE_ARTIFACT_ID"
    echo "    Archetype Group Id    : $ARCHETYPE_GROUP_ID"
    echo ""
    echo "Environment: "
    echo "    Working Directory     : $(pwd)"
    echo ""

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
    fechGroupIdFromPom

    if isValid; then
        confirmation
    else
        help
    fi
}

main