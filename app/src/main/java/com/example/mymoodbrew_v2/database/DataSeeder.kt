package com.example.mymoodbrew_v2.database

import com.example.mymoodbrew_v2.models.CoffeeRecipe
import com.example.mymoodbrew_v2.models.CoffeeVariation
import com.example.mymoodbrew_v2.models.Ingredient
import com.example.mymoodbrew_v2.models.VariationIngredient
import com.example.mymoodbrew_v2.models.WeeklyRecipe
import com.example.mymoodbrew_v2.models.CoffeeLink
import com.example.mymoodbrew_v2.models.User
import com.example.mymoodbrew_v2.models.Favorite
import com.example.mymoodbrew_v2.models.Recommendation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object DataSeeder {
    fun seedDatabase(db: AppDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            db.clearAllTables();

            val coffeeRecipeDao = db.coffeeRecipeDao();
            val coffeeVariationDao = db.coffeeVariationDao();
            val ingredientDao = db.ingredientDao();
            val variationIngredientDao = db.variationIngredientDao();
            val weeklyRecipeDao = db.weeklyRecipeDao();
            val coffeeLinkDao = db.coffeeLinkDao();
            val userDao = db.userDao();
            val favoriteDao = db.favoriteDao();
            val recommendationDao = db.recommendationDao();

            // Insert Coffee Recipes
            val cappuccinoRecipe = CoffeeRecipe(
                recipeId = 1,
                title = "Cappuccino",
                description = "Espresso coffee topped with frothed milk and milk foam",
                ingredients = "Espresso, Milk",
                preparationSteps = "Brew espresso, Steam milk, Foam milk, Combine",
                recommendedDuration = 7,
                imageUrl = "https://media.istockphoto.com/id/505168330/de/foto/tasse-caf%C3%A9-latte-macchiato-mit-kaffeebohnen-und-zimt.jpg?s=612x612&w=0&k=20&c=sTGL_8pXFroBXotCW4KcGCjLf5X1Prs0jh6hqdPc2FI="
            );
            coffeeRecipeDao.insert(cappuccinoRecipe);

            val americanoRecipe = CoffeeRecipe(
                recipeId = 2,
                title = "Americano",
                description = "Espresso with hot water added for a stronger, diluted flavor",
                ingredients = "Espresso, Hot water",
                preparationSteps = "Brew espresso, Add hot water",
                recommendedDuration = 5,
                imageUrl = "https://www.roastmarket.de/magazin/wp-content/uploads/2016/05/Caffe-Americano-in-Tasse.jpg"
            );
            coffeeRecipeDao.insert(americanoRecipe);

            val mochaRecipe = CoffeeRecipe(
                recipeId = 3,
                title = "Mocha",
                description = "A chocolate-flavored variant of a latte with espresso and milk",
                ingredients = "Espresso, Milk, Chocolate syrup",
                preparationSteps = "Brew espresso, Steam milk, Add chocolate syrup, Combine",
                recommendedDuration = 8,
                imageUrl = "https://images.immediate.co.uk/production/volatile/sites/2/2021/11/Mocha-1fc71f7.png?resize=768,574"
            );
            coffeeRecipeDao.insert(mochaRecipe);

            // Insert Coffee Variations
            val icedLatteVariation = CoffeeVariation(
                variationId = 1,
                name = "Iced Latte",
                description = "Cold version of a latte served with ice",
                caffeineContent = 95.0,
                calories = 200,
                fatContent = 7.0,
                sugar = 22.0,
                imageUrl = "https://cdn11.bigcommerce.com/s-5ljyj9oebs/images/stencil/600x600/products/2819/18201/P071422183748_1__66386.1690917599.jpg?c=2",
                recipeId = 2 // refers to the "Americano" recipe
            );
            coffeeVariationDao.insert(icedLatteVariation);

            val caramelMacchiatoVariation = CoffeeVariation(
                variationId = 2,
                name = "Caramel Macchiato",
                description = "Latte with caramel syrup on top",
                caffeineContent = 90.0,
                calories = 230,
                fatContent = 6.0,
                sugar = 28.0,
                imageUrl = "https://dinnerthendessert.com/wp-content/uploads/2023/10/Caramel-Macchiato-10.jpg",
                recipeId = 2 // refers to the "Americano" recipe
            );
            coffeeVariationDao.insert(caramelMacchiatoVariation);

            val blackCoffeeVariation = CoffeeVariation(
                variationId = 3,
                name = "Black Coffee",
                description = "A simple brewed coffee without milk or sugar",
                caffeineContent = 95.0,
                calories = 5,
                fatContent = 0.0,
                sugar = 0.0,
                imageUrl = "https://wakeupcoffee.ca/wp-content/uploads/2024/02/img-How-to-Make-Black-Coffee-Taste-Good-1.webp",
                recipeId = 1 // refers to the "Cappuccino" recipe
            );
            coffeeVariationDao.insert(blackCoffeeVariation);

            // Insert Ingredients
            val espressoBeans = Ingredient(ingredientId = 1, name = "Espresso beans", description = "Ground coffee beans used to make espresso");
            ingredientDao.insert(espressoBeans);

            val milk = Ingredient(ingredientId = 2, name = "Milk", description = "Dairy or non-dairy liquid used for steaming or mixing");
            ingredientDao.insert(milk);

            val chocolateSyrup = Ingredient(ingredientId = 3, name = "Chocolate syrup", description = "Sweet liquid used for adding chocolate flavor to beverages");
            ingredientDao.insert(chocolateSyrup);

            val vanillaSyrup = Ingredient(ingredientId = 4, name = "Vanilla syrup", description = "Sweet liquid used for flavoring coffee with vanilla");
            ingredientDao.insert(vanillaSyrup);

            // Insert Variation Ingredients
            variationIngredientDao.insert(VariationIngredient(id = 1, variationId = 1, ingredientId = 1, quantity = 1.5, unit = "oz")); // Iced Latte (Espresso beans)
            variationIngredientDao.insert(VariationIngredient(id = 2, variationId = 1, ingredientId = 2, quantity = 8.0, unit = "oz"));   // Iced Latte (Milk)
            variationIngredientDao.insert(VariationIngredient(id = 3, variationId = 2, ingredientId = 2, quantity = 8.0, unit = "oz"));   // Caramel Macchiato (Milk)
            variationIngredientDao.insert(VariationIngredient(id = 4, variationId = 2, ingredientId = 1, quantity = 1.5, unit = "oz")); // Caramel Macchiato (Espresso beans)
            variationIngredientDao.insert(VariationIngredient(id = 5, variationId = 2, ingredientId = 3, quantity = 1.0, unit = "tbsp")); // Caramel Macchiato (Caramel syrup)

            // Insert Weekly Recipes
            weeklyRecipeDao.insert(WeeklyRecipe(weeklyRecipeId = 1, coffeeRecipeId = 1, startDate = "2025-01-01", endDate = "2025-01-07")); // Espresso week
            weeklyRecipeDao.insert(WeeklyRecipe(weeklyRecipeId = 2, coffeeRecipeId = 2, startDate = "2025-01-08", endDate = "2025-01-14")); // Latte week
            weeklyRecipeDao.insert(WeeklyRecipe(weeklyRecipeId = 3, coffeeRecipeId = 3, startDate = "2025-01-15", endDate = "2025-01-21")); // Cappuccino week

            // Insert Coffee Links
            coffeeLinkDao.insert(CoffeeLink(linkId = 1, variationId = 1, url = "https://example.com/icedlatte/recipe", description = "Recipe for Iced Latte"));
            coffeeLinkDao.insert(CoffeeLink(linkId = 2, variationId = 2, url = "https://example.com/caramelmacchiato/recipe", description = "Caramel Macchiato preparation"));
            coffeeLinkDao.insert(CoffeeLink(linkId = 3, variationId = 3, url = "https://example.com/blackcoffee/recipe", description = "How to make Black Coffee"));

            // Insert Users
            userDao.insert(User(userId = 1, name = "John Doe", email = "johndoe@example.com", passwordHash = "hashed_password_123"));
            userDao.insert(User(userId = 2, name = "Jane Smith", email = "janesmith@example.com", passwordHash = "hashed_password_456"));
            userDao.insert(User(userId = 3, name = "Alex Brown", email = "alexbrown@example.com", passwordHash = "hashed_password_789"));

            // Insert Favorites
            favoriteDao.insert(Favorite(favoriteId = 1, userId = 1, coffeeRecipeId = 1, createdAt = "2025-01-01 10:00:00"));
            favoriteDao.insert(Favorite(favoriteId = 2, userId = 2, coffeeRecipeId = 2, createdAt = "2025-01-05 14:00:00"));
            favoriteDao.insert(Favorite(favoriteId = 3, userId = 3, coffeeRecipeId = 3, createdAt = "2025-01-10 12:00:00"));

            // Insert Recommendations
            recommendationDao.insert(Recommendation(recommendationId = 1, userId = 1, coffeeRecipeId = 1, createdAt = "2025-01-01 09:00:00", expiresAt = "2025-01-02 09:00:00"))
            recommendationDao.insert(Recommendation(recommendationId = 2, userId = 2, coffeeRecipeId = 2, createdAt = "2025-01-06 11:00:00", expiresAt = "2025-01-07 11:00:00"))
            recommendationDao.insert(Recommendation(recommendationId = 3, userId = 3, coffeeRecipeId = 3, createdAt = "2025-01-11 08:00:00", expiresAt = "2025-01-12 08:00:00"))
        }
    }
}