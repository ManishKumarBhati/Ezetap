package com.bmk.baseproject.fragment

import android.os.Bundle
import android.view.View
import com.bmk.baseproject.R
import com.bmk.baseproject.databinding.FragmentSecondSampleBinding

import com.bmk.domain.Response
import com.bumptech.glide.Glide

class SecondSampleFragment : BaseFragment() {
    lateinit var binding: FragmentSecondSampleBinding

    override fun getLayout() = R.layout.fragment_second_sample

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSecondSampleBinding.bind(view)
        arguments?.let {
            it.getParcelable<Response>(ARGS_USER_DATA)?.let { response ->
                binding.tvHeader.text = response.headingText
                Glide.with(this)
                    .load(response.logoUrl)
                    .into(binding.ivLogo)
            }
        }
    }

    companion object {
        const val ARGS_USER_DATA = "user_data"
    }
}