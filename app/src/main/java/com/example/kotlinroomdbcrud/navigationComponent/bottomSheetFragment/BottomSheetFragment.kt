package com.example.kotlinroomdbcrud.navigationComponent.bottomSheetFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.kotlinroomdbcrud.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var homeMenu: RelativeLayout;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val offsetFromTop = 50
//        (dialog as? BottomSheetDialog)?.behavior?.apply {
//            isFitToContents = false
////            setExpandedOffset(offsetFromTop)
////            state = BottomSheetBehavior.STATE_EXPANDED
//            setPeekHeight(50,true)
//        }


        homeMenu = view.findViewById(R.id.homeMenu);

        val bottomSheetBehavior =  (dialog as BottomSheetDialog).behavior
        bottomSheetBehavior.peekHeight = 100

        homeMenu.setOnClickListener {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)

        }
    }
}