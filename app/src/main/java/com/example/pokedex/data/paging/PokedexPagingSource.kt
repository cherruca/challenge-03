package com.example.pokedex.data.paging

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.liveData
import com.example.pokedex.api.PokeApiService
import com.example.pokedex.data.Pokemon

class PokedexPagingSource(
    private val remoteDataSource: PokeApiService
): PagingSource<Int, Pokemon>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val position = params.key ?: 1
            val response = remoteDataSource.getPokemons(position, 5)

            if (response.results != null) {
                LoadResult.Page(
                    data = response.results,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == response.count) null else (position + 1)
                )
            } else {
                LoadResult.Error(throw Exception("No Response"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)

        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(5)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(5)
        }
    }

    private fun getPokemons(): LiveData<PagingData<Pokemon>> = Pager(
        config = PagingConfig(pageSize = 5, maxSize = 5),
        pagingSourceFactory = { PokedexPagingSource(remoteDataSource) }
    ).liveData

}