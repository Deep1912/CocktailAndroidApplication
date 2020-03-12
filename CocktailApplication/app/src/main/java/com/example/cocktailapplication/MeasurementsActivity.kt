package com.example.cocktailapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class MeasurementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measurements)

        var actionBar = getSupportActionBar()
        actionBar?.setTitle("Measurments")

        var measurmentContent = CreateMeasurmentContent()
        var measurmentFirstColumnList = GetTableFirstColumnList(measurmentContent)
        var measurmentSecondColumnList = GetTableSecondColumnList(measurmentContent)

        var measurmentContentTable = findViewById(R.id.measurments_content) as TableLayout
        CreateTable(measurmentContentTable, measurmentFirstColumnList, measurmentSecondColumnList)

        var simpleSyrupContent = CreateSimpleSyrupContent()
        var simpleSyrupFirstColumnList = GetTableFirstColumnList(simpleSyrupContent)
        var simpleSyrupSecondColumnList = GetTableSecondColumnList(simpleSyrupContent)

        var simpleSyrupContentTable = findViewById(R.id.simple_syrup_content) as TableLayout
        CreateTable(simpleSyrupContentTable, simpleSyrupFirstColumnList, simpleSyrupSecondColumnList)

        var sourIngredientsContentTextView = findViewById(R.id.sour_ingredients_content) as TextView
        sourIngredientsContentTextView.text = "Lemon Juice\n" +
                "Orange Juice"

        var sweetIngredientsContentTextView = findViewById(R.id.sweet_ingredients_content) as TextView
        sweetIngredientsContentTextView.text = "Simple Syrup\n" +
                "Honey Syrup (1 : 1 ratio of honey and water)\n"
    }

    fun GetTableFirstColumnList(measurmentContent: MutableList<Pair<String, String>>): MutableList<String> {
        var firstColumnList = mutableListOf<String>()

        for (content in measurmentContent) {
            firstColumnList.add(content.first)
        }

        return firstColumnList
    }

    fun GetTableSecondColumnList(measurmentContent: MutableList<Pair<String, String>>): MutableList<String> {
        var secondColumnList = mutableListOf<String>()

        for (content in measurmentContent) {
            secondColumnList.add(content.second)
        }

        return secondColumnList
    }

    fun CreateMeasurmentContent() : MutableList<Pair<String, String>> {
        var measurmentContent = mutableListOf<Pair<String, String>>()

        measurmentContent.add(Pair("1 oz", "2 TBSP (30 ml)"))
        measurmentContent.add(Pair("12 dash", "1 TSP"))
        measurmentContent.add(Pair("Splash", "0.5 oz"))
        measurmentContent.add(Pair("6 drops", "1 dash"))
        measurmentContent.add(Pair("Sugarcube", "1 TSP Sugar"))

        return measurmentContent
    }

    fun CreateSimpleSyrupContent() : MutableList<Pair<String, String>> {
        var simpleSyrupContent = mutableListOf<Pair<String, String>>()

        simpleSyrupContent.add(Pair("1 unit", "Sugar"))
        simpleSyrupContent.add(Pair("1 unit", "Water"))

        return simpleSyrupContent
    }

    fun CreateTable(
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
}
