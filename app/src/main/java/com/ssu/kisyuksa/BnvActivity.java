package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.ssu.kisyuksa.databinding.ActivityBnvBinding;

public class BnvActivity extends AppCompatActivity {

    private ActivityBnvBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBnvBinding.inflate(getLayoutInflater());
        replaceFragment(new RoungeMainFragment());
        setContentView(binding.getRoot());

        binding.bnvMain.setOnNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.bnv_home) {
                replaceFragment(new RoungeMainFragment());
                return true;
            } else if (menuItem.getItemId() == R.id.bnv_check) {
                replaceFragment(new NoticeAndSleepOutFragment());
                return true;
            } else if (menuItem.getItemId() == R.id.bnv_board) {
                replaceFragment(new MainBoardFragment());
                return true;
            } else if (menuItem.getItemId() == R.id.bnv_person) {
                openMyPageActivity();
                return true;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(binding.flContent.getId(), fragment)
                .commit();
    }
    private void openMyPageActivity() {
        Intent intent = new Intent(this, MyPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
