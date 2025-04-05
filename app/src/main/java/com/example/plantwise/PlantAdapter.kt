package com.example.plantwise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class PlantAdapter(private var plantList: List<Plant>) :
    RecyclerView.Adapter<PlantAdapter.PlantViewHolder>(), Filterable {

    private var filteredPlantList: List<Plant> = plantList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plant, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = filteredPlantList[position]
        holder.bind(plant)
    }

    override fun getItemCount(): Int = filteredPlantList.size

    inner class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val plantImage: ImageView = itemView.findViewById(R.id.plantImage)
        private val plantName: TextView = itemView.findViewById(R.id.plantName)
//        private val plantInfo: TextView = itemView.findViewById(R.id.plantInfo)

        fun bind(plant: Plant) {
            plantName.text = plant.name
//            plantInfo.text = "Sunlight: ${plant.sunlight} • Water: ${plant.watering}"
            Glide.with(itemView.context).load(plant.image).into(plantImage)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault()) ?: ""
                val result = if (query.isEmpty()) {
                    plantList
                } else {
                    plantList.filter {
                        it.name.lowercase(Locale.getDefault()).contains(query)
                    }
                }

                return FilterResults().apply { values = result }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredPlantList = results?.values as List<Plant>
                notifyDataSetChanged()
            }
        }
    }
}
