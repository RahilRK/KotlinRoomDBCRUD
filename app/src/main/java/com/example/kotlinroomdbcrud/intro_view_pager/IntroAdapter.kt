import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinroomdbcrud.databinding.IntroItemBinding
import com.example.kotlinroomdbcrud.intro_view_pager.IntroModel

class IntroAdapter(
    private val activity: Context,
    private val list: ArrayList<IntroModel>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<IntroAdapter.ViewHolder>() {

    var tag = "homeSliderAdapter"
    lateinit var binding: IntroItemBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        binding = IntroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val model = list[position]
        binding.apply {

            imageView.setBackgroundResource(model.image)
            txtTitle1.text = model.title1
            txtTitle2.text = model.title2
            txtText.text = model.txtText
            txtTitle2.lineColor = model.underlineColor
        }


        if(position == list.size - 2) {
            viewPager2.post { runn }
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }

    private val runn = Runnable {
        list.addAll(list)
        notifyDataSetChanged()
    }
}