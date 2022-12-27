package pol3436.test.moto_history.ui.notifications

import android.R
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pol3436.test.moto_history.databinding.FragmentNotificationsBinding
import pol3436.test.moto_history.utill.AppUtill


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val view1: Activity = requireActivity()
        val view: Activity = getActivity() as Activity
        println(view1.toString() + "Asdf")
        Log.d("test", view1.toString() + "Asdf")


        AppUtill(view1, "상세 asdfdsffds" , Gravity.CENTER_VERTICAL, 0, 0)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}