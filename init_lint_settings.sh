#!/bin/bash

chmod +x "./gradlew"

# pre commit 린트 검사 훅 등록
./gradlew addKtlintCheckGitPreCommitHook

echo "린트 검사 규칙이 적용되었습니다."
