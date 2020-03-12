package com.example.cocktailapplication

class Cocktail(val cocktailidconstructor : String, val cocktailnameconstructor : String,
               val SummaryConstructor : String, val MixMethodConstructor : String,
               val IngredientsConstructor : MutableList<Pair<String, String>>,
               val GarnishIngredientsConstructor : MutableList<Pair<String, String>>,
               val InstructionConstructor : String) {
    var Id : String
    var CocktailName : String
    var Summary : String
    var MixMethod : String
    var Ingredients : MutableList<Pair<String, String>>
    var GarnishIngredients : MutableList<Pair<String, String>>
    var Instruction : String

    init {
        this.Id = cocktailidconstructor
        this.CocktailName = cocktailnameconstructor
        this.Summary = SummaryConstructor
        this.MixMethod = MixMethodConstructor
        this.Ingredients = IngredientsConstructor
        this.GarnishIngredients = GarnishIngredientsConstructor
        this.Instruction = InstructionConstructor
    }

    fun GetIngredientQuantityList() : MutableList<String> {
        var ingredientQuantityList = mutableListOf<String>()
        for(ingredient in this.Ingredients)
        {
            ingredientQuantityList.add(ingredient.first)
        }

        return ingredientQuantityList
    }

    fun GetIngredientNameList() : MutableList<String> {
        var ingredientNameList = mutableListOf<String>()
        for(ingredient in this.Ingredients)
        {
            ingredientNameList.add(ingredient.second)
        }

        return ingredientNameList
    }

    fun GetGarnishIngredientQuantityList() : MutableList<String> {
        var garnishIngredientQuantityList = mutableListOf<String>()
        for(ingredient in this.GarnishIngredients)
        {
            garnishIngredientQuantityList.add(ingredient.first)
        }

        return garnishIngredientQuantityList
    }

    fun GetGarnishIngredientNameList() : MutableList<String> {
        var garnishIngredientNameList = mutableListOf<String>()
        for(ingredient in this.GarnishIngredients)
        {
            garnishIngredientNameList.add(ingredient.second)
        }

        return garnishIngredientNameList
    }
}