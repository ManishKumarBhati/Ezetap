package com.bmk.baseproject.fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bmk.baseproject.R
import com.bmk.baseproject.databinding.FragmentSampleBinding
import com.bmk.baseproject.helper.Helper
import com.bmk.baseproject.util.ResponseState
import com.bmk.baseproject.viewmodel.SampleViewModel
import com.bmk.domain.Repository
import com.bmk.domain.Response
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SampleFragment : BaseFragment() {
    lateinit var binding: FragmentSampleBinding


    @Inject
    lateinit var repository: Repository

    private val viewModel: SampleViewModel by viewModels()
    var data: Response? = null

    @Inject
    lateinit var helper: Helper
    override fun getLayout(): Int {
        return R.layout.fragment_sample
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSampleBinding.bind(view)
        loadData()
        binding.btnNav.setOnClickListener {
            data?.let {
                val bundle = Bundle()
                bundle.putParcelable(SecondSampleFragment.ARGS_USER_DATA, data)
                findNavController().navigate(
                    R.id.nav_to_second_frag,
                    bundle
                )
            } ?: showToast("data not available you can not perform the action ")
        }
    }

    private fun loadData() {
        viewModel.getData().observe(viewLifecycleOwner) {
            helper.toggleProgress(it is ResponseState.Loading)
            when (it) {
                is ResponseState.Error -> {
                    helper.showError(it.throwable)
                }
                is ResponseState.Success -> this.data = it.data
                else -> {}
            }
        }
    }

}