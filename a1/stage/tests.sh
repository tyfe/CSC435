echo "Building test files"
make clean
make

echo "Accept tests:\n"
for file in ./tests/accept/*
do
    echo "Testing file $file\n"
    java Compiler "$file"
done

echo "Reject tests:\n"
for file in ./tests/reject/*
do
    echo "Testing file $file\n"
    java Compiler "$file"
done
