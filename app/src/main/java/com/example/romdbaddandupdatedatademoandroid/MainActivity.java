package com.example.romdbaddandupdatedatademoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// MainActivity.java
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private User currentUser;  // Keep track of the current user for updating

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextName = findViewById(R.id.editTextName);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonUpdate = findViewById(R.id.buttonUpdate);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editTextName.getText().toString().trim();

                if (!userName.isEmpty()) {
                    User user = new User();
                    user.setName(userName);

                    userViewModel.insert(user);

                    editTextName.getText().clear();
                }
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedName = editTextName.getText().toString().trim();

                if (currentUser != null && !updatedName.isEmpty()) {
                    currentUser.setName(updatedName);
                    Log.d("sdfghjklkjghfg", "onClick: "+updatedName);

                    userViewModel.update(currentUser);

                    editTextName.getText().clear();
                }
            }
        });

        // Observe the LiveData to update the UI when the data changes
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // Update UI based on the changes in the data
                // For simplicity, you might want to display the user list or handle it according to your UI design
            }
        });
    }
}
