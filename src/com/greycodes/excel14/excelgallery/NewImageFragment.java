package com.greycodes.excel14.excelgallery;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.greycodes.excel14.R;
import com.greycodes.excel14.database.ExcelDataBase;
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
String[] name;
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
				if(title.length()>25){
					Toast.makeText(getActivity(), "Caption should be less than 25 characters", Toast.LENGTH_LONG).show();
				}
				else {
					Gallery meal = ((NewImageActivity) getActivity())
							.getCurrentMeal();
					Toast.makeText(getActivity(),
							"Photo will be publiched once approved",
							Toast.LENGTH_LONG).show();
					// When the user clicks "Save," upload the meal to Parse
					// Add data to the meal object:
					// Associate the meal with the current user
					SharedPreferences sharedPreferences = getActivity()
							.getSharedPreferences("login", Context.MODE_PRIVATE);
					if (sharedPreferences.getBoolean("registered", false)) {

						String[] columns = { "FNAME" };
						ExcelDataBase excelDataBase = new ExcelDataBase(
								getActivity());
						SQLiteDatabase sqLiteDatabase = excelDataBase
								.getSQLiteDataBase();
						Cursor cursor = sqLiteDatabase.query("USER", columns,
								null, null, null, null, null);
						cursor.moveToFirst();
						name = new String[1];
						name[0] = cursor.getString(cursor
								.getColumnIndex("FNAME"));
					} else {
						name[0] = "Guest";
					}
					ParseUser parseUser = new ParseUser();
					parseUser.setUsername(name[0]);
					meal.setAuthor(ParseUser.getCurrentUser());
					//	meal.setAuthor(ParseUser);
					// Add the rating
					meal.setRating("5");
					String tit =title.getText().toString();
					if(tit.length()==0){
						tit="Excel 2014";
					}
					meal.setTitle(name[0] + "  " + tit);
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
