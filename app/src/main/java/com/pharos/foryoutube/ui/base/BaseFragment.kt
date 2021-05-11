package com.pharos.foryoutube.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.pharos.foryoutube.data.UserPreferences
import com.pharos.foryoutube.data.network.RemoteDataSource
import com.pharos.foryoutube.data.repository.BaseRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())

        lifecycleScope.launch { userPreferences.tokenAccess.first() }

        return binding.root
    }

//    fun logout() = lifecycleScope.launch{
//        val tokenAccess = userPreferences.tokenAccess.first()
//        val api = remoteDataSource.buildApi(JobsApi::class.java, tokenAccess)
//        viewModel.logout(api)
//        userPreferences.clear()
//        requireActivity().startNewActivity(AuthActivity::class.java)
//    }


    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R

}