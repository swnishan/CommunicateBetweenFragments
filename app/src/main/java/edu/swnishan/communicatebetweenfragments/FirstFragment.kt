package edu.swnishan.communicatebetweenfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        //listen fragment result from the child fragment manager.
        //Once result receive show the Toast
        childFragmentManager.setFragmentResultListener("key_parent", this) {key, result->
            // get the result from bundle
            val stringResult = result.getString("say_hi")
            Toast.makeText(requireContext(), "$key: $stringResult", Toast.LENGTH_LONG).show()
        }

        childFragmentManager.beginTransaction().replace(R.id.child_fragment_container,ChildFragment()).commit()

        button_next.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, 0, 0, R.anim.exit_to_right)
                .add(R.id.fragment_container,SecondFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}