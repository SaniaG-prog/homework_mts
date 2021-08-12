package com.mtsteta.homework1.fragments

import android.os.Bundle
import android.os.health.PackageHealthStats
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import com.mtsteta.homework1.MyViewModel
import com.mtsteta.homework1.R

private const val USER_NAME: String = "userName"
private const val USER_PASSWORD: String = "userPassword"
private const val USER_EMAIL: String = "userEmail"
private const val USER_PHONE: String = "userPhone"

class UserInfoFragment : Fragment() {

    private lateinit var exitButton: Button
    private lateinit var userNameTextView: TextView
    private lateinit var userEmailTextView: TextView
    private lateinit var userNameEditText: EditText
    private lateinit var userPasswordEditText: EditText
    private lateinit var userEmailEditText: EditText
    private lateinit var userPhoneEditText: EditText
    private lateinit var navController: NavController

    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exitButton = view.findViewById(R.id.user_info_button_exit)
        userNameTextView = view.findViewById(R.id.user_info_name)
        userEmailTextView = view.findViewById(R.id.user_info_email)
        userNameEditText = view.findViewById(R.id.user_info_editable_name)
        userPasswordEditText = view.findViewById(R.id.user_info_editable_password)
        userEmailEditText = view.findViewById(R.id.user_info_editable_email)
        userPhoneEditText = view.findViewById(R.id.user_info_editable_phone)
        navController = view.findNavController()

        userNameTextView.text = myViewModel.getValueByKeyInPrefs(USER_NAME)
        userEmailTextView.text = myViewModel.getValueByKeyInPrefs(USER_EMAIL)
        userNameEditText.setText(myViewModel.getValueByKeyInPrefs(USER_NAME))
        userPasswordEditText.setText(myViewModel.getValueByKeyInPrefs(USER_PASSWORD))
        userEmailEditText.setText(myViewModel.getValueByKeyInPrefs(USER_EMAIL))
        userPhoneEditText.setText(myViewModel.getValueByKeyInPrefs(USER_PHONE))

        userNameEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                myViewModel.addPairToPrefs(USER_NAME, s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        userPasswordEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                myViewModel.addPairToPrefs(USER_PASSWORD, s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        userEmailEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                myViewModel.addPairToPrefs(USER_EMAIL, s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        userPhoneEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                myViewModel.addPairToPrefs(USER_PHONE, s.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        exitButton.setOnClickListener {
            navController.navigate(R.id.action_userInfoFragment_to_logInFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserInfoFragment()
    }
}