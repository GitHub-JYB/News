package admin_jyb.news.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import admin_jyb.news.R;
import admin_jyb.news.news.ContentFragment;
import admin_jyb.news.news.social.SocialFragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        setupToggle();
        setupDrawerContent();
        replaceFragment();
    }

    private void setupDrawerContent() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private void replaceFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content, ContentFragment.newInstence()).commit();
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_news:
                if (!isItemChecked(item)){
                    replaceFragment();
                }
                break;
            case R.id.navigation_photo:
                if (!isItemChecked(item)){
                    replaceFragment();
                }
                break;
        }
        item.setChecked(true);
        drawerlayout.closeDrawers();
    }

    private boolean isItemChecked(MenuItem item) {
        return item.isChecked();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        actionBarDrawerToggle.syncState();//没有这个的话进去默认是箭头，加了之后就是三横杠的
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return actionBarDrawerToggle.onOptionsItemSelected(item)
                || super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)){
            drawerlayout.closeDrawers();
        }else {
            super.onBackPressed();
        }
    }


    private void initToolbar() {
        toolbar.setTitle("新闻中心");
        setSupportActionBar(toolbar);
    }

    /**
     * 关联drawerlayout和toolbar
     */
    private void setupToggle() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout,
                toolbar, R.string.open, R.string.close);
        drawerlayout.setDrawerListener(actionBarDrawerToggle);
    }
}
