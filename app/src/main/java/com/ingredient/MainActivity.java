package com.ingredient;

import android.support.v4.app.Fragment;
import android.view.Menu;

public class MainActivity extends SingleFragmentActivity {
//    List<Recipe> recipes;
//    IngredientDAO ingredientDAO;
//    IngredientCursorWrapper cursorWrapper;
//
//    @BindView(R.id.search_view)
//    SearchView searchView;
//    @BindView(R.id.recyclerIngredient)
//    RecyclerView recyclerIngredient;
//
//    @BindView(R.id.recyclerRecipe)
//    RecyclerView recyclerRecipe;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_list_of_recipes);
//        ButterKnife.bind(this);
//        ingredientDAO = IngredientDAO.getInstance(this);
//        recipes = ingredientDAO.getRecipeList(null);
//        Log.e("Main", "sdfdd");
//
//        initUI();
//    }
//
//    private void initUI() {
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//            getSupportActionBar().setCustomView(R.layout.text_action_bar);
//        }
//        searchView.setQueryHint(getResources().getString(R.string.find_by_ingredients));
//        searchView.setIconifiedByDefault(false);
//        int searchPlateId = searchView.getContext().getResources()
//                .getIdentifier("android:id/search_plate", null, null);
//        View searchPlateView = searchView.findViewById(searchPlateId);
//        if (searchPlateView != null) {
//            searchPlateView.setBackgroundColor(Color.WHITE);
//        }
//        searchView.setSuggestionsAdapter(new SimpleCursorAdapter(
//                this, android.R.layout.simple_list_item_1, null,
//                new String[]{DBSchema.IngredientTable.Cols.NAME},
//                new int[]{android.R.id.text1}));
//        List<Ingredient> list = new ArrayList<>();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (newText.length() > 1) {
//                    cursorWrapper = ingredientDAO.getIngredientByKey(newText);
//                    searchView.getSuggestionsAdapter().changeCursor(cursorWrapper);
//                }
//                return false;
//            }
//        });
//        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
//            @Override
//            public boolean onSuggestionSelect(int position) {
//                return false;
//            }
//
//            @Override
//            public boolean onSuggestionClick(int position) {
//                searchView.setQuery("", false);
//                if (recyclerIngredient.getVisibility() == View.GONE) {
//                    recyclerIngredient.setVisibility(View.VISIBLE);
//                }
//                ((IngredientAdapter) recyclerIngredient.getAdapter()).addIngredient(cursorWrapper.getSimpleIngredient());
//                List<Ingredient> ingredients = ((IngredientAdapter) recyclerIngredient.getAdapter()).getIngredients();
//                recyclerIngredient.getAdapter().notifyItemInserted(ingredients.size());
//                List<Recipe> recipestese = ingredientDAO.getRecipeIdsByIngredients(ingredients);
//                recyclerRecipe.setAdapter(new RecipeAdapter(MainActivity.this, recipestese));
//                return true;
//            }
//        });
//
//        if (recyclerIngredient.getAdapter() == null) {
//            recyclerIngredient.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//            recyclerIngredient.setAdapter(new IngredientAdapter(this, list, recyclerIngredient, recyclerRecipe));
//        }
//        recyclerRecipe.setLayoutManager(new LinearLayoutManager(this));
//        recyclerRecipe.setAdapter(new RecipeAdapter(this, recipes));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}
