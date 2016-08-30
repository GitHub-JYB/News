package admin_jyb.news.main.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import admin_jyb.news.R;
import admin_jyb.news.main.presenter.MainPresenterImpl;
import admin_jyb.news.news.NewsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;


    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MainPresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);
        initToolbar();
        initToggle();
        initDrawerContent();
        replaceFragment(NewsFragment.newInstence());
    }

    private void initDrawerContent() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        presenter.switchNavigation(item);
                        return true;
                    }
                });
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
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            closeDrawers();
        } else {
            super.onBackPressed();
        }
    }


    private void initToolbar() {
        toolbar.setTitle(R.string.Title);
        setSupportActionBar(toolbar);
    }

    /**
     * 关联drawerlayout和toolbar
     */
    private void initToggle() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout,
                toolbar, R.string.open, R.string.close);
        drawerlayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public void closeDrawers() {
        drawerlayout.closeDrawers();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment).commit();
    }
}
