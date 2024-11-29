-- CoffeeRecipe Table
CREATE TABLE CoffeeRecipe (
    recipeId INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    ingredients TEXT NOT NULL,
    preparationSteps TEXT NOT NULL,
    recommendedDuration INTEGER NOT NULL,
    imageUrl TEXT NOT NULL
);

-- CoffeeVariation Table
CREATE TABLE CoffeeVariation (
    variationId INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    caffeineContent REAL NOT NULL,
    calories INTEGER NOT NULL,
    fatContent REAL NOT NULL,
    sugar REAL NOT NULL,
    imageUrl TEXT NOT NULL,
    recipeId INTEGER NOT NULL,
    FOREIGN KEY (recipeId) REFERENCES CoffeeRecipe(recipeId)
);

-- Ingredient Table
CREATE TABLE Ingredient (
    ingredientId INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT NOT NULL
);

-- VariationIngredient Table
CREATE TABLE VariationIngredient (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    variationId INTEGER NOT NULL,
    ingredientId INTEGER NOT NULL,
    quantity REAL NOT NULL,
    unit TEXT NOT NULL,
    FOREIGN KEY (variationId) REFERENCES CoffeeVariation(variationId),
    FOREIGN KEY (ingredientId) REFERENCES Ingredient(ingredientId)
);

-- WeeklyRecipe Table
CREATE TABLE WeeklyRecipe (
    weeklyRecipeId INTEGER PRIMARY KEY AUTOINCREMENT,
    coffeeRecipeId INTEGER NOT NULL,
    startDate TEXT NOT NULL,
    endDate TEXT NOT NULL,
    FOREIGN KEY (coffeeRecipeId) REFERENCES CoffeeRecipe(recipeId)
);

-- CoffeeLink Table
CREATE TABLE CoffeeLink (
    linkId INTEGER PRIMARY KEY AUTOINCREMENT,
    variationId INTEGER NOT NULL,
    url TEXT NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (variationId) REFERENCES CoffeeVariation(variationId)
);

-- User Table
CREATE TABLE User (
    userId INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    passwordHash TEXT NOT NULL
);

-- Favorite Table
CREATE TABLE Favorite (
    favoriteId INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    coffeeRecipeId INTEGER NOT NULL,
    createdAt TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (coffeeRecipeId) REFERENCES CoffeeRecipe(recipeId)
);

-- Recommendation Table
CREATE TABLE Recommendation (
    recommendationId INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    coffeeRecipeId INTEGER NOT NULL,
    createdAt TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expiresAt TEXT NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (coffeeRecipeId) REFERENCES CoffeeRecipe(recipeId)
);