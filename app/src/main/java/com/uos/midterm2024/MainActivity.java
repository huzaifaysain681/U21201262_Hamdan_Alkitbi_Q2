package com.uos.midterm2024;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameInput, bookTitleInput, reviewInput;
    CheckBox requestResponseCheckbox;
    Button submitReviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI elements
        nameInput = findViewById(R.id.nameInput);
        bookTitleInput = findViewById(R.id.bookTitleInput);
        reviewInput = findViewById(R.id.reviewInput);
        requestResponseCheckbox = findViewById(R.id.requestResponseCheckbox);
        submitReviewButton = findViewById(R.id.submitReviewButton);

        // Set up the Submit button event
        submitReviewButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String bookTitle = bookTitleInput.getText().toString();
            String review = reviewInput.getText().toString();
            boolean wantsResponse = requestResponseCheckbox.isChecked();

            if (name.isEmpty() || bookTitle.isEmpty() || review.isEmpty()) {
                Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Send data to ReviewerActivity
            Intent intent = new Intent(MainActivity.this, ReviewerActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("bookTitle", bookTitle);
            intent.putExtra("review", review);
            intent.putExtra("wantsResponse", wantsResponse);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String feedback = data.getStringExtra("reviewerFeedback");
            TextView feedbackTextView = findViewById(R.id.feedbackTextView);
            feedbackTextView.setText("Reviewer Response: " + feedback);
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "No response from the reviewer.", Toast.LENGTH_SHORT).show();
        }
    }
}
