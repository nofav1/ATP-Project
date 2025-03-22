# 🧩 Maze Generator – Client-Server Java Application

## 📌 Overview

This project implements a **client-server architecture in Java**, focusing on the **generation and manipulation of mazes**. It demonstrates advanced programming concepts such as:

- **Design patterns**
- **Multithreading**
- **Client-server communication**

The goal is to build a dynamic application that can generate mazes, handle multiple client requests, and demonstrate efficient data processing.

---

## 🧱 Project Components

### 1. 🔧 Maze Generation
- Utilizes the **Decorator Design Pattern** to support flexible implementation of various maze generation algorithms.
- Supports generation of different maze types based on user input or predefined parameters.

### 2. 🔌 Client-Server Communication
- A **Java server** listens for incoming client requests, processes them by generating mazes, and returns results.
- Communication is managed via input/output streams to ensure smooth and efficient data exchange.

### 3. 🧵 Multithreading
- The server handles **multiple client connections concurrently** using Java threads.
- This ensures high performance and responsiveness under heavy load.

### 4. ✅ Testing
- Includes a suite of **JUnit tests** to validate:
  - Maze generation logic
  - Client-server communication
- Ensures reliability and correctness of core functionalities.

---

## 🚀 Installation & Setup

1. **Clone the repository**  
   Clone the project to your local machine using:
   ```bash
   git clone https://github.com/nofav1/ATP-Project.git

2. **Open in IntelliJ or another Java IDE**

   - Open the project folder: `ATP-Project/ATP-Project-PartB`

   - The project includes subfolders like:
     - `src/` – contains the main source code (`Client`, `Server`, `Algorithms`, etc.)
     - `JUnit/` – contains test files
     - `resources/` – configuration and supporting files

3. **Build and run the project**

   - Make sure your IDE resolves all dependencies (e.g., `JUnit`)
   - Mark the `src` folder as **Source Root** and the `test` folder as **Test Root** in IntelliJ (if needed)
   - Compile and run the server and client from their respective main classes
