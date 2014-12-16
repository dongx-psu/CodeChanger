# README #
This is a Code Changer for simplified C (C99 Standard), which based on my previous compiler project. I added a few features to my compiler project including new grammar, pretty code printer and the application of Code Changer. Why there is such a project is a mystery. :D

## Features ##
- Support numbers of C99 language features including pointers, complicated variable declarations, unions, etc.
- Pretty code printer which can reform the code to a clean manner.
- Support short curcuit expressions in main predication positions.

## Dependencies && Running ##
You can work with this project with eclipse. Please make sure your java is 1.7+. This project uses Jflex and Cup, use the batch file in the source folder to generate Parser and Lexer.

There is a runnable jar under the project folder. Run it with  
`java -jar CodeChanger.jar /path/to/a/cpp/input/source/file function_name /path/to/the/entire/project/directory`.   
It will generate the output source in specific directory with name `output_source.c` and show the output about function call count and list in standard output.

`test1.c` is the original example, and `test2.c` is a more complicated one. You can also find the outputs for them in the project folder. 

## Notes ##
Considering expansibility, I build this project in a compiler manner based on my old compiler project. Since time is limited, I only implemented core part for the requirements, which can track function calls, resolve shortcut curcuit. Note that I only handle the situation where the short curcuit exxpressions placed as a predication in 'if' statement, 'while' statement and the second oprand of 'for' statments. It can be extended by reorganizing the AST in a more complicated manner.

This project can now deal with continuous short curcuit expressions, like: `if ((A() || B()) && (C() || D()))`, recursively. Yet, I still do not handle the case, such as `if (A() + (B() && X()) == 0)`. In short, I only handle the situation when the whole short curcuit expression placed as the whole predication. Other features, for example unfolding recursive functions calls (`A(B(), C(D()))`), can also be achieved by reconstruct the AST carefully, which I didn't work on. If we want to introduce accurate data types for added variables, we need to go at least one step further to semantic checking.

In my opinion, if we want to deal with this task more perfectly, we need to push the work into IR level where each expression could be extract more accurately. Nevertheless, this is only a toy project that has limited features and maybe also contains some bugs.