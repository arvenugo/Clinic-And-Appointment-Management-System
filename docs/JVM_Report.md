* Class Loader

A Class Loader is a fundamental component of the Java Runtime Environment (JRE) responsible for dynamically loading Java classes into the Java Virtual Machine (JVM). Unlike traditional compiled languages, Java loads classes on demand (lazy loading) rather than all at once at startup. 

 
Hierarchy of Built-in Class Loaders 
Java uses a parent-delegation hierarchy to manage class loading: 

Bootstrap Class Loader: The "root" loader, written in native code. It loads core Java APIs (e.g., java.lang.*, java.util.*) from the JDK's runtime image (formerly rt.jar).
Platform Class Loader (formerly Extension): A child of the Bootstrap loader. It loads platform-specific modules and extensions, such as Java SE platform APIs not in the core.
System/Application Class Loader: A child of the Platform loader and the one most developers interact with. It loads classes from the application's classpath (defined via -cp or CLASSPATH environment variable).
Custom Class Loaders: Developers can create their own by extending the java.lang.ClassLoader class to load classes from unique sources like databases, networks, or encrypted files.

* Runtime Data Areas (Heap, Stack, Method Area, PC Register)

VM Runtime Data Areas are memory regions allocated during program execution, including Heap, Stack, Method Area, and PC Register. The Heap shares objects/instances across all threads, while the Stack manages local variables and method calls per thread. The Method Area (now metaspace) stores class metadata/statics, and PC Registers track current instruction pointers for each thread. 

Core JVM Runtime Data Areas
Heap Memory (Shared):
Purpose: Stores all objects and their instance variables.
Lifecycle: Created on JVM startup; cleared by Garbage Collection.
Structure: Divided into Young Generation (new objects) and Old Generation (long-lived objects).
JVM Stack (Per-Thread):
Purpose: Stores method call frames, local variables, and object references.
Lifecycle: Created when a thread starts and destroyed when it terminates.
Key Detail: Fast memory, throws StackOverflowError if memory is exhausted.
Method Area / Metaspace (Shared):
Purpose: Stores class structure, field/method data, runtime constant pool, and static variables.
Lifecycle: Created on JVM startup.
Key Detail: Implemented as Metaspace (using native memory) in modern Java (Java 8+) rather than permanent generation.
PC Register (Per-Thread):
Purpose: Holds the address of the current instruction being executed.


* Execution Engine

The Execution Engine is a core component of the Java Virtual Machine (JVM) responsible for executing the bytecode loaded into memory. It acts as the bridge between platform-independent Java bytecode and the underlying hardware by translating instructions into machine code. 

The engine uses three main sub-components to balance startup speed and runtime performance: 
1. Interpreter
The Interpreter is the first to run when an application starts. 
Function: It reads bytecode instructions sequentially and executes them line-by-line.
Pros & Cons: It allows for a fast application startup since no initial compilation is needed. However, it is slower for repeated tasks because it re-interprets the same code every time it is called. 

2. Just-In-Time (JIT) Compiler
To improve performance, the JIT Compiler works alongside the interpreter. 

Function: It monitors code execution using a Profiler to identify "hot spots"—sections of code executed frequently.
Optimization: Once a hot spot is identified, the JIT compiler translates that entire block of bytecode into native machine code and stores it in the Code Cache for direct reuse.
Components:
Intermediate Code Generator: Produces an intermediate representation of the code.
Code Optimizer: Refines the intermediate code for efficiency.
Target Code Generator: Produces final native machine code. 

3. Garbage Collector (GC)
The Garbage Collector is an automated memory management tool. 
Function: It runs as a background daemon thread, identifying and destroying objects that are no longer reachable or referenced by the application.
Benefit: It prevents memory leaks and frees developers from manual memory deallocation, which is required in languages like C++.

* JIT Compiler vs Interpreter


| Feature      | Interpreter                                               | JIT Compiler                                                           |
| ------------ | --------------------------------------------------------- | ---------------------------------------------------------------------- |
| Execution    | Reads and executes code instruction by instruction        | Compiles chunks of code into native machine code at runtime            |
| Performance  | Slower; repeats translation work every time the code runs | Faster; once code is compiled and cached, it runs at native speeds     |
| Startup      | Fast; can start running the program immediately           | Slower; requires an initial "warm-up" period to compile code           |
| Optimization | Minimal to none                                           | High; can perform speculative optimizations based on real runtime data |
| Memory       | Low; only needs to store the current instructions         | Higher; needs memory to store the generated machine code               |


* "Write Once, Run Anywhere"

Java applications are called WORA (Write Once Run Anywhere). This means a programmer can develop Java code on one system and can expect it to run on any other Java-enabled system without any adjustment. This is all possible because of JVM. How Java is WORA: In traditional programming languages like C, C++ when programs were compiled, they used to be converted into the code understood by the particular underlying hardware, so If we try to run the same code at another machine with different hardware, which understands different code will cause an error, so you have to re-compile the code to be understood by the new hardware. In Java, the program is not converted to code directly understood by Hardware, rather it is converted to bytecode(.class file), which is interpreted by JVM, so once compiled it generates bytecode file, which can be run anywhere (any machine) which has JVM( Java Virtual Machine) and hence it gets the nature of Write Once and Run Anywhere.



