# Incremental IQL Engine for BPM

## Introduction
This project introduces an innovative Incremental IQL Engine designed to enhance Business Process Management (BPM). It aims to improve workflow efficiency and uncover potential business opportunities within organizations. The engine simplifies the querying of workflow logs, making it accessible for non-technical users like project managers, and addresses the limitations of traditional BPM life cycles.

## Key Features
- **Incremental Algorithm**: Enhances log processing efficiency by treating inputs as a stream.
- **User-Friendly Interface**: Enables direct querying of workflow logs, eliminating the need for complex database operations.
- **High Performance**: Demonstrates up to a 1000x speed increase in specific scenarios compared to the traditional IQL engine.
- **Scalability**: Effectively handles over 10 million log records without heap memory constraints.
- **In-depth Analysis**: Offers insights into factors affecting query speed and efficiency, with a focus on different operator types.

## Project Structure
- `main/`: Contains `Main.java`, the entry point for running experiments.
- `model/`: Organizes workflow logs, including sub-folders like `incident`, `incident tree`, and `log`.
- `evaluation/`: Holds predefined rules for running queries and assessing performance.
- `test/`: Contains test scripts and cases for system validation.

## Installation
Clone and set up the project using the following commands:
```bash
git clone https://github.com/thanlau/query-workflow-logs-system.git
cd query-workflow-logs-system
```

## Usage
To run the experiment using the Main.java file in the main directory, follow these instructions:
```bash
cd main
# Compile and run the Main.java file
javac Main.java
java Main
```

## Contributing
We welcome contributions! If you'd like to contribute, please:

Fork the repository.
Create a new branch (git checkout -b feature-branch).
Commit your changes (git commit -am 'Add some feature').
Push to the branch (git push origin feature-branch).
Submit a Pull Request.
