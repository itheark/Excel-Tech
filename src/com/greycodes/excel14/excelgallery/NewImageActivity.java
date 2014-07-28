package com.greycodes.excel14.excelgallery;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.greycodes.excel14.R;

/*
 * NewMealActivity contains two fragments that handle
 * data entry and capturing a photo of a given meal.
 * The Activity manages the overall meal data.
 */
public class NewImageActivity extends FragmentActivity {

	private Gallery meal;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		meal = new Gallery();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);

		// Begin with main data entry view,
		// NewMealFragment
		setContentView(R.layout.gallery_activity_new_meal);
		
		android.support.v4.app.FragmentManager manager =getSupportFragmentManager();
		android.support.v4.app.Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

		if (fragment == null) {
			fragment = new NewImageFragment();
			manager.beginTransaction().add(R.id.fragmentContainer, fragment)
					.commit();
		}
	}

	public Gallery getCurrentMeal() {
		return meal;
	}

}
