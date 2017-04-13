package com.ingredient.database;


public class DBSchema {
    static final class RecipeTable{
        static final String NAME = "recipe";

        static final class Cols {
            static final String ID = "_id";
            static final String RECIPE_ID = "recipe_id";
            static final String NAME = "name";
            static final String DESCRIPTION = "description";
            static final String IMAGE = "image";
            static final String FAVORITE = "favorite";
        }
    }
    public static final class IngredientTable{
        static final String NAME = "ingredient";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String NAME = "name";
            static final String INGREDIENT_ID = "ingredient_id";
        }
    }
    static final class IngredientInRecipeTable{
        static final String NAME = "ingredient_in_recipe";

        static final class Cols {
            static final String ID = "_id";
            static final String RECIPE_ID = "recipe_id";
            static final String INGREDIENT_ID = "ingredient_id";
            static final String NAME = "name";
            static final String QUANTITY = "quantity";
        }
    }
    static final class StepsTable{
        static final String NAME = "step";

        static final class Cols {
            static final String ID = "_id";
            static final String RECIPE_ID = "recipe_id";
            static final String DESCRIPTION = "description";
            static final String IMAGE = "image";
        }
    }
}
