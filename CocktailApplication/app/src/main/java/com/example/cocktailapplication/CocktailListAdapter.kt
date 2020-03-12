package com.example.cocktailapplication

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button

class CocktailListAdapter(private val context: Context,
                          private val dataSource: MutableList<Cocktail>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.cocktail_list_view, parent, false)
        val cocktailListButton = rowView.findViewById<View>(R.id.cocktail_list_view_button) as Button
        val cocktail = getItem(position) as Cocktail

        var cocktailListString = SpannableString (cocktail.CocktailName + "\n" + cocktail.Summary)
        cocktailListString.setSpan(RelativeSizeSpan(1.3f), 0,cocktail.CocktailName.length, 0)
        cocktailListButton.text = cocktailListString

        if (position % 2 == 0) {
            cocktailListButton.setBackgroundResource(R.drawable.cocktail_list_button_even)
        }
        else{
            cocktailListButton.setBackgroundResource(R.drawable.cocktail_list_button_odd)
        }

        cocktailListButton.setOnClickListener {
            val intent = Intent(context, CocktailDetailActivity::class.java)

            ////////////////////////////////////////////////////////////////////////
            // To Do : Use parcelable to pass entire cocktail object              //
            ////////////////////////////////////////////////////////////////////////
            intent.putExtra("cocktailName", cocktail.CocktailName)
            intent.putExtra("MixMethod", cocktail.MixMethod)
            intent.putStringArrayListExtra("ingredientsQuantity", ArrayList(cocktail.GetIngredientQuantityList()))
            intent.putStringArrayListExtra("ingredientsName", ArrayList(cocktail.GetIngredientNameList()))
            intent.putStringArrayListExtra("garnishIngredientsQuantity", ArrayList(cocktail.GetGarnishIngredientQuantityList()))
            intent.putStringArrayListExtra("garnishIngredientsName", ArrayList(cocktail.GetGarnishIngredientNameList()))
            intent.putExtra("Instruction", cocktail.Instruction)

            context.startActivity(intent)
        }
        return rowView
    }
}
