package com.rajendra.sketchide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.rajendra.sketchide.databinding.ActivityInformationBinding;
import com.rajendra.sketchide.databinding.ActivityMainBinding;
import com.rajendra.sketchide.ui.activities.InformationActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    Toolbar toolbar;
    MenuItem lastCheckedItem; // Keep track of the last checked item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = binding.toolbar; // Initialize the toolbar

        setSupportActionBar(toolbar);

        // Set click listener for the navigation icon
        binding.toolbar.setNavigationOnClickListener(v -> binding.drawerLayout.open());

        // Set item selected listener for navigation view
        binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            // Handle menu item selected
            if (lastCheckedItem != null) {
                lastCheckedItem.setChecked(false);
            }

            // Check the current item
            menuItem.setChecked(true);
            lastCheckedItem = menuItem;

            //Drawer Item Click Action

            int ItemId = menuItem.getItemId();

            if (ItemId == R.id.drawer_about) {
                // Define the URL of the external link
                String url = "https://github.com/androidbulb/SketchIDE/tree/Design"; // Replace this with your desired URL
                // Create an intent with ACTION_VIEW action and the URL data
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                // Start the activity
                startActivity(intent);
            }
            if (ItemId == R.id.drawer_settings) {
                Toast.makeText(MainActivity.this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            }
            if (ItemId == R.id.drawer_information) {
                Intent ideInformationIntent = new Intent(MainActivity.this, InformationActivity.class);
                startActivity(ideInformationIntent);
            }
            if (ItemId == R.id.drawer_tools) {
                Toast.makeText(MainActivity.this, "Tools Clicked", Toast.LENGTH_SHORT).show();
            }
            if (ItemId == R.id.drawer_sign) {
                Toast.makeText(MainActivity.this, "Sign Clicked", Toast.LENGTH_SHORT).show();
            }

            binding.drawerLayout.close();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }
}
