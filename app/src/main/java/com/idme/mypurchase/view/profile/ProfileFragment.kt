package com.idme.mypurchase.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.idme.mypurchase.common.Constants
import com.idme.mypurchase.common.ImageLoader
import com.idme.mypurchase.common.ViewModelFactory
import com.idme.mypurchase.common.base.BaseFragment
import com.idme.mypurchase.databinding.ProfileFragmentBinding
import com.idme.mypurchase.network.ProfileSchema
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : BaseFragment() {

    val screenNavigator
        get() = injector.getScreenNavigator()

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ProfileViewModel>
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this){
            when(it){
                Constants.State.NORMAL -> {
                    binding.profileLayout.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                Constants.State.IN_PROGRESS -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.profileLayout.visibility = View.GONE
                }
                Constants.State.ERROR -> {

                }
            }
        }

        viewModel.userProfile.observe(this){ profile->
            bindingView(profile)
        }

        viewModel.fetchProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        bindingView(viewModel.userProfile.value)
    }

    private fun bindingView(profileSchema: ProfileSchema?){
        profileSchema?.let {
            binding.apply {
                fullName.text = it.fullName
                name.text = it.name
                username.text = it.userName
                phoneNumber.text = it.formattedNumber
                registrationDate.text = it.formattedDate

                imageLoader.load(it.imageUrl,profileImage)

                purchaseBtn.setOnClickListener {
                    screenNavigator.profileToPurchase()
                }
            }
        }
    }
}