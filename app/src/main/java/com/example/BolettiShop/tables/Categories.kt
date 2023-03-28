package com.example.BolettiShop.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
class Categories {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var nextCategories = 0
}