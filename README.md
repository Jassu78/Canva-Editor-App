# Canva Editor App

A straightforward Android application for basic text editing. Users can add text to a canvas, adjust its appearance with bold and italic styles, change font size, and freely drag elements around.

---

## Features

* **Add Text:** Dynamically add new text elements to the canvas.
* **Bold/Italic Toggle:** Apply or remove bold and italic formatting.
* **Font Size Adjustment:** Easily increase or decrease text size.
* **Drag and Drop:** Move text elements anywhere on the canvas.
* **Basic Undo/Redo:** Provides fundamental state management for recent actions.

---

## Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Jassu78/Canva-Editor-App.git](https://github.com/Jassu78/Canva-Editor-App.git)
    ```
2.  **Open in Android Studio:** Import the cloned project.
3.  **Run:** Build and deploy the app to an Android device or emulator.

---

## How to Use

1.  Tap **"Add Text"** to place a new "New Text" element on the canvas.
2.  To modify an existing text element (bold, italic, size), you'll need to implement a mechanism to **select** it first (e.g., by adding a click listener to each `TextView` to set it as the `currentTextView`).
3.  Use the **"Bold"** and **"Italic"** buttons to toggle these styles.
4.  Adjust font size with the **"Increase Size"** and **"Decrease Size"** buttons.
5.  **Drag** any text element by touching and moving it.
6.  Use **"Undo"** and **"Redo"** for basic action management.

---

## Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests.

---

## License

This project is open-source and distributed under the [MIT License](https://opensource.org/licenses/MIT).
