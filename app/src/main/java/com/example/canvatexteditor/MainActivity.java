package com.example.canvatexteditor;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private FrameLayout canvasArea;
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();
    private int fontSize = 16;
    private boolean isBold = false;
    private boolean isItalic = false;
    private TextView currentTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasArea = findViewById(R.id.canvasArea);

        Button btnAddText = findViewById(R.id.btnAddText);
        Button btnBold = findViewById(R.id.btnBold);
        Button btnItalic = findViewById(R.id.btnItalic);
        Button btnIncreaseSize = findViewById(R.id.btnIncreaseSize);
        Button btnDecreaseSize = findViewById(R.id.btnDecreaseSize);

        btnAddText.setOnClickListener(v -> addText());
        btnBold.setOnClickListener(v -> toggleBold());
        btnItalic.setOnClickListener(v -> toggleItalic());
        btnIncreaseSize.setOnClickListener(v -> changeFontSize(true));
        btnDecreaseSize.setOnClickListener(v -> changeFontSize(false));
    }

    // Add new text to the canvas
    private void addText() {
        TextView newText = new TextView(this);
        newText.setText("New Text");
        newText.setTextSize(fontSize);
        newText.setTextColor(Color.BLACK);
        newText.setBackgroundColor(Color.LTGRAY);

        // Apply bold or italic if set
        applyTextStyle(newText);

        newText.setX(100);
        newText.setY(100);

        newText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Handle drag-and-drop
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    float x = event.getRawX();
                    float y = event.getRawY();
                    v.setX(x - v.getWidth() / 2);
                    v.setY(y - v.getHeight() / 2);
                }
                return true;
            }
        });

        // Save the initial state for undo/redo
        saveState();

        // Add the new TextView to the canvas
        canvasArea.addView(newText);
    }

    // Toggle Bold style
    private void toggleBold() {
        isBold = !isBold;
        if (currentTextView != null) {
            applyTextStyle(currentTextView);
        }
    }

    // Toggle Italic style
    private void toggleItalic() {
        isItalic = !isItalic;
        if (currentTextView != null) {
            applyTextStyle(currentTextView);
        }
    }

    // Change font size
    private void changeFontSize(boolean increase) {
        if (increase) {
            fontSize++;
        } else {
            fontSize--;
        }
        if (currentTextView != null) {
            currentTextView.setTextSize(fontSize);
        }
    }

    // Apply bold and italic style to the text
    private void applyTextStyle(TextView textView) {
        textView.setTypeface(null, isBold ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
        textView.setTypeface(null, isItalic ? android.graphics.Typeface.ITALIC : android.graphics.Typeface.NORMAL);
    }

    // Save the current text state for undo functionality
    private void saveState() {
        undoStack.push("State");
        redoStack.clear();
    }

    // Undo the last action
    private void undoAction() {
        if (!undoStack.isEmpty()) {
            redoStack.push(undoStack.pop());
            Toast.makeText(this, "Undo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No actions to undo", Toast.LENGTH_SHORT).show();
        }
    }

    // Redo the last undone action
    private void redoAction() {
        if (!redoStack.isEmpty()) {
            undoStack.push(redoStack.pop());
            Toast.makeText(this, "Redo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No actions to redo", Toast.LENGTH_SHORT).show();
        }
    }
}
