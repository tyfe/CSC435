echo "Building test files"
make clean
make
rm -rf output
mkdir output
mkdir output/ir
mkdir output/class

echo "Accept tests:\n"
for file in ./tests/accept/*
do
    name=$(basename "$file" ".ul")
    echo "Testing file $file"
    java Compiler "$file" > "output/class/$name.j"
    cd output/class
    java jasmin.Main "$name.j"
    echo "Output created in output/class/$name.class\n"
    echo "Running program $name.ul\n"
    java "$name"
    cd ../..
    echo "\n"
done

echo "Reject tests:\n"
for file in ./tests/reject/*
do
    echo "Testing file $file\n"
    java Compiler "$file"
done
