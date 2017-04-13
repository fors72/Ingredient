package com.ingredient;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

public class CreateRecipeActivity extends SingleFragmentActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_recipe_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected Fragment createFragment() {
        return new CreateRecipeFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment != null){
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
