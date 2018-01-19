import os

os.system("""
        echo "Building test files"
        make clean
        make

        echo "Accept tests:"
        for file in ./tests/accept/*
        do
            echo "Testing file $file"
            java Compiler "$file"
        done

        echo "Reject tests:"
        for file in ./tests/reject/*
        do
            echo "Testing file $file"
            java Compiler "$file"
        done
    """
)
