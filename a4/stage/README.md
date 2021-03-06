This is just a simple grammar for the unnamed language as specified in the CSC435 assignments.
For further information, see the pdf in this directory.

Note that in order to run this project, an out of date version of ANTLR (version 3.0.1) must be set on the class path.

To build the file:

`make`

To clean up the files generated by ANTLR:

`make clean`

To run the tests directly using a shell script, run the `tests.sh` file.

Otherwise, if shell scripts are unavailable for whatever reason (such as on the UVic servers), then run `tests.py` using Python.

The tests script automatically runs `make clean` and `make`, so there's no need to have everything pre-compiled for it. The class path must still be set correctly for it to work however. The script will compile all code into `.class` files and automatically run that code in the terminal. The output files for the tests may be found in `/output/class/`.

If the grammar is working properly, then the tests in the accept folder should all be accepted by the grammar (no output generated by ANTLR) and type checker. Likewise, any tests in the reject folder should have some sort of output generated by either ANTLR or the type checker stating where the error was.

Right now, the compiler will throw an error during the code generation phase if there is not a valid typed return statement at the bottom of a non-void returning function (not able to check during type checking).
