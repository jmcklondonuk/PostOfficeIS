#!/bin/sh
if [ "$#" -ne 0 ]; then
	ARGS="$@"
	./gradlew run --console=plain --args="${ARGS}"
else
	./gradlew run --console=plain
fi

