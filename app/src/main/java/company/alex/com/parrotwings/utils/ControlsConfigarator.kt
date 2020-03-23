package company.alex.com.parrotwings.utils

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ControlsConfigarator {

    companion object {
        fun configarateRecyclerView(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    LinearLayoutManager(recyclerView.context).orientation
                )
            )
            recyclerView.adapter = adapter
        }
    }
}