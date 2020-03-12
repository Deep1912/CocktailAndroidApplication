package com.example.cocktailapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class CocktailDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_detail)

        var cocktailName = intent.getStringExtra("cocktailName")
        var actionBar = getSupportActionBar()
        actionBar?.setTitle(cocktailName)

        var MixMethod = intent.getStringExtra("MixMethod")
        val textViewMixMethod = findViewById(R.id.cocktail_detail_MixMethod) as TextView
        textViewMixMethod.setText(MixMethod)

        var ingredientsQuantityStringList = intent.getStringArrayListExtra("ingredientsQuantity")
        var ingredientsNameStringList = intent.getStringArrayListExtra("ingredientsName")
        var cocktailIngredientTableLayout = findViewById(R.id.cocktail_ingredients_table) as TableLayout
        CreateTableForIngredients(
            cocktailIngredientTableLayout,
            ingredientsQuantityStringList,
            ingredientsNameStringList
        )

        var garnishIngredientsQuantityStringList = intent.getStringArrayListExtra("garnishIngredientsQuantity")
        var garnishIngredientsNameStringList = intent.getStringArrayListExtra("garnishIngredientsName")
        var cocktailGarnishIngredientTableLayout = findViewById(R.id.cocktail_garnishIngredients_table) as TableLayout
        CreateTableForIngredients(
            cocktailGarnishIngredientTableLayout,
            garnishIngredientsQuantityStringList,
            garnishIngredientsNameStringList
        )

        var Instruction = intent.getStringExtra("Instruction")
        val textViewInstruction = findViewById(R.id.cocktail_detail_Instruction) as TextView
        textViewInstruction.setText(Instruction.toString().replace(". ", ".\n "))
    }

    fun CreateTextViewWithPadding(content: String, paddingInPixel: Int): TextView {
        val firstcol = TextView(this)
        firstcol.apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            text = content
        }
        if (paddingInPixel > 0) {
            firstcol.setWidth(paddingInPixel)
        }

        return firstcol
    }

    fun CreateTableForIngredients(
        cocktailIngredientTableLayout: TableLayout,
        ingredientsQuantityStringList: MutableList<String>,
        ingredientsNameStringList: MutableList<String>
    ) {

        for (i in 0..(ingredientsQuantityStringList.size - 1)) {
            val row = TableRow(this)
            row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            row.addView(CreateTextViewWithPadding(ingredientsQuantityStringList[i], 150))
            row.addView(CreateTextViewWithPadding(": " + ingredientsNameStringList[i], -1))

            cocktailIngredientTableLayout.addView(row)
        }
    }
}
