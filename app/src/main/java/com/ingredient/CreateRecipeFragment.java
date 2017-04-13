package com.ingredient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ingredient.adapters.IngredientAdapter;
import com.ingredient.adapters.StepPageAdapter;
import com.ingredient.database.DBSchema;
import com.ingredient.database.IngredientCursorWrapper;
import com.ingredient.database.IngredientDAO;
import com.ingredient.objects.recipeModel.Ingredient;
import com.ingredient.objects.recipeModel.Recipe;
import com.ingredient.objects.recipeModel.Step;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CreateRecipeFragment extends Fragment implements View.OnClickListener {
    private static final int REQUEST_MAIN_IMAGE = 10000;
    IngredientDAO ingredientDAO;
    Recipe recipe = new Recipe();
    IngredientCursorWrapper cursorWrapper;
    StepPageAdapter stepPageAdapter;
    IngredientAdapter ingredientAdapter;
    Bitmap mainBitmap;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.recyclerIngredient)
    RecyclerView recyclerIngredient;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.steps_view_pager)
    ViewPager stepsViewPager;
    @BindView(R.id.iBackStep)
    ImageView iBackStep;
    @BindView(R.id.iNextStep)
    ImageView iNextStep;
    @BindView(R.id.iAddStep)
    ImageView iAddStep;
    @BindView(R.id.eName)
    EditText eName;
    @BindView(R.id.eDescription)
    EditText eDescription;
    @BindView(R.id.progressMainImage)
    ProgressBar progressMainImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        ingredientDAO = IngredientDAO.getInstance(getContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_create_recipe, container, false);
        ButterKnife.bind(this, v);
        initUI();
        return v;
    }

    private void initUI() {
        imageView2.setOnClickListener(this);
        if (mainBitmap != null){
            imageView2.setImageBitmap(mainBitmap);
        }
        iBackStep.setOnClickListener(this);
        iBackStep.setVisibility(View.GONE);
        iNextStep.setOnClickListener(this);
        iNextStep.setVisibility(View.GONE);
        iAddStep.setOnClickListener(this);
        searchView.setQueryHint(getResources().getString(R.string.add_ingredient));
        searchView.setIconifiedByDefault(false);
        int searchPlateId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_plate", null, null);
        View searchPlateView = searchView.findViewById(searchPlateId);
        if (searchPlateView != null) {
            searchPlateView.setBackgroundColor(Color.WHITE);
        }
        searchView.setSuggestionsAdapter(new SimpleCursorAdapter(
                getActivity(), android.R.layout.simple_list_item_1, null,
                new String[]{DBSchema.IngredientTable.Cols.NAME},
                new int[]{android.R.id.text1}));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1) {
                    cursorWrapper = ingredientDAO.getIngredientByKey(newText,((IngredientAdapter) recyclerIngredient.getAdapter()).getIngredients());
                    searchView.getSuggestionsAdapter().changeCursor(cursorWrapper);
                }
                return false;
            }
        });
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                searchView.setQuery("", false);
                if (recyclerIngredient.getVisibility() == View.GONE) {
                    recyclerIngredient.setVisibility(View.VISIBLE);
                }
                ((IngredientAdapter) recyclerIngredient.getAdapter()).addIngredient(cursorWrapper.getSimpleIngredient());
                List<Ingredient> ingredients = ((IngredientAdapter) recyclerIngredient.getAdapter()).getIngredients();
                recyclerIngredient.getAdapter().notifyItemInserted(ingredients.size());
                return true;
            }
        });

        if (ingredientAdapter == null) {
            ingredientAdapter = new IngredientAdapter(getActivity(), recyclerIngredient, null);
        } else {
            if (recyclerIngredient.getVisibility() == View.GONE && ingredientAdapter.getIngredients().size() > 0) {
                recyclerIngredient.setVisibility(View.VISIBLE);
            }
        }
        recyclerIngredient.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerIngredient.setAdapter(ingredientAdapter);
        if (stepPageAdapter == null) {
            List<Step> steps = new ArrayList<>();
            steps.add(new Step(null, ""));
            stepPageAdapter = new StepPageAdapter(getActivity(), steps, stepsViewPager, iBackStep, iNextStep, iAddStep);
        }
        stepsViewPager.setAdapter(stepPageAdapter);
        stepsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position != 0) {
                    iBackStep.setVisibility(View.VISIBLE);
                } else {
                    iBackStep.setVisibility(View.GONE);
                }
                if (position + 1 != stepsViewPager.getAdapter().getCount()) {
                    iNextStep.setVisibility(View.VISIBLE);
                    iAddStep.setVisibility(View.GONE);
                } else {
                    iNextStep.setVisibility(View.GONE);
                    iAddStep.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == iBackStep.getId()) {
            stepsViewPager.setCurrentItem(stepsViewPager.getCurrentItem() - 1);
        } else if (id == iNextStep.getId()) {
            stepsViewPager.setCurrentItem(stepsViewPager.getCurrentItem() + 1);
        } else if (id == iAddStep.getId()) {
            ((StepPageAdapter) stepsViewPager.getAdapter()).addStep(new Step(null, ""));
            stepsViewPager.setCurrentItem(stepsViewPager.getCurrentItem() + 1);
        } else if (id == imageView2.getId()) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            getActivity().startActivityForResult(intent, REQUEST_MAIN_IMAGE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_create_recipe) {
            if (eName.getText().toString().length() < 3) {
                Toast.makeText(getContext(), "Название рецепта должно иметь более 3х символов", Toast.LENGTH_LONG).show();
                return false;
            } else if (eDescription.getText().toString().length() < 3) {
                Toast.makeText(getContext(), "Описание рецепта должно иметь более 3х символов", Toast.LENGTH_LONG).show();
                return false;
            } else if (((IngredientAdapter) recyclerIngredient.getAdapter()).getIngredients().size() < 1) {
                Toast.makeText(getContext(), "Должен быть хотя бы 1 ингредиент!", Toast.LENGTH_LONG).show();
                return false;
            } else if (((StepPageAdapter) stepsViewPager.getAdapter()).getSteps().size() < 1) {
                Toast.makeText(getContext(), "Должен быть хотя бы 1 шаг приготовления!", Toast.LENGTH_LONG).show();
                return false;
            }
            recipe.setIngredients(((IngredientAdapter) recyclerIngredient.getAdapter()).getIngredients());
            recipe.setSteps(((StepPageAdapter) stepsViewPager.getAdapter()).getSteps());
            recipe.setName(eName.getText().toString());
            recipe.setDescription(eDescription.getText().toString());
            recipe.setId(new Random().nextInt(Integer.MAX_VALUE));
            IngredientDAO.getInstance(getContext()).addRecipe(recipe);
            getActivity().setResult(Activity.RESULT_OK);
            getActivity().finish();
        }
        return true;
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                if (inputStream != null) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
                    bmp = getResizedBitmap(bmp, 700);
                    if (requestCode >= 0 && requestCode != REQUEST_MAIN_IMAGE) {
                        ImageView imageView = (ImageView) ((StepPageAdapter) stepsViewPager.getAdapter()).getViews().get(requestCode).findViewById(R.id.iStep);
                        ProgressBar progressUploadImage = (ProgressBar) ((StepPageAdapter) stepsViewPager.getAdapter()).getViews().get(requestCode).findViewById(R.id.progressUploadImage);
                        Step step = ((StepPageAdapter) stepsViewPager.getAdapter()).getSteps().get(requestCode);
                        step.setBitmap(bmp);
                        step.setUpload(true);
                        progressUploadImage.setVisibility(View.VISIBLE);
                        imageView.setImageBitmap(bmp);
                    } else if (requestCode == REQUEST_MAIN_IMAGE) {
                        mainBitmap = bmp;
                        imageView2.setImageBitmap(bmp);
                        progressMainImage.setVisibility(View.VISIBLE);
                    }
//                    iEventImage.setImageBitmap(bmp);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    byte[] image = outputStream.toByteArray();
                    String path = "recipe/" + UUID.randomUUID() + ".jpg";
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference reference = storage.getReference(path);
                    UploadTask uploadTask = reference.putBytes(image);
                    uploadTask.addOnSuccessListener(getActivity(), new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") Uri uri = taskSnapshot.getDownloadUrl();
                            if (requestCode >= 0 && requestCode != REQUEST_MAIN_IMAGE) {
                                Step step = ((StepPageAdapter) stepsViewPager.getAdapter()).getSteps().get(requestCode);
                                step.setImage(uri.toString());
                                step.setUpload(false);
                                if (((StepPageAdapter) stepsViewPager.getAdapter()).getViews().get(requestCode) != null) {
                                    ProgressBar progressUploadImage = (ProgressBar) ((StepPageAdapter) stepsViewPager.getAdapter()).getViews().get(requestCode).findViewById(R.id.progressUploadImage);
                                    if (progressUploadImage != null) {
                                        progressUploadImage.setVisibility(View.GONE);
                                    }
                                }
                            } else if (requestCode == REQUEST_MAIN_IMAGE) {
                                recipe.setImage(uri.toString());
                                progressMainImage.setVisibility(View.GONE);
                            }
                        }
                    });
                    uploadTask.addOnFailureListener(getActivity(), new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (requestCode >= 0 && requestCode != REQUEST_MAIN_IMAGE) {
                                Toast.makeText(getActivity(), "фото не загружено! шаг " + requestCode + " " + e.getCause(), Toast.LENGTH_LONG).show();
                                ((StepPageAdapter) stepsViewPager.getAdapter()).getSteps().get(requestCode).setUpload(false);
                                if (((StepPageAdapter) stepsViewPager.getAdapter()).getViews().get(requestCode) != null) {
                                    ProgressBar progressUploadImage = (ProgressBar) ((StepPageAdapter) stepsViewPager.getAdapter()).getViews().get(requestCode).findViewById(R.id.progressUploadImage);
                                    progressUploadImage.setVisibility(View.GONE);
                                }
                            } else if (requestCode == REQUEST_MAIN_IMAGE) {
                                Toast.makeText(getActivity(), "фото не загружено!" + e.getCause(), Toast.LENGTH_LONG).show();
                                progressMainImage.setVisibility(View.GONE);
                            }
                        }
                    });

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}
