**PROJECT TITLE: <title of the project>

**GROUP: <identifier of the group>

(Names, numbers, self assessment, and contribution of
the members of the group to the project according to:)

NAME1: <name>, NR1: <student number>,
GRADE1: <0 to 20 value>, CONTRIBUTION1: <0 to 100 %>

NAME2: <name>, NR2: < student number >, GRADE2: <0 to 20 value>, CONTRIBUTION2: <0 to 100 %>

NAME1: Ricardo Wragg Freitas, NR1: 199502870
GRADE1: 16, CONTRIBUTION1: 100%


...

(Note that the sum of the CONTRIBUTION? values must be
100 %)



** SUMMARY: (Describe what your tool does and its main features.)

The main purpose of this tool is to convert a yal file into a Jasmin syntax file


** EXECUTE: (indicate how to run your tool)

To perform the compilation to Jasmin:
java -jar yal2jvm.jar <input-file.yal>

To compile the Jasmin file to Java bytecodes:
java -jar jasmin.jar <input-file.j>

To execute the Java bytecodes:
java <input-file>


**DEALING WITH SYNTACTIC ERRORS: (Describe how the syntactic error recovery of your tool does work. Does it exit after the first error?)

The syntatic error analysis phase is performed by the javacc generated files.


**SEMANTIC ANALYSIS: (Refer the possible semantic rules implemented by your tool.)

The semantic analiser implements the visitor pattern in order to visit all the AST nodes.
The semantic visitor looks at each node and uses a particular analiser according to the node type.
The particular analisers are created at a factory.
There are N analiser types:
- Module analiser
    Analises a MODULE node
- Function analiser
    Analises a FUNCTION node. Checks for:
    - Duplicate function ids
    - Duplicate arguments
    - Correct return assignments
- Assign analiser
    Analises an ASSIGN node. Checks for:
    - Correct function calls


**INTERMEDIATE REPRESENTATIONS (IRs): (for example, when applicable, briefly describe the HLIR (high-level IR) and the LLIR (low-level IR) used, if your tool includes an LLIR with structure different from the HLIR)



**CODE GENERATION: (when applicable, describe how the code generation of your tool works and identify the possible problems your tool has regarding code generation.)

The code generator implements the visitor pattern in order to visit all the AST nodes.
The code generator looks at each node and uses a particular generator according to the node type.
The particular generators are created at a factory.
There are N generator types:
- Module code generator
- Function code generator
- Function end code generator
- Function call code generator



**OVERVIEW: (refer the approach used in your tool, the main algorithms, the third-party tools and/or packages, etc.)

The application was split into:

    3 main modules:
    - Syntactic analiser
    - Semantic analiser
    - Code generator

    2 secondary modules:
    - Utilities module
    - Profiler module

    one main file, which:
    - Acts as a façade for all the compilation steps
    - Performs input checks
    - Displays errors

General development principles
- Modules talk to each other via services, which expose the module's functionality to the outside
- Design patterns where applicable
- No anti-patterns
- SOLID principles
- Clean code


**TESTSUITE AND TEST INFRASTRUCTURE: (Describe the content of your testsuite regarding the number of examples, the approach to automate the test, etc.)



**TASK DISTRIBUTION: (Identify the set of tasks done by each member of the project.)

Ricardo Wragg Freitas, NR: 199502870
    Architect
        Application overall architecture
        Module definition and contracts
        General development principles
    Developer
        Application development
        Implementation details
        Design pattern choice
    Project Manager
        Project management and tracking
        Task estimation
    Product Owner
        Translation of specifications into functional requirements
    QA engineer
        Test suite design, implementation and testing
    Release Manager
        Deliverables definition
        Deliverables preparation
        Repository maintenance
        README.txt writing


**PROS: (Identify the most positive aspects of your tool)

It rocks!


**CONS: (Identify the most negative aspects of your tool)

None.