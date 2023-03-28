package com.example.BolettiShop.vm

import com.example.BolettiShop.db.ProductDatabase.Companion.getDatabase

import android.app.Application
import com.example.BolettiShop.db.ProductDao
import androidx.lifecycle.LiveData
import com.example.BolettiShop.db.ProductDatabase
import com.example.BolettiShop.tables.Product
import com.example.BolettiShop.tables.Categories

class ProductRepository(application: Application) {
    private val menuDao: ProductDao?
    val menu1Week: LiveData<List<Product?>?>?
    val categories: LiveData<List<Categories?>?>?
    fun insertMenu1(menu1Week: Product?) {
        ProductDatabase.dbWriteExecutor.execute { menuDao!!.insertMenu1Week(menu1Week) }
    }

    fun insertCategories(categories: Categories?) {
        ProductDatabase.dbWriteExecutor.execute { menuDao!!.insertCategories(categories) }
    }

    fun deleteMenu1() {
        menuDao!!.deleteMenu1Week()
    }

    fun deleteCategories() {
        menuDao!!.deleteCategories()
    }

    init {
        menuDao = getDatabase(application.applicationContext)!!.menuDao()
        menu1Week = menuDao!!.LoadAllMenu1Week()
        categories = menuDao.LoadAllCategories()
    }
}