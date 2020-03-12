package com.example.cocktailapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlin.text.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileReader
import java.io.FileWriter
import android.content.Intent
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        var fileDirectory = getFilesDir()
        var fileName:String = fileDirectory.absolutePath
        //data/user/0/com.example.cocktailapplication/files
        fileName = fileName + "/CocktailData.txt"

        ////////////////////////////////////////////////////////////////////////
        // String is used to store data to support export of cocktail data    //
        // in future versions                                                 //
        ////////////////////////////////////////////////////////////////////////

        var dataString = "1; Manhattan; Whiskey, Vermouth, Angostura; Stir with Ice; 2 oz, Whiskey (Bourbon), 1 oz, Sweet Vermouth (Italian), 2 dash, Angostura Bitters,; 1, Maraschino Cherry,; Mix ingerdients. Stir with ice 20 times. Serve in ice-colded-glass.;\n" +
                "2; Martini (Dry); Gin or Vodka, Vermouth; Stir with Ice; 4 oz, Gin (traditional) or Vodka, 1 dash, Dry Vermouth,; 3, Olives,; Mix ingredients. Stir with ice 40 times. Serve in ice-colded-glass.;\n" +
                "3; Margarita; Tequila, Orange & Lime Juice; Shake with Ice; 1.5 oz, Tequila, 1 oz, Cointreau(or ~ orange juice), 0.75 oz, lime juice,; On rim, Salt,; Mix ingredients. Shake with ice 30 times. Ice-colded-glass with salt rim.;\n" +
                "4; Old fashioned; Whiskey, Angostura; Stir with Ice; 1, Suger Cube, 3-4 dash, Angostura Bitters (2-6 based on style), 5 sq cm, Orange Peel, Splash, Soda, 2 oz, Whiskey (Rye),; 2, Maraschino Cherry, Must be, On Rocks, Optional, Orange Slice,; Suger cube in glass. Pour bitters. Muddle slowly till break feel. Squeeze orange peel then put it in glass. Slightly muddle orange peel. Add soda, whiskey, ice. Stir 30 times. Garnish.;\n" +
                "5; Negroni; Gin, Vermouth, Campari(bitters); Stir with Ice; 1 oz, Gin, 1 oz, Sweet Vermouth, 1 oz , Campari,; Peel/slice, Orange,; Mix ingredients with ice. Stir 30 times. Strain into ice-colded-glass. Garnish.;\n" +
                "6; Sour; Any Liquor, Any Sour Ingredient; Shake with Ice; 1.5 - 2 oz, Base Liquor (Any), 0.75 oz, Sour Ingredient, 1 oz, Sweet Ingredient,; Optional, Fruit of Sour/Sweet Ingredient, Optional, On Rocks,; Mix all with ice. Shake. Strain. Garnish.;\n" +
                "7; California Sour = Stone Sour; Sour + Orange Juice; Shake with Ice; 1.5 - 2 oz, Base Liquor (Any), 0.75 oz, Sour Ingredient, 1 oz, Sweet Ingredient, 1 oz, Orange Juice,; Optional, Orange Slice, Optional, On Rocks,; Mix all with ice. Shake. Strain. Garnish.;\n" +
                "8; Claret Lemonade; Lemonade with wine or Sour with wine; Shake with Ice; 4 oz, Red Wine, 0.75 oz, Lemon Juice, 1 oz, Simple Syrup,; Must be, On ton of Ice,; Mix all with ice. Shake. In another glass, put ton of ice. Pour and strain mixed ingredients in glass with ton of ice.;\n" +
                "9; Daiquiri; Sour with Rum; Shake with Ice; 1.5 - 2 oz, Rum (White), 0.75 oz, Lime Juice, 1 oz, Simple Syrup,; Slice, Lime,; Mix all with ice. Shake. Strain. Garnish.;\n" +
                "10; Sex on the Beach; Alcoholic fruit juice; Stir with Ice; Few, Solid Ice till glass fills, 1.5 oz, Vodka, 0.5 oz, Peach Liquor (Peach Schnapps), 1.5 oz, Cranberry Juice, 1.5 oz, Orange Juice OR Pineapple Juice,; 1, Maraschino Cherry, Slice, Orange, 1, Umbrella,; Put solid ice till glass fills. Add all ingredients. Stir well. Add cherry and orange slice on umbrella. Garnish.;\n" +
                "11; Pina Colada; Rum, Pineapple Juice, Coconut; Shake with Ice; 2 oz, Rum (White), 1.5 oz, Coconut Cream, 2 oz, Pineapple Juice,; Wedge, Pineapple,; Mix all with ice. Shake 20 -30 sec or may want to run blender. Garnish.;\n" +
                "12; Brown Derby; Whiskey Sour with Grapefruit; Shake with Ice; 1.5 oz, Bourbon, 1 oz, Grapefruit Juice, 0.5 oz, Honey Syrup,; None, None,; Mix all with ice. Shake.;\n"

        var AllCocktails = mutableListOf<Cocktail>()

        var fout = FileWriter(fileName, false)
        fout.write(dataString)
        fout.close()

        var fin = FileReader(fileName)
        var allLines = fin.readLines()
        for (cocktailString in allLines)
        {
            AllCocktails.add(ConvertCocktailStringToCockatialObject(cocktailString))
        }


        val myadapter = CocktailListAdapter(this, AllCocktails)
        val mylistView = findViewById(R.id.home_listview) as ListView
        mylistView.adapter = myadapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.appbar_measures)
        {
            val intent = Intent(this, MeasurementsActivity::class.java)
            this.startActivity(intent)
            return true
        }
        else{
            return super.onOptionsItemSelected(item)
        }

    }

    fun ConvertCocktailStringToCockatialObject(CocktailString : String) : Cocktail {
        var ingredients = mutableListOf<Pair<String, String>>()
        var garnishIngredients = mutableListOf<Pair<String, String>>()

        var startIndex = 0

        var nextTokenIndex = FindNextToken(CocktailString, startIndex)
        var ID = CocktailString.substring(nextTokenIndex.first, nextTokenIndex.second)
        startIndex = nextTokenIndex.second + 1

        nextTokenIndex = FindNextToken(CocktailString, startIndex)
        var cocktailName = CocktailString.substring(nextTokenIndex.first, nextTokenIndex.second)
        startIndex = nextTokenIndex.second + 1

        nextTokenIndex = FindNextToken(CocktailString, startIndex)
        var Summary = CocktailString.substring(nextTokenIndex.first, nextTokenIndex.second)
        startIndex = nextTokenIndex.second + 1

        nextTokenIndex = FindNextToken(CocktailString, startIndex)
        var MixMethod = CocktailString.substring(nextTokenIndex.first, nextTokenIndex.second)
        startIndex = nextTokenIndex.second + 1

        nextTokenIndex = FindNextToken(CocktailString, startIndex)
        var IngredientListString = CocktailString.substring(nextTokenIndex.first, nextTokenIndex.second)
        ingredients = FindAllIngredients(IngredientListString)
        startIndex = nextTokenIndex.second + 1

        nextTokenIndex = FindNextToken(CocktailString, startIndex)
        var GarnishIngredientListString = CocktailString.substring(nextTokenIndex.first, nextTokenIndex.second)
        garnishIngredients = FindAllIngredients(GarnishIngredientListString)
        startIndex = nextTokenIndex.second + 1

        nextTokenIndex = FindNextToken(CocktailString, startIndex)
        var Instruction = CocktailString.substring(nextTokenIndex.first, nextTokenIndex.second)

        return Cocktail(ID, cocktailName, Summary, MixMethod, ingredients, garnishIngredients, Instruction)
    }

    fun FindNextToken(cocktailString : String, startIndex : Int) : Pair<Int, Int> {
        var endIndex = cocktailString.indexOf(";", startIndex)
        return Pair(startIndex, endIndex)
    }

    fun FindAllIngredients(ingredientString : String) : MutableList<Pair<String, String>> {
        var ingredients = mutableListOf<Pair<String, String>>()
        var ingredientStringLength = ingredientString.length
        var startIndex = 0
        while (startIndex < ingredientStringLength) {
            var ingredientQuantityIndex = FindNextIngredient(ingredientString, startIndex)
            startIndex = ingredientQuantityIndex.second + 1
            var ingredientNameIndex = FindNextIngredient(ingredientString, startIndex)
            startIndex = ingredientNameIndex.second + 1
            ingredients.add(
                Pair(
                    ingredientString.substring(ingredientQuantityIndex.first, ingredientQuantityIndex.second),
                    ingredientString.substring(ingredientNameIndex.first, ingredientNameIndex.second)
                )
            )
        }

        return ingredients
    }

    fun FindNextIngredient(ingredientString: String, startIndex : Int) : Pair<Int, Int> {
        var endIndex = ingredientString.indexOf(",", startIndex)
        return Pair(startIndex, endIndex)
    }
}
