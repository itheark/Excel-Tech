package com.greycodes.excel14.excelgallery;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greycodes.excel14.R;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/*
 * This fragment manages the data entry for a
 * new Meal object. It lets the user input a 
 * meal name, give it a rating, and take a 
 * photo. If there is already a photo associated
 * with this meal, it will be displayed in the 
 * preview at the bottom, which is a standalone
 * ParseImageView.
 */
public class NewImageFragment extends android.support.v4.app.Fragment {

	private ImageView photoButton;
	private ImageView saveButton;
	private ImageView cancelButton;
	private ParseImageView mealPreview;
	private EditText title ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle SavedInstanceState) {
		View v = inflater.inflate(R.layout.gallery_fragment_new_meal, parent, false);


		// The mealRating spinner lets people assign favorites of meals they've
		// eaten.
		// Meals with 4 or 5 ratings will appear in the Favorites view.
	
		
			 title = ((EditText) v.findViewById(R.id.excelgallery_gettitle));
			
		
		photoButton = ((ImageView) v.findViewById(R.id.photo_button));
		photoButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				startCamera();
			}
		});

		saveButton = ((ImageView) v.findViewById(R.id.save_button));
		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Gallery meal = ((NewImageActivity) getActivity()).getCurrentMeal();

				// When the user clicks "Save," upload the meal to Parse
				// Add data to the meal object:
			

				// Associate the meal with the current user
				meal.setAuthor(ParseUser.getCurrentUser());

				
				// Add the rating
				meal.setRating("5");
				meal.setTitle(title.getText().toString());
				// If the user added a photo, that data will be
				// added in the CameraFragment

				// Save the meal and return
				meal.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						if (e == null) {
							getActivity().setResult(Activity.RESULT_OK);
							getActivity().finish();
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
									"Error saving: " + e.getMessage(),
									Toast.LENGTH_SHORT).show();
						}
					}

				});

			}
		});

		cancelButton = ((ImageView) v.findViewById(R.id.cancel_button));
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().setResult(Activity.RESULT_CANCELED);
				getActivity().finish();
			}
		});

		// Until the user has taken a photo, hide the preview
		mealPreview = (ParseImageView) v.findViewById(R.id.meal_preview_image);
		mealPreview.setVisibility(View.INVISIBLE);

		return v;
	}

	/*
	 * All data entry about a Meal object is managed from the NewMealActivity.
	 * When the user wants to add a photo, we'll start up a custom
	 * CameraFragment that will let them take the photo and save it to the Meal
	 * object owned by the NewMealActivity. Create a new CameraFragment, swap
	 * the contents of the fragmentContainer (see activity_new_meal.xml), then
	 * add the NewMealFragment to the back stack so we can return to it when the
	 * camera is finished.
	 */
	public void startCamera() {
		android.support.v4.app.Fragment cameraFragment = new CameraFragment();
		android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
		

		transaction.replace(R.id.fragmentContainer, cameraFragment);
		transaction.addToBackStack("NewImageFragment");
		transaction.commit();
	}

	/*
	 * On resume, check and see if a meal photo has been set from the
	 * CameraFragment. If it has, load the image in this fragment and make the
	 * preview image visible.
	 */
	@Override
	public void onResume() {
		super.onResume();
		ParseFile photoFile = ((NewImageActivity) getActivity())
				.getCurrentMeal().getPhotoFile();
		if (photoFile != null) {
			mealPreview.setParseFile(photoFile);
			mealPreview.loadInBackground(new GetDataCallback() {
				@Override
				public void done(byte[] data, ParseException e) {
					mealPreview.setVisibility(View.VISIBLE);
				}
			});
		}
	}

}
