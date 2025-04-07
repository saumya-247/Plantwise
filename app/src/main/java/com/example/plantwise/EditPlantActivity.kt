package com.example.plantwise

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class EditPlantActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var descEditText: EditText
    private lateinit var timeTextView: TextView
    private lateinit var saveButton: Button

    private var hour = 0
    private var minute = 0
    private var plantId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_plants_activity) // 💡 Updated to use your new layout!

        // 🌱 Grabbing references from layout
        nameEditText = findViewById(R.id.plantNameInput)
        descEditText = findViewById(R.id.plantDescInput)
        timeTextView = findViewById(R.id.wateringTimePicker) // Now a TextView!
        saveButton = findViewById(R.id.savePlantBtn)

        // 🆔 Get plant ID from intent
        plantId = intent.getStringExtra("plantId") ?: ""
        if (plantId.isEmpty()) {
            Toast.makeText(this, "Oops! Missing plant ID 😖", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // 💾 Load existing plant details into UI
        nameEditText.setText(intent.getStringExtra("name") ?: "")
        descEditText.setText(intent.getStringExtra("desc") ?: "")
        hour = intent.getIntExtra("hour", 0)
        minute = intent.getIntExtra("minute", 0)
        updateTimeText()

        // ⏰ Open time picker when TextView is clicked
        timeTextView.setOnClickListener {
            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                hour = selectedHour
                minute = selectedMinute
                updateTimeText()
            }, hour, minute, true).show()
        }

        // 💾 Save changes to Firestore
        saveButton.setOnClickListener {
            val updatedData = mapOf(
                "name" to nameEditText.text.toString(),
                "desc" to descEditText.text.toString(),
                "hour" to hour,
                "minute" to minute
            )

            FirebaseFirestore.getInstance()
                .collection("user_plants")
                .document(plantId)
                .update(updatedData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Plant updated 🌿", Toast.LENGTH_SHORT).show()

                    // 🌟 Send result back to MyPlantsActivity
                    setResult(RESULT_OK)
                    finish()
                }
                .addOnFailureListener { error ->
                    Log.e("EditPlantActivity", "Update failed: ${error.message}")
                    Toast.makeText(this, "Update failed 😢", Toast.LENGTH_SHORT).show()
                }
        }

    }

    private fun updateTimeText() {
        timeTextView.text = String.format("Watering Time: %02d:%02d", hour, minute)
    }
}
