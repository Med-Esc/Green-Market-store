## Quick orientation

- Language: Java. The repository currently contains a single entry point at `src/Main.java` (default package) which prints "Hello, World!".
- Project layout (top-level `src/` folders): `cart/`, `confirmation/`, `filtering/`, `Home/`, `Prodects/` — all currently empty. Note: the folder name `Prodects` is spelled as-is in the tree; preserve this exact name when editing.
- No build manifest detected (no `pom.xml`, `build.gradle`, or `gradlew`). The project includes an IntelliJ `.iml` and `.idea/` configuration, so the intended workflow is often via IntelliJ IDEA.

## What to know before making edits

- Entry point: `src/Main.java`. Because code is in the default package, do not add package declarations unless you also update the compile/run instructions or switch to a build tool (Maven/Gradle).
- Module folders (e.g. `src/Home/`, `src/cart/`) represent UI/feature areas. They are empty now but are the expected places for related classes.
- Preserve existing file/folder names (including typos like `Prodects`) unless doing a deliberate refactor and updating run/IDE config.

## Build / run / debug (discovered from the repo)

1) Preferred (IDE): open the project in IntelliJ IDEA (project has `.iml` and `.idea/`) and run the `Main` class using the IDE run configuration.

2) Manual (PowerShell) — useful when no build tool is present:

```
# from project root (PowerShell)
$files = Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out Main
```

If you introduce packages (e.g. `package com.example;`), compile the sources into `out` and run the fully-qualified main class (for example `java -cp out com.example.Main`).

## Conventions & patterns observed (for AI edits)

- Default-package based single-entry application. Keep changes minimal and consistent with this until the project adopts a build tool.
- Feature/area folders (empty now) are the canonical places for UI and domain classes. When suggesting new files, place them under the appropriate folder (e.g. `src/filtering/FilterPanel.java`).
- No tests, no dependency files, and no external service integrations were found. When adding a dependency, prefer adding a build manifest (Maven/Gradle) and update these instructions accordingly.

## Useful examples to reference in PRs or edits

- To call out the entry point: `src/Main.java` (prints `Hello, World!`).
- To add a new UI class for products, put it at `src/Prodects/ProductList.java` (note folder name).

## When merging or refactoring

- If you move classes into packages, update the project run instructions and communicate the change in the PR description (how to compile/run with the new package layout).
- If you add a build tool (recommended for non-trivial changes), include quick commands in this file for the new tool (Maven or Gradle) and keep the old manual steps for backwards compatibility.

---
If anything here is unclear or you want me to emphasize other files or workflows (IDE run configurations, intended GUI framework, or tests), tell me which areas to expand and I will iterate.
