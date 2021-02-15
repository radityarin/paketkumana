package com.radityarin.paketkumana.presentation.home

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.radityarin.paketkumana.R
import com.radityarin.paketkumana.adapter.StatusAdapter
import com.radityarin.paketkumana.data.Resource
import com.radityarin.paketkumana.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()
    private var isSuccess = true
    private var courieCodePosition = 1
    private var courierCodelist: ArrayList<String> = ArrayList()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<NestedScrollView>
    private lateinit var statusAdapter: StatusAdapter
    private lateinit var loadingAnimation: AnimationDrawable
    private var isLoading = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finishAfterTransition()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCartLoadingAnimation()

        statusAdapter = StatusAdapter()
        bottomSheetBehavior = BottomSheetBehavior.from(binding.layoutBottomSheet)

        binding.btnCekResi.setOnClickListener {
            startLoadingAnimation()
            lifecycleScope.launch {
                homeViewModel.getCekResi(
                    courierCodelist[courieCodePosition],
                    binding.inputResi.text.toString()
                )
                    .observe(viewLifecycleOwner) { apiResponse ->
                        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                            binding.tvNoResi.text = apiResponse.cekResi?.summary?.awb
                            binding.tvKurir.text = apiResponse.cekResi?.summary?.courier
                            binding.tvLayanan.text = apiResponse.cekResi?.summary?.service
                            binding.tvTanggal.text = apiResponse.cekResi?.summary?.date
                            binding.tvPengirim.text = apiResponse.cekResi?.detail?.shipper
                            binding.tvPenerima.text = apiResponse.cekResi?.detail?.receiver

                            statusAdapter.setData(apiResponse.cekResi?.history)
                            val recyclerViewState =
                                binding.rvStatus.layoutManager?.onSaveInstanceState()
                            statusAdapter.notifyDataSetChanged()
                            binding.rvStatus.layoutManager?.onRestoreInstanceState(recyclerViewState)
                            binding.rvStatus.adapter = statusAdapter
                            stopLoadingAnimation()
                        } else {
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }


            }
        }

        homeViewModel.getListCourier().observe(viewLifecycleOwner) { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> {
                        //                        loadingVisibility()
                    }
                    is Resource.Success -> {
                        if (isSuccess) {
                            var list: ArrayList<String> = ArrayList()
                            response.data?.forEach {
                                list.add(it.description)
                                courierCodelist.add(it.code)
                            }

                            var aa =
                                context?.let {
                                    ArrayAdapter(
                                        it,
                                        R.layout.support_simple_spinner_dropdown_item,
                                        list
                                    )
                                }
                            aa?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                            with(binding.spinner)
                            {
                                adapter = aa
                                setSelection(0, false)
                            }

                            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    courieCodePosition = position
                                }

                            }

                        }
                    }
                    is Resource.Error -> {
                        isSuccess = false
                        Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCartLoadingAnimation()
    {
        binding.ivLoading.setBackgroundResource(R.drawable.anim_loading)
        loadingAnimation = binding.ivLoading.background as AnimationDrawable
    }

    private fun startLoadingAnimation()
    {
        isLoading = true
        binding.ivLoading.visibility = View.VISIBLE
        loadingAnimation.start()
    }

    private fun stopLoadingAnimation()
    {
        isLoading = false
        binding.ivLoading.visibility = View.INVISIBLE
        loadingAnimation.stop()
    }

}