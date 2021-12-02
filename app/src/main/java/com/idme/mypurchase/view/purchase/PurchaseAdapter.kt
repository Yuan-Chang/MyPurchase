package com.idme.mypurchase.view.purchase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idme.mypurchase.common.ImageLoader
import com.idme.mypurchase.common.Utils
import com.idme.mypurchase.databinding.PurchaseItemBinding
import com.idme.mypurchase.network.PurchaseSchema

class PurchaseAdapter(val utils: Utils, val imageLoader: ImageLoader) : RecyclerView.Adapter<PurchaseViewHolder>() {

    private var _binding: PurchaseItemBinding? = null
    private val binding get() = _binding!!

    private var purchaseList: List<PurchaseSchema> = emptyList()
    var onClickListener: ((PurchaseSchema) -> Unit)? = null

    fun bindData(data: List<PurchaseSchema>) {
        purchaseList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {

        return PurchaseViewHolder(
            PurchaseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        )
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val item = purchaseList[position]

        _binding = PurchaseItemBinding.bind(holder.itemView.rootView)
        binding.apply {
            itemName.text = item.itemName
            purchaseDate.text = item.formattedPurchaseDate
            price.text = "\$${item.price}"
            serial.text = item.serialNumber
            description.text = item.description

            if (item.isOpen){
                detailLayout.visibility = View.VISIBLE
            } else {
                detailLayout.visibility = View.GONE
            }

            imageLoader.load(item.image,itemImage)

            itemRoot.setOnClickListener {
                item.isOpen = !item.isOpen
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount() = purchaseList.size
}

class PurchaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)