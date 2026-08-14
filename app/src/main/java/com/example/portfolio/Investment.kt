package com.example.portfolio

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// 1. Define the Entity (The database table)
@Entity(tableName = "investments")
data class Investment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fundName: String,
    val units: Double,
    val averageNav: Double, // The price you bought it at
    val currentNav: Double  // The latest price
)

// 2. Define the DAO (The queries used to access the data)
@Dao
interface InvestmentDao {
    @Query("SELECT * FROM investments ORDER BY fundName ASC")
    fun getAllInvestments(): Flow<List<Investment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvestment(investment: Investment)
}

// 3. Setup the Database
// (exportSchema = false is added here to prevent compiler warnings during the GitHub Actions build)
@Database(entities = [Investment::class], version = 1, exportSchema = false)
abstract class PortfolioDatabase : RoomDatabase() {
    abstract fun investmentDao(): InvestmentDao
}

// 4. Create the Repository 
// (This acts as the bridge between your database and the ViewModel)
class PortfolioRepository @Inject constructor(
    private val dao: InvestmentDao
) {
    // Exposes an observable stream of database changes
    val allInvestments: Flow<List<Investment>> = dao.getAllInvestments()

    suspend fun addInvestment(investment: Investment) {
        dao.insertInvestment(investment)
    }
}

