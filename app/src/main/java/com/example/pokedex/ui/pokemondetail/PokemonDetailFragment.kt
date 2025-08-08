package com.example.pokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonDetailBinding
import com.example.pokedex.ui.adapter.SpriteAdapter
import com.example.pokedex.ui.adapter.StatAdapter
import com.example.pokedex.ui.adapter.TypeAdapter

// TODO: where's the topAppBar for this screen?
class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding
    private var player: ExoPlayer? = null

    private val viewModel: PokemonDetailViewModel by lazy {
        ViewModelProvider(this)[PokemonDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = PokemonDetailFragmentArgs.fromBundle(requireArguments())
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        viewModel.getPokemonDetail(name = args.pokemonId)
        viewModel.pokemonDetail.observe(viewLifecycleOwner) { response ->
            if (response?.name?.isNotBlank() == true) {
                binding.detailTitle.text = response.name
            } else {
                binding.detailTitle.text = getString(R.string.error_no_internet)
            }

            response?.types?.let { types ->
                binding.recyclerviewTypes.adapter = TypeAdapter(types)
                binding.recyclerviewTypes.layoutManager = GridLayoutManager(context, 2)
            }

            response?.sprites?.let { sprites ->
                val spriteUrls = listOfNotNull(
                    sprites.frontDefault,
                    sprites.backDefault,
                    sprites.frontShiny,
                    sprites.backShiny,
                    sprites.frontFemale,
                    sprites.backFemale,
                    sprites.frontShinyFemale,
                    sprites.backShinyFemale
                )

                binding.recyclerviewSprites.adapter = SpriteAdapter(spriteUrls)
                binding.recyclerviewSprites.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

                val snapHelper = PagerSnapHelper()
                binding.recyclerviewSprites.onFlingListener = null
                snapHelper.attachToRecyclerView(binding.recyclerviewSprites)
            }

            // formats the height and weight variables to metric and concatenates its strings to print one line
            response?.let {
                binding.detailHeightWeight.text =
                    buildString {
                        append("%.2f".format(response.height.times(0.1)))
                        append(" mts - ")
                        append("%.2f".format(response.weight.times(0.1)))
                        append(" kg ")
                    }
            }

            response?.stats?.let { stats ->
                binding.recyclerviewStats.adapter = StatAdapter(stats)
                binding.recyclerviewStats.layoutManager = GridLayoutManager(context, 2)
            }

            binding.btnPlay.isEnabled = !response?.cries?.latest.isNullOrEmpty()
            response?.name?.isEmpty()?.let {
                binding.btnFavorite.isEnabled = !it
                binding.btnFavorite.text = getFavoriteBtnText(response.name)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player = ExoPlayer.Builder(requireContext()).build()
        binding.btnPlay.setOnClickListener {
            playCry()
        }
        binding.btnFavorite.setOnClickListener {
            viewModel.pokemonDetail.value?.name?.let { it ->
                viewModel.toggleFavorite(it)
                binding.btnFavorite.text = getFavoriteBtnText(it)
            }
        }
    }

    private fun playCry() {
        viewModel.pokemonDetail.value?.cries?.latest?.let { cryUrl ->
            val mediaItem = MediaItem.fromUri(cryUrl)
            player?.apply {
                setMediaItem(mediaItem)
                prepare()
                play()
            }
        }
    }

    private fun getFavoriteBtnText(name: String): String {
        if (viewModel.favoriteRepository.isFavorite(name)) {
            return "Remove favorite"
        } else {
            return "Add favorite"
        }
    }
}
