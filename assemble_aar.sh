#!/bin/bash

chmod +x "./gradlew"

# ktlint 검사
./gradlew ktlintCheck

# AAR 배포
./gradlew assembleRelease

# 모든 모듈의 AAR 을 수집하여 ./build/outputs 에 배치
./gradlew assembleAAR

