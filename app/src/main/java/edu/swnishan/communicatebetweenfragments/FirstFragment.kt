package edu.swnishan.communicatebetweenfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //listen result from the ChildFragment through childFragmentManager
        //Once result receive show the Toast
        childFragmentManager.setFragmentResultListener("key_parent", this) {key, result->
            // get the result from bundle
            val stringResult = result.getString("say_hi_parent")
            Toast.makeText(requireContext(), "$key: $stringResult", Toast.LENGTH_SHORT).show()
        }


        //add child fragment
        childFragmentManager.beginTransaction().replace(R.id.child_fragment_container,ChildFragment()).commit()

        //show another fragment whn click the next.
        //this will add to the same fragment manager
        button_next.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, 0, 0, R.anim.exit_to_right)
                .add(R.id.fragment_container,SecondFragment())
                .addToBackStack(null)
                .commit()
        }

        //pass result to the ChildFragment through childFragmentManager when press the button
        button_child.setOnClickListener {
            childFragmentManager.setFragmentResult("key_child", bundleOf("say_hi_child" to "Hi child fragment"))
        }
    }
}