package dat.com.QuizzDT;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new PlayFragment());
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                item.setChecked(true);
                switch (id)
                {
                    case R.id.dashboard:
                        replaceFragment(new PlayFragment());
                        //reset so cau tra loi dung
                        History.resetCorrectCount();
                        break;
                    case R.id.selectChallenge:
                        replaceFragment(new ViewHisFragment());
                        //reset so cau tra loi dung
                        History.resetCorrectCount();
                        break;
                }

                return true;
            }
        });

    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.body, fragment);
        fragmentTransaction.commit();
    }

    //create option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optional_menu, menu);
        return true;
    }
    //option onclick
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_info){
            Intent intent = new Intent(MainActivity.this, InformationActivity.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.action_rate){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            String UriText = "mailto:" + Uri.encode("uetmemberx@gmail.com") + "?subject="
                    //get Android version
                    + Uri.encode("Góp ý: Android Release " + Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)","$1")))
                    + Uri.encode("");
            Uri uri = Uri.parse(UriText);
            intent.setData(uri);
            startActivity(Intent.createChooser(intent, "send email"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}