package com.example.listycity

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CityRepository {
    private val _cities =
        mutableStateListOf(
            City("Edmonton", "AB"),
            City("Vancouver", "BC"),
            City("Toronto", "ON"),
        )
    private val db = Firebase.firestore
    private val citiesRef = db.collection("cities")

    init {
        citiesRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            _cities.clear()
            snapshot?.documents?.forEach { doc ->
                try {
                    val city = doc.toObject(City::class.java)
                    if (city != null) {
                        _cities.add(city)
                    }
                } catch (e: Exception) {
                    Log.e("CityRepository", "Error converting city", e)
                }
            }
        }
    }

    val cities: List<City>
        get() = _cities

    fun addCity(city: City) {
        citiesRef.document(city.id).set(city)
    }

    fun updateCity(
        oldCity: City,
        updatedCity: City,
    ) {
        citiesRef.document(oldCity.id).set(updatedCity)
    }

    fun deleteCity(city: City) {
        citiesRef.document(city.id).delete()
    }
}
