package com.idme.mypurchase.view.purchase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.idme.mypurchase.common.Constants
import com.idme.mypurchase.common.ImageLoader
import com.idme.mypurchase.common.Utils
import com.idme.mypurchase.common.ViewModelFactory
import com.idme.mypurchase.common.base.BaseFragment
import com.idme.mypurchase.databinding.PurchaseFragmentBinding
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [PurchaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PurchaseFragment : BaseFragment() {

    val screenNavigator
        get() = injector.getScreenNavigator()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<PurchaseViewModel>
    private val viewModel: PurchaseViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var purchaseAdapter: PurchaseAdapter

    private var _binding: PurchaseFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this){
            when(it){
                Constants.State.NORMAL -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                Constants.State.IN_PROGRESS -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Constants.State.ERROR -> {

                }
            }
        }

        viewModel.purchaseList.observe(this){ purchaseList ->
            purchaseList?.let {
                purchaseAdapter.bindData(it)
            }
        }

        viewModel.fetchPurchase()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PurchaseFragmentBinding.inflate(inflater, container, false)

        purchaseAdapter = PurchaseAdapter(utils, imageLoader)
        binding.apply {
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = purchaseAdapter
            }
            backBtn.setOnClickListener { screenNavigator.navigateUp() }
        }

        return binding.root
    }
}