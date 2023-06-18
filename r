#!/usr/bin/env bash
set -euo pipefail
IFS=$'\r\n'

rm -f *.class
javac *.java
java imsyn
eog out/tmp.png
