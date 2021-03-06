package com.agusibrahim.appkasir;
import android.app.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v4.widget.*;
import android.support.v7.widget.*;
import android.support.design.widget.*;
import android.view.*;
import android.support.v4.view.*;
import android.widget.Toast;
import android.content.res.*;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.agusibrahim.appkasir.Fragment.*;
import com.agusibrahim.appkasir.Model.Produk;
import java.util.*;
import com.agusibrahim.appkasir.Adapter.*;

public class MainActivity extends AppCompatActivity
{
	DrawerLayout mDrawer;
	NavigationView nvDrawer;
	ActionBarDrawerToggle drawerToggle;
	Toolbar toolbar;
	public static ProdukDataAdapter dataproduk;
	public static BelanjaanDataAdapter dataBalanjaan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar=(Toolbar) findViewById(R.id.mytoolbar);
		setSupportActionBar(toolbar);
		mDrawer=(DrawerLayout) findViewById(R.id.drawer_layout);
		nvDrawer=(NavigationView) findViewById(R.id.naView);
		setupDrawer(nvDrawer);
		drawerToggle= setupDrawerToggle();
		mDrawer.addDrawerListener(drawerToggle);
		getSupportFragmentManager().beginTransaction().replace(R.id.konten, new belanjaFragment()).commit();
		nvDrawer.getMenu().getItem(0).setChecked(true);
		dataproduk=new ProdukDataAdapter(this, Produk.getInit(this));
		dataBalanjaan=new BelanjaanDataAdapter(this);
    }
	private void setupDrawer(NavigationView nview){
		nview.setNavigationItemSelectedListener(
			new NavigationView.OnNavigationItemSelectedListener(){
				public boolean onNavigationItemSelected(MenuItem menu){
					Terpilih(menu);
					return true;
				}
			});
	}
	private ActionBarDrawerToggle setupDrawerToggle(){
		return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_buka, R.string.drawer_tutup);
	}
	private void Terpilih(MenuItem menu){
		Class fragclass;
		Fragment frag = null;
		switch(menu.getItemId()){
			case R.id.frag1:
				fragclass = belanjaFragment.class;
				break;
			case R.id.frag2:
				fragclass=productFragment.class;
				break;
			default:
				fragclass=belanjaFragment.class;
		}
		try{
			frag=(Fragment) fragclass.newInstance();
		}catch(Exception e){}
		FragmentManager fm=getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.konten, frag).commit();
		menu.setChecked(true);
		setTitle(menu.getTitle());
		mDrawer.closeDrawers();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO: Implement this method
		switch (item.getItemId()){
			case android.R.id.home:
				mDrawer.openDrawer(GravityCompat.START);
				return true;
			
				
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mainmenu, menu);
		return true;
	}

}
