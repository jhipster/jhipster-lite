#!/bin/bash

GIT_MAIN_BRANCH='main'
GIT_REMOTE='upstream'

show_syntax() {
  echo "You want to release a new version"
  echo "  - current version: ${currentVersion}"
  echo "  - release version: ${releaseVersion} (which is a patch)"
  echo " "
  echo "Usage: $0 <patch|minor|major>" >&2
  exit 1
}

currentVersion=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)
releaseVersion=${currentVersion//-SNAPSHOT/}

checkGit=$(git status --porcelain|wc -l)
if [[ $checkGit != 0 ]]; then
  echo "*** Check: there are uncommitted changes..."
  show_syntax
fi

if [ "$#" -ne 1 ]; then
  show_syntax
fi

echo "*** Git: update project..."
git switch $GIT_MAIN_BRANCH
git fetch $GIT_REMOTE
git rebase $GIT_REMOTE/$GIT_MAIN_BRANCH

if [[ "$1" == "patch" ]]; then
  echo "*** Version: remove SNAPSHOT and keep the version"
  ./mvnw versions:set -DremoveSnapshot versions:commit -q

elif [[ "$1" == "minor" ]]; then
  echo "*** Version: remove SNAPSHOT and change to minor version"
  ./mvnw versions:set -DremoveSnapshot versions:commit -q
  ./mvnw build-helper:parse-version versions:set \
    -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.nextMinorVersion}.0 \
    versions:commit -q
elif [[ "$1" == "major" ]]; then
  echo "*** Version: remove SNAPSHOT and change to major version"
  ./mvnw versions:set -DremoveSnapshot versions:commit -q
  ./mvnw build-helper:parse-version versions:set \
    -DnewVersion=\${parsedVersion.nextMajorVersion}.0.0 \
    versions:commit -q
else
  show_syntax
fi

echo "*** Git: commit, tag and push tag..."
releaseVersion=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)
npm version "${releaseVersion}" --no-git-tag-version

git add . && git commit -m "Release v${releaseVersion}"
git tag -a v"${releaseVersion}" -m "Release v${releaseVersion}"
git push $GIT_REMOTE v"${releaseVersion}"

echo "*** Version: add SNAPSHOT"
./mvnw build-helper:parse-version versions:set \
  -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion}-SNAPSHOT \
  versions:commit -q

echo "*** Git: commit, push to $GIT_MAIN_BRANCH..."
nextVersion=$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout)
npm version "${nextVersion}" --no-git-tag-version
git add . && git commit -m "Update to next version v${nextVersion}"
git push $GIT_REMOTE $GIT_MAIN_BRANCH
