package com.uos.midterm2024;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewerActivity extends AppCompatActivity {

    TextView reviewInfoTextView;
    EditText responseInput;
    Button submitResponseButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewer);

        // Initialize UI elements
        reviewInfoTextView = findViewById(R.id.reviewInfoTextView);
        responseInput = findViewById(R.id.responseInput);
        submitResponseButton = findViewById(R.id.submitResponseButton);
        cancelButton = findViewById(R.id.cancelButton);

        // Get the intent data
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String bookTitle = intent.getStringExtra("bookTitle");
        String review = intent.getStringExtra("review");
        boolean wantsResponse = intent.getBooleanExtra("wantsResponse", false);

        // Display the user's review
        reviewInfoTextView.setText("Name: " + name + "\nBook Title: " + bookTitle + "\nReview: " + review);

        // If the checkbox is not checked, hide response input and button
        if (!wantsResponse) {
            responseInput.setVisibility(View.GONE);
            submitResponseButton.setVisibility(View.GONE);
        }

        // Handle the Submit Response button click
        submitResponseButton.setOnClickListener(v -> {
            String feedback = responseInput.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("reviewerFeedback", feedback);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Handle the Cancel button click
        cancelButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
