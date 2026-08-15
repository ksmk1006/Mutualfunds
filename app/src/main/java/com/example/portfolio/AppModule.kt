package com.example.portfolio

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Teaches Hilt how to build the Room Database
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PortfolioDatabase {
        return Room.databaseBuilder(
            context,
            PortfolioDatabase::class.java,
            "portfolio_db"
        ).build()
    }

    // Teaches Hilt how to pull the DAO out of the Database
    @Provides
    fun provideInvestmentDao(database: PortfolioDatabase): InvestmentDao {
        return database.investmentDao()
    }
}
