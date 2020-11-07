package edu.swnishan.communicatebetweenfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import kotlinx.android.synthetic.main.fragment_child.*


class ChildFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //pass result to ParentFragment through FragmentManager (ParentFragmentManager)
        button_parent.setOnClickListener {
            setFragmentResult("key_parent", bundleOf("say_hi_parent" to "Hi parent fragment"))
        }

        //listen result from the ParentFragment through FragmentManager (ParentFragmentManager)
        //Once result receive show the Toast
        setFragmentResultListener("key_child") {key, result->
            // get the result from bundle
            val stringResult = result.getString("say_hi_child")
            Toast.makeText(requireContext(), "$key: $stringResult", Toast.LENGTH_SHORT).show()
        }
    }
}